package com.example.taller4.widget

import android.app.Service
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.os.IBinder
import android.widget.RemoteViews
import com.example.taller4.Database.FirebaseDataBaseRepository
import com.example.taller4.R

class UserWidgetService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val appWidgetManager = AppWidgetManager.getInstance(this)
        val thisWidget = ComponentName(this, UserWidgetProvider::class.java)
        val allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget)

        for (widgetId in allWidgetIds) {
            val views = RemoteViews(packageName, R.layout.widget_user)

            // Fetch the user names and update the widget
            FirebaseDataBaseRepository().getUsers(
                onSuccess = { users ->
                    val userNames = users.joinToString("\n") { it.name }
                    views.setTextViewText(R.id.text_user_names, userNames)
                    appWidgetManager.updateAppWidget(widgetId, views)
                },
                onFailure = { /* Handle failure */ }
            )
        }

        stopSelf()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
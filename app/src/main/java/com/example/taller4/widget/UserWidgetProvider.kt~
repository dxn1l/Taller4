package com.example.taller4.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.example.taller4.Database.FirebaseDataBaseRepository
import com.example.taller4.R

class UserWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action == AppWidgetManager.ACTION_APPWIDGET_UPDATE) {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val thisWidget = ComponentName(context, UserWidgetProvider::class.java)
            val allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget)
            for (widgetId in allWidgetIds) {
                updateAppWidget(context, appWidgetManager, widgetId)
            }
        }
    }

    companion object {
        private fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
            val views = RemoteViews(context.packageName, R.layout.widget_user)

            // Set up the intent that starts the UpdateService, which will
            // perform the update, and attach it to a PendingIntent.
            val intent = Intent(context, UserWidgetService::class.java)
            val pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
            views.setOnClickPendingIntent(R.id.button_update, pendingIntent)

            // Fetch the user names and update the widget
            FirebaseDataBaseRepository().getUsers(
                onSuccess = { users ->
                    val userNames = users.joinToString("\n") { it.name }
                    views.setTextViewText(R.id.text_user_names, userNames)
                    appWidgetManager.updateAppWidget(appWidgetId, views)
                },
                onFailure = { /* Handle failure */ }
            )
        }
    }
}
package com.example.taller4.Navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.taller4.Database.FirebaseDataBaseRepository
import com.example.taller4.Screens.AddUserScreen
import com.example.taller4.Screens.DetailsUserScreen
import com.example.taller4.Screens.ViewUsersScreen
import com.example.taller4.Screens.WelcomeScreen
import com.example.taller4.Database.User
import com.example.taller4.Screens.ConfigurationScreen
import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background

@Composable
fun TallerApp() {

  val context = LocalContext.current
  val sharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
  var backgroundColor by remember {
    mutableStateOf(Color(sharedPreferences.getInt("backgroundColor", Color.White.toArgb())))
  }
  var textColor by remember { mutableStateOf(if (backgroundColor == Color.Black) Color.White else Color.Black) }

  val firebaseDataBaseRepository = FirebaseDataBaseRepository()
  var currentScreen by remember { mutableStateOf(Screen.WelcomeScreen) }
    var selectedUser by remember { mutableStateOf<User?>(null) }


  Column(modifier = Modifier.background(backgroundColor)) {
    when (currentScreen) {
      Screen.WelcomeScreen -> {
        WelcomeScreen(
          onViewUsersClick = { currentScreen = Screen.ViewUsersScreen },
          textColor = textColor
        )
      }

      Screen.AddUserScreen -> {
        AddUserScreen(
          firebaseDataBaseRepository = firebaseDataBaseRepository,
          onUserAdded = { currentScreen = Screen.ViewUsersScreen },
          onBack = { currentScreen = Screen.ViewUsersScreen },
          textColor = textColor
        )
      }

      Screen.ViewUsersScreen -> {
        ViewUsersScreen(
          firebaseDataBaseRepository = firebaseDataBaseRepository,
          onBack = { currentScreen = Screen.WelcomeScreen },
          onAddUserClick = { currentScreen = Screen.AddUserScreen },
          onSettingsClick = { currentScreen = Screen.ConfigurationScreen },
          onViewDetailsClick = { user ->
            selectedUser = user
            currentScreen = Screen.DetailScreen
          },
          textColor = textColor

          )
      }

      Screen.DetailScreen -> {
        selectedUser?.let { user ->
          DetailsUserScreen(
            user = user,
            onBack = { currentScreen = Screen.ViewUsersScreen },
            textColor = textColor
          )
        }
      }

      Screen.ConfigurationScreen -> {
        ConfigurationScreen(
          onBackToViewUsers = { currentScreen = Screen.ViewUsersScreen },
          onBackToWelcome = { currentScreen = Screen.WelcomeScreen },
          onColorChange = { newColor ->
            backgroundColor = newColor
            textColor = if (newColor == Color.Black) Color.White else Color.Black
          }
        )
      }
    }

  }



}

enum class Screen {
  WelcomeScreen,
  AddUserScreen,
  ViewUsersScreen,
  DetailScreen,
  ConfigurationScreen
}
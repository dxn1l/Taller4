package com.example.taller4.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.taller4.Database.FirebaseDataBaseRepository
import com.example.taller4.Screens.AddUserScreen
import com.example.taller4.Screens.DetailsUserScreen
import com.example.taller4.Screens.ViewUsersScreen
import com.example.taller4.Screens.WelcomeScreen
import com.example.taller4.Database.User

@Composable
fun tallerApp() {

  val firebaseDataBaseRepository = FirebaseDataBaseRepository()
  var currentScreen by remember { mutableStateOf(Screen.WelcomeScreen) }
    var selectedUser by remember { mutableStateOf<User?>(null) }

  when (currentScreen) {
    Screen.WelcomeScreen -> {
      WelcomeScreen(
        onViewUsersClick = { currentScreen = Screen.ViewUsersScreen }
      )
    }
    Screen.AddUserScreen -> {
      AddUserScreen(
        firebaseDataBaseRepository = firebaseDataBaseRepository,
        onUserAdded = { currentScreen = Screen.ViewUsersScreen },
        onBack = { currentScreen = Screen.ViewUsersScreen }
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
          currentScreen = Screen.DetailScreen },

      )
    }
    Screen.DetailScreen -> {
      selectedUser?.let { user ->
        DetailsUserScreen(
          user = user,
          onBack = { currentScreen = Screen.ViewUsersScreen }
        )
      }
    }
    Screen.ConfigurationScreen -> {

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
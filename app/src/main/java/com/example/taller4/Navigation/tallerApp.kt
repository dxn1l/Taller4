package com.example.taller4.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.taller4.Database.FirebaseDataBaseRepository
import com.example.taller4.Screens.AddUserScreen
import com.example.taller4.Screens.ViewUsersScreen
import com.example.taller4.Screens.WelcomeScreen

@Composable
fun tallerApp() {

  val firebaseDataBaseRepository = FirebaseDataBaseRepository()
  var currentScreen by remember { mutableStateOf(Screen.WelcomeScreen) }

  when (currentScreen) {
    Screen.WelcomeScreen -> {
      WelcomeScreen(
        onAddUserClick = { currentScreen = Screen.AddUserScreen },
        onViewUsersClick = { currentScreen = Screen.ViewUsersScreen }
      )
    }
    Screen.AddUserScreen -> {
      AddUserScreen(
        firebaseDataBaseRepository = firebaseDataBaseRepository,
        onUserAdded = { currentScreen = Screen.WelcomeScreen }
      )
    }
    Screen.ViewUsersScreen -> {
      ViewUsersScreen(
        firebaseDataBaseRepository = firebaseDataBaseRepository,
        onBack = { currentScreen = Screen.WelcomeScreen }
      )
    }
  }



}

enum class Screen {
  WelcomeScreen,
  AddUserScreen,
  ViewUsersScreen
}
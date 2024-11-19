package com.example.taller4.Screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.taller4.Database.FirebaseDataBaseRepository

@Composable
fun AddUserScreen(

    firebaseDataBaseRepository: FirebaseDataBaseRepository,
    onUserAdded: () -> Unit

) {
    Text("Add User Screen")
}
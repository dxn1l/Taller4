package com.example.taller4.Screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.taller4.Database.FirebaseDataBaseRepository


@Composable
fun ViewUsersScreen(

    firebaseDataBaseRepository: FirebaseDataBaseRepository,
    onBack: () -> Unit

) {
    Text("View Users Screen")
}
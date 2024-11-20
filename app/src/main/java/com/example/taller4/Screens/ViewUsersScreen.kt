package com.example.taller4.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.taller4.Database.FirebaseDataBaseRepository
import com.example.taller4.Database.User


@Composable
fun ViewUsersScreen(

    firebaseDataBaseRepository: FirebaseDataBaseRepository,
    onBack: () -> Unit,
    onAddUserClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onViewDetailsClick: (User) -> Unit,
    textColor: Color

) {
    var users by remember { mutableStateOf(listOf<User>()) }

    LaunchedEffect(Unit) {
        firebaseDataBaseRepository.getUsers(
            onSuccess = { users = it },
            onFailure = {  }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(users) { user ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = user.name)
                        Button(
                            onClick = { onViewDetailsClick(user) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Cyan,
                                contentColor = Color.Black
                            ),
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Text("Ver Detalles" , color = textColor)
                        }
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = onAddUserClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Cyan,
                    contentColor = Color.Black
                )
            ) {
                Text("Añadir Usuario" , color = textColor)
            }
            Button(
                onClick = onSettingsClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Cyan,
                    contentColor = Color.Black
                )
            ) {
                Text("Configuración" , color = textColor)
            }
            Button(
                onClick = onBack,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Cyan,
                    contentColor = Color.Black
                )
            ) {
                Text("Volver a Bienvenida" , color = textColor)
            }
        }
    }


}
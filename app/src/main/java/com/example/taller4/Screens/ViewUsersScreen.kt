package com.example.taller4.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.taller4.Database.FirebaseDataBaseRepository
import com.example.taller4.Database.User
import androidx.compose.material3.*
import androidx.compose.ui.res.painterResource
import com.example.taller4.R

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
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = user.name, style = MaterialTheme.typography.titleLarge)
                        IconButton(
                            onClick = { onViewDetailsClick(user) }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "Ver Detalles",
                                tint = Color.Cyan
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Volver",
                    tint = Color.Cyan
                )
            }
            IconButton(onClick = onSettingsClick) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Configuración",
                    tint = Color.Cyan
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        FloatingActionButton(
            onClick = onAddUserClick,
            containerColor = Color.Cyan,
            contentColor = Color.Black,
            modifier = Modifier.size(72.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Añadir Usuario",
                tint = Color.Black
            )
        }
    }


}
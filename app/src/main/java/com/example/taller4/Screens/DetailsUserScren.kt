package com.example.taller4.Screens

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taller4.Database.User
import androidx.compose.material3.*

@Composable
fun DetailsUserScreen(
    user: User,
    onBack: () -> Unit,
    textColor: Color
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Nombre: ${user.name}", color = textColor, style = MaterialTheme.typography.titleLarge)
                Text(text = "Apellido: ${user.last_name}", color = textColor, style = MaterialTheme.typography.bodyLarge)
                Text(text = "Dirección: ${user.direction}", color = textColor, style = MaterialTheme.typography.bodyLarge)
                Text(text = "Teléfono: ${user.phone}", color = textColor, style = MaterialTheme.typography.bodyLarge)
                Text(text = "Email: ${user.email}", color = textColor, style = MaterialTheme.typography.bodyLarge)
                Text(text = "Edad: ${user.age}", color = textColor, style = MaterialTheme.typography.bodyLarge)
                Text(text = "Género: ${user.gender}", color = textColor, style = MaterialTheme.typography.bodyLarge)
            }
        }

        IconButton(
            onClick = onBack,
            modifier = Modifier
                .padding(top = 16.dp)
                .size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Volver",
                tint = Color.Cyan
            )
        }
    }

}
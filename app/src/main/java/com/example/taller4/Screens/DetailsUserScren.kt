package com.example.taller4.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taller4.Database.User

@Composable
fun DetailsUserScreen(
    user: User,
    onBack: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Nombre: ${user.name}")
        Text(text = "Apellido: ${user.last_name}")
        Text(text = "Dirección: ${user.direction}")
        Text(text = "Teléfono: ${user.phone}")
        Text(text = "Email: ${user.email}")
        Text(text = "Edad: ${user.age}")
        Text(text = "Género: ${user.gender}")

        Button(
            onClick = onBack,
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Text("Volver a Ver Usuarios")
        }
    }

}
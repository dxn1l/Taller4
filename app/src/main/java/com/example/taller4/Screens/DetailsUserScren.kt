package com.example.taller4.Screens

import androidx.compose.ui.graphics.Color
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
        Text(text = "Nombre: ${user.name}" , color = textColor)
        Text(text = "Apellido: ${user.last_name}" , color = textColor)
        Text(text = "Dirección: ${user.direction}" , color = textColor)
        Text(text = "Teléfono: ${user.phone}" , color = textColor)
        Text(text = "Email: ${user.email}"  , color = textColor)
        Text(text = "Edad: ${user.age}" , color = textColor)
        Text(text = "Género: ${user.gender}" , color = textColor)

        Button(
            onClick = onBack,
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Text("Volver a Ver Usuarios" , color = textColor)
        }
    }

}
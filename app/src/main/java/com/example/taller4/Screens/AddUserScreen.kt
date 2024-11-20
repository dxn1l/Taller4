package com.example.taller4.Screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.taller4.Database.FirebaseDataBaseRepository
import com.example.taller4.Database.User

@Composable
fun AddUserScreen(
    firebaseDataBaseRepository: FirebaseDataBaseRepository,
    onUserAdded: () -> Unit,
    onBack: () -> Unit,
    textColor: Color
) {
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var direction by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Hombre") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre" , color = textColor) },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Apellido" , color = textColor) },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = direction,
            onValueChange = { direction = it },
            label = { Text("Dirección" , color = textColor) },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Teléfono" , color = textColor) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email" , color = textColor) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Edad" , color = textColor) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text("Género:" , color = textColor)
            RadioButton(
                selected = gender == "Hombre",
                onClick = { gender = "Hombre" }
            )
            Text("Hombre" , color = textColor)
            RadioButton(
                selected = gender == "Mujer",
                onClick = { gender = "Mujer" }
            )
            Text("Mujer")
        }
        Button(
            onClick = {
                val user = User(
                    name = name,
                    last_name = lastName,
                    direction = direction,
                    phone = phone,
                    email = email,
                    age = age.toIntOrNull() ?: 0,
                    gender = gender
                )
                firebaseDataBaseRepository.addUser(user, onSuccess = {
                    Log.d("AddUserScreen", "User added successfully")
                    onUserAdded()
                }, onFailure = { exception ->
                    Log.e("AddUserScreen", "Error adding user", exception)
                })
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Text("Añadir Usuario" , color = textColor)
        }
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
package com.example.taller4.Screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
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
            label = { Text("Nombre" ) },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Apellido" ) },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = direction,
            onValueChange = { direction = it },
            label = { Text("Dirección" ) },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Teléfono" ) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email" ) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Edad") },
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
        FloatingActionButton(
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
            containerColor = Color.Cyan,
            contentColor = Color.Black,
            modifier = Modifier
                .padding(top = 16.dp)
                .size(72.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Añadir Usuario",
                tint = Color.Black
            )
        }
        IconButton(
            onClick = onBack,
            modifier = Modifier
                .padding(top = 16.dp)
                .size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Volver a Ver Usuarios",
                tint = Color.Cyan
            )
        }
    }
}
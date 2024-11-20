package com.example.taller4.Screens

import android.content.Context
import android.content.SharedPreferences
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun ConfigurationScreen(
    onBackToViewUsers: () -> Unit,
    onBackToWelcome: () -> Unit,
    onColorChange: (Color) -> Unit
) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
    var backgroundColor by remember {
        mutableStateOf(Color(sharedPreferences.getInt("backgroundColor", Color.White.toArgb())))
    }
    var textColor by remember { mutableStateOf(if (backgroundColor == Color.Black) Color.White else Color.Black) }

    val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    val sensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            event?.let {
                val x = it.values[0]

                // Change background color to black on rotation
                if (x > 7 || x < -7) {
                    backgroundColor = Color.Black
                    textColor = Color.White

                    // Save background color to SharedPreferences
                    with(sharedPreferences.edit()) {
                        putInt("backgroundColor", backgroundColor.toArgb())
                        apply()
                    }

                    // Notify the parent composable about the color change
                    onColorChange(backgroundColor)
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
    }

    DisposableEffect(Unit) {
        sensorManager.registerListener(sensorEventListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        onDispose {
            sensorManager.unregisterListener(sensorEventListener)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Configuration Screen", color = textColor)
        Button(
            onClick = onBackToViewUsers,
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Text("Back to View Users", color = textColor)
        }
        Button(
            onClick = onBackToWelcome,
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Text("Back to Welcome Screen", color = textColor)
        }
        Button(
            onClick = {
                backgroundColor = Color.White
                textColor = Color.Black

                // Save background color to SharedPreferences
                with(sharedPreferences.edit()) {
                    putInt("backgroundColor", backgroundColor.toArgb())
                    apply()
                }

                // Notify the parent composable about the color change
                onColorChange(backgroundColor)
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Text("Reset to White", color = textColor)
        }
    }
}
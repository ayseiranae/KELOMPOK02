package com.example.kelompok02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.kelompok02.ui.theme.KELOMPOK02Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KELOMPOK02Theme {
                // State navigasi: "register" → "login" → "profile"
                var currentScreen by remember { mutableStateOf("register") }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    when (currentScreen) {
                        "register" -> RegistrationScreen(
                            modifier = Modifier.padding(innerPadding),
                            onRegistrationSuccess = { currentScreen = "login" }
                        )
                        "login" -> LoginScreen(
                            modifier = Modifier.padding(innerPadding),
                            onLoginSuccess = { currentScreen = "profile" }
                        )
                        "profile" -> ProfileScreen(
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}
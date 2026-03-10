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
                // REVISI: Start screen ganti ke "welcome"
                var currentScreen by remember { mutableStateOf("welcome") }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    when (currentScreen) {
                        "welcome" -> WelcomeScreen(
                            onLoginClick = { currentScreen = "login" },
                            onRegisterClick = { currentScreen = "register" }
                        )
                        "register" -> RegistrationScreen(
                            modifier = Modifier.padding(innerPadding),
                            onRegistrationSuccess = { currentScreen = "login" },
                            onLoginClick = { currentScreen = "login" },
                            // MENGHUBUNGKAN TOMBOL BACK:
                            onBackClick = { currentScreen = "welcome" }
                        )
                        "login" -> LoginScreen(
                            modifier = Modifier.padding(innerPadding),
                            onLoginSuccess = { currentScreen = "profile" },
                            onForgotPasswordClick = { currentScreen = "forgot_password" },
                            // MENGHUBUNGKAN TOMBOL BACK:
                            onBackClick = { currentScreen = "welcome" }
                        )
                        "forgot_password" -> ForgotPasswordScreen(
                            modifier = Modifier.padding(innerPadding),
                            onBackToLoginClick = { currentScreen = "login" }
                        )
                        "profile" -> ProfileScreen(
                            modifier = Modifier.padding(innerPadding),
                            onLogoutClick = { currentScreen = "welcome" }
                        )
                    }
                }
            }
        }
    }
}
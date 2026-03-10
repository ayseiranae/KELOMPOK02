package com.example.kelompok02

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ForgotPasswordScreen(
    modifier: Modifier = Modifier,
    onBackToLoginClick: () -> Unit = {}
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    var errorMessage by remember { mutableStateOf<String?>(null) }
    var successMessage by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    val coroutineScope = rememberCoroutineScope()

    fun isPasswordValid(pw: String): Boolean {
        val hasMinLength = pw.length >= 8
        val hasLetter = pw.any { it.isLetter() }
        val hasSymbol = pw.any { !it.isLetterOrDigit() && !it.isWhitespace() }
        return hasMinLength && hasLetter && hasSymbol
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Lupa Password", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(bottom = 16.dp))
        Text(text = "Masukkan Username dan Email untuk verifikasi, lalu buat Password baru.", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, textAlign = TextAlign.Center, modifier = Modifier.padding(bottom = 32.dp))

        OutlinedTextField(value = username, onValueChange = { username = it; errorMessage = null; successMessage = null }, label = { Text("Username terdaftar") }, singleLine = true, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(value = email, onValueChange = { email = it; errorMessage = null; successMessage = null }, label = { Text("Email terdaftar") }, singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email), modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = newPassword,
            onValueChange = {
                newPassword = it
                errorMessage = null
                successMessage = null
                passwordError = null
            },
            label = { Text("Password Baru") },
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isError = passwordError != null,
            supportingText = {
                if (passwordError != null) {
                    Text(text = passwordError!!, color = MaterialTheme.colorScheme.error)
                }
            },
            trailingIcon = {
                val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = "Toggle Password")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (errorMessage != null) {
            Text(text = errorMessage!!, color = MaterialTheme.colorScheme.error, fontSize = 14.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(bottom = 16.dp))
        }
        if (successMessage != null) {
            Text(text = successMessage!!, color = Color(0xFF4CAF50), fontSize = 14.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 16.dp))
        }

        Button(
            onClick = {
                if (!UserData.isRegistered()) {
                    errorMessage = "Belum ada user yang terdaftar di aplikasi."
                } else if (username == UserData.username && email == UserData.email) {
                    // REVISI: Cek validasi password baru di sini
                    if (!isPasswordValid(newPassword)) {
                        passwordError = "Password minimal 8 karakter, mengandung huruf & simbol."
                    } else {
                        UserData.password = newPassword
                        successMessage = "Password berhasil diubah! Mengalihkan..."

                        coroutineScope.launch {
                            delay(1500)
                            onBackToLoginClick()
                        }
                    }
                } else {
                    errorMessage = "Username atau Email tidak cocok."
                }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(text = "Reset Password", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = onBackToLoginClick) {
            Text(text = "Kembali ke Login", fontSize = 14.sp, color = MaterialTheme.colorScheme.secondary)
        }
    }
}
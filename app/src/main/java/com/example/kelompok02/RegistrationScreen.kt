package com.example.kelompok02

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    onRegistrationSuccess: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    // REVISI: State untuk menyimpan pesan error password
    var passwordError by remember { mutableStateOf<String?>(null) }

    // REVISI: Fungsi untuk validasi password
    fun isPasswordValid(pw: String): Boolean {
        val hasMinLength = pw.length >= 8
        val hasLetter = pw.any { it.isLetter() }
        val hasSymbol = pw.any { !it.isLetterOrDigit() && !it.isWhitespace() } // Bukan huruf, bukan angka, bukan spasi
        return hasMinLength && hasLetter && hasSymbol
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Tombol Back di Kiri Atas
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.Start).offset(x = (-12).dp)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Kembali",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Text(
            text = "Registrasi User",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 24.dp, top = 8.dp)
        )

        OutlinedTextField(value = firstName, onValueChange = { firstName = it }, label = { Text("First Name") }, singleLine = true, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(value = lastName, onValueChange = { lastName = it }, label = { Text("Last Name") }, singleLine = true, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(value = username, onValueChange = { username = it }, label = { Text("Username") }, singleLine = true, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email), modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(12.dp))

        // REVISI: Input Password yang sudah dimodifikasi untuk menampung error
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = null // Reset error saat user mulai mengetik lagi
            },
            label = { Text("Password") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isError = passwordError != null, // Akan merubah kotak jadi merah jika ada error
            supportingText = {
                if (passwordError != null) {
                    Text(text = passwordError!!, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        // Kurangi spacer sedikit karena supportingText sudah memakan ruang
        Spacer(modifier = Modifier.height(8.dp))

        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Identitas Tambahan", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.secondary, modifier = Modifier.align(Alignment.Start).padding(bottom = 8.dp))

        OutlinedTextField(value = phoneNumber, onValueChange = { phoneNumber = it }, label = { Text("Phone Number") }, singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone), modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(value = dateOfBirth, onValueChange = { }, label = { Text("Date of Birth") }, placeholder = { Text("DD/MM/YYYY") }, singleLine = true, readOnly = true, trailingIcon = { IconButton(onClick = { showDatePicker = true }) { Icon(imageVector = Icons.Filled.DateRange, contentDescription = "Pilih Tanggal") } }, modifier = Modifier.fillMaxWidth().clickable { showDatePicker = true })
        Spacer(modifier = Modifier.height(12.dp))

        if (showDatePicker) {
            DatePickerDialog(onDismissRequest = { showDatePicker = false }, confirmButton = { TextButton(onClick = { showDatePicker = false; datePickerState.selectedDateMillis?.let { millis -> val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()); dateOfBirth = formatter.format(Date(millis)) } }) { Text("OK") } }, dismissButton = { TextButton(onClick = { showDatePicker = false }) { Text("Batal") } }) { DatePicker(state = datePickerState) }
        }

        OutlinedTextField(value = address, onValueChange = { address = it }, label = { Text("Address") }, maxLines = 3, modifier = Modifier.fillMaxWidth().height(100.dp))
        Spacer(modifier = Modifier.height(28.dp))

        // Tombol Save
        Button(
            onClick = {
                // REVISI: Cek dulu apakah password valid sebelum menyimpan
                if (!isPasswordValid(password)) {
                    passwordError = "Password minimal 8 karakter, mengandung huruf & simbol (contoh: @, #, !)."
                    return@Button // Berhenti di sini, jangan lanjut simpan data
                }

                // Jika lolos validasi, simpan data
                UserData.firstName = firstName
                UserData.lastName = lastName
                UserData.username = username
                UserData.email = email
                UserData.password = password
                UserData.phoneNumber = phoneNumber
                UserData.dateOfBirth = dateOfBirth
                UserData.address = address

                onRegistrationSuccess()
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Save", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Sudah punya akun?", fontSize = 14.sp)
            TextButton(onClick = onLoginClick) { Text(text = "Login di sini", fontWeight = FontWeight.Bold) }
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}
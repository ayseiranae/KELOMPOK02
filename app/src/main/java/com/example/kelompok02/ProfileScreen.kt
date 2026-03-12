package com.example.kelompok02

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onLogoutClick: () -> Unit = {}, // REVISI: Tambah aksi navigasi logout
    onEditProfileClick: () -> Unit = {} // REVISI: Tambah aksi navigasi ke custom profile
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Profile",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        ProfileImage(oneEditClick = onEditProfileClick) // Diha nambah ini

        // Card berisi data user
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ProfileItem(label = "First Name", value = UserData.firstName)
                HorizontalDivider()
                ProfileItem(label = "Last Name", value = UserData.lastName)
                HorizontalDivider()
                ProfileItem(label = "Username", value = UserData.username)
                HorizontalDivider()
                ProfileItem(label = "Email", value = UserData.email)
                HorizontalDivider()
                ProfileItem(label = "Phone Number", value = UserData.phoneNumber)
                HorizontalDivider()
                ProfileItem(label = "Date of Birth", value = UserData.dateOfBirth)
                HorizontalDivider()
                ProfileItem(label = "Address", value = UserData.address)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // REVISI: Menambahkan tombol Logout sesuai assignment
        Button(
            onClick = onLogoutClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
            Text(
                text = "Logout",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onError
            )
        }
    }
}

@Composable
private fun ProfileItem(label: String, value: String) {
    Column {
        Text(
            text = label,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value.ifBlank { "-" },
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
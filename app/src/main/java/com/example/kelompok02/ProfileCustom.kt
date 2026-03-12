package com.example.kelompok02

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kelompok02.ui.theme.KELOMPOK02Theme

@Composable
fun ProfileCustomScreen(
    onBackClick: () -> Unit = {}
) {
    // States for customization - Initialized from UserData
    var hairEnabled by remember { mutableStateOf(UserData.hairEnabled) }
    var shirtEnabled by remember { mutableStateOf(UserData.shirtEnabled) }
    var pantsEnabled by remember { mutableStateOf(UserData.pantsEnabled) }
    var shoesEnabled by remember { mutableStateOf(UserData.shoesEnabled) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Header with Back Button (Fixed at top)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = "Custom Avatar",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        // Content area (Centered)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 64.dp, bottom = 140.dp) // Offset for header and footer
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Avatar Display Box
            Box(
                modifier = Modifier
                    .size(320.dp)
                    .background(Color(0xFF8BC34A))
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                // LAYER 3 (Bottom)
                AvatarPart(resId = R.drawable.arm_right, x = 65, y = 0, size = 80, alpha = 1f)
                AvatarPart(resId = R.drawable.arm_left, x = -65, y = 0, size = 80, alpha = 1f)
                AvatarPart(resId = R.drawable.leg_right, x = 33, y = 85, size = 70, alpha = 1f)
                AvatarPart(resId = R.drawable.leg_left, x = -33, y = 85, size = 70, alpha = 1f)

                // LAYER 2 (Middle)
                AvatarPart(resId = R.drawable.head, x = 0, y = -80, size = 100, alpha = 1f)
                AvatarPart(resId = R.drawable.hair, x = 1, y = -57, size = 180, alpha = if (hairEnabled) 1f else 0f)
                AvatarPart(resId = R.drawable.armshorter_right, x = 50, y = -7, size = 50, alpha = if (shirtEnabled) 1f else 0f)
                AvatarPart(resId = R.drawable.armshorter_left, x = -50, y = -7, size = 50, alpha = if (shirtEnabled) 1f else 0f)
                AvatarPart(resId = R.drawable.pantsshorter_right, x = 30, y = 80, size = 50, alpha = if (pantsEnabled) 1f else 0f)
                AvatarPart(resId = R.drawable.pantsshorter_left, x = -30, y = 80, size = 50, alpha = if (pantsEnabled) 1f else 0f)

                // LAYER 1 (Top)
                AvatarPart(resId = R.drawable.face, x = 0, y = -75, size = 50, alpha = 1f)
                AvatarPart(resId = R.drawable.neck, x = 0, y = -27, size = 60, alpha = 1f)
                AvatarPart(resId = R.drawable.shirt, x = 0, y = 20, size = 110, alpha = 1f)
                AvatarPart(resId = R.drawable.hand_right, x = 100, y = 30, size = 30, alpha = 1f)
                AvatarPart(resId = R.drawable.hand_left, x = -100, y = 30, size = 30, alpha = 1f)
                AvatarPart(resId = R.drawable.pants, x = 0, y = 75, size = 95, alpha = 1f)
                AvatarPart(resId = R.drawable.shoe_right, x = 50, y = 125, size = 45, alpha = if (shoesEnabled) 1f else 0f)
                AvatarPart(resId = R.drawable.shoe_left, x = -50, y = 125, size = 45, alpha = if (shoesEnabled) 1f else 0f)
            }
        }

        // Bottom Controls Container
        Surface(
            modifier = Modifier.align(Alignment.BottomCenter),
            shadowElevation = 8.dp,
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Checkbox Options - Horizontal
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomOption(modifier = Modifier.weight(1f), label = "Rambut", checked = hairEnabled, onCheckedChange = { hairEnabled = it })
                    CustomOption(modifier = Modifier.weight(1f), label = "Baju", checked = shirtEnabled, onCheckedChange = { shirtEnabled = it })
                    CustomOption(modifier = Modifier.weight(1f), label = "Celana", checked = pantsEnabled, onCheckedChange = { pantsEnabled = it })
                    CustomOption(modifier = Modifier.weight(1f), label = "Sepatu", checked = shoesEnabled, onCheckedChange = { shoesEnabled = it })
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Save Button
                Button(
                    onClick = {
                        // SAVE TO USER DATA
                        UserData.hairEnabled = hairEnabled
                        UserData.shirtEnabled = shirtEnabled
                        UserData.pantsEnabled = pantsEnabled
                        UserData.shoesEnabled = shoesEnabled
                        onBackClick()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = "Save",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun AvatarPart(resId: Int, x: Int, y: Int, size: Int, alpha: Float) {
    Image(
        painter = painterResource(id = resId),
        contentDescription = null,
        modifier = Modifier
            .offset(x = x.dp, y = y.dp)
            .size(size.dp),
        contentScale = ContentScale.Fit,
        alpha = alpha
    )
}

@Composable
fun CustomOption(modifier: Modifier = Modifier, label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(vertical = 4.dp)
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary)
        )
        Text(
            text = label,
            fontSize = 11.sp, // Reduced font size to fit
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileCustomPreview() {
    KELOMPOK02Theme {
        ProfileCustomScreen()
    }
}

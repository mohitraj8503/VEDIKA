package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import com.example.viewmodel.Screen
import com.example.viewmodel.VedikaViewModel

@Composable
fun BookingConfirmedScreen(
    viewModel: VedikaViewModel,
    uiState: com.example.viewmodel.VedikaUiState,
    modifier: Modifier = Modifier
) {
    val latestBooking = uiState.bookings.firstOrNull() ?: com.example.data.VedikaData.initialBookings.first()

    Scaffold(containerColor = VedikaCream) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Animated Sacred Checkmark Emblem
            Surface(
                shape = CircleShape,
                color = VedikaGreenLight,
                border = BorderStroke(3.dp, VedikaGreen),
                modifier = Modifier.size(110.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Success",
                        tint = VedikaGreen,
                        modifier = Modifier.size(60.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Headline
            Text(
                text = "Booking Confirmed!",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = VedikaTextDark,
                    fontSize = 26.sp
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Your ${latestBooking.serviceTitle} is scheduled successfully.",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = VedikaTextMedium,
                    fontSize = 15.sp
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Booking Details Card
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = VedikaSurface,
                border = BorderStroke(1.dp, VedikaCardBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(22.dp)) {
                    Text(
                        text = "Booking Details",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = VedikaTextDark,
                            fontSize = 19.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    DetailItem(
                        icon = Icons.Default.CalendarMonth,
                        text = "${latestBooking.date} • ${latestBooking.time}"
                    )
                    DetailItem(
                        icon = Icons.Default.Person,
                        text = latestBooking.panditName
                    )
                    DetailItem(
                        icon = Icons.Default.LocationOn,
                        text = latestBooking.location
                    )
                    DetailItem(
                        icon = Icons.Default.ConfirmationNumber,
                        text = "Booking ID: #${latestBooking.id}"
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Action Buttons (52dp height)
            Button(
                onClick = { viewModel.setBottomNavTab("Bookings") },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = VedikaOrange),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Text("View Details", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = { viewModel.navigateTo(Screen.Chat) },
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.5.dp, VedikaOrange),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Icon(Icons.Default.Chat, contentDescription = null, tint = VedikaOrange, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Chat with Pandit", fontWeight = FontWeight.Bold, color = VedikaOrange, fontSize = 15.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            TextButton(
                onClick = { viewModel.setBottomNavTab("Home") }
            ) {
                Text("Back to Home", color = VedikaTextMedium, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }
        }
    }
}

@Composable
private fun DetailItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String
) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = VedikaOrange,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Medium,
                color = VedikaTextDark,
                fontSize = 15.sp
            )
        )
    }
}

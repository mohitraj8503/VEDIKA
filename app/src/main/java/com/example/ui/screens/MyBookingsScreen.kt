package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.BookingRecord
import com.example.model.BookingStatus
import com.example.ui.components.ServiceVisualArt
import com.example.ui.components.VedikaBottomBar
import com.example.ui.theme.*
import com.example.viewmodel.Screen
import com.example.viewmodel.VedikaViewModel

@Composable
fun MyBookingsScreen(
    viewModel: VedikaViewModel,
    uiState: com.example.viewmodel.VedikaUiState,
    modifier: Modifier = Modifier
) {
    var selectedStatusTab by remember { mutableStateOf("Upcoming") }

    val filteredBookings = remember(selectedStatusTab, uiState.bookings) {
        when (selectedStatusTab) {
            "Upcoming" -> uiState.bookings.filter {
                it.status == BookingStatus.CONFIRMED || it.status == BookingStatus.PENDING
            }
            "Completed" -> uiState.bookings.filter { it.status == BookingStatus.COMPLETED }
            "Cancelled" -> uiState.bookings.filter { it.status == BookingStatus.CANCELLED }
            else -> uiState.bookings
        }
    }

    Scaffold(
        containerColor = VedikaCream,
        topBar = {
            Surface(color = VedikaCream, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "My Bookings",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = VedikaTextDark,
                        fontSize = 26.sp
                    ),
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 14.dp)
                )
            }
        },
        bottomBar = {
            VedikaBottomBar(
                currentTab = uiState.activeTab,
                onTabSelected = { viewModel.setBottomNavTab(it) }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp)
        ) {
            // Segmented Tabs: [Upcoming] | [Completed] | [Cancelled]
            item {
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = VedikaCreamDark,
                    border = BorderStroke(1.dp, VedikaCardBorder),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(4.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        listOf("Upcoming", "Completed", "Cancelled").forEach { tab ->
                            val isSelected = selectedStatusTab == tab
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(if (isSelected) VedikaOrange else Color.Transparent)
                                    .clickable { selectedStatusTab = tab },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = tab,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold,
                                    fontSize = 14.sp,
                                    color = if (isSelected) Color.White else VedikaTextMedium
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(18.dp))
            }

            // Booking Cards
            if (filteredBookings.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 48.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.EventBusy,
                            contentDescription = null,
                            tint = VedikaTextMuted,
                            modifier = Modifier.size(54.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "No $selectedStatusTab bookings found",
                            style = MaterialTheme.typography.bodyLarge.copy(color = VedikaTextMedium, fontSize = 15.sp)
                        )
                    }
                }
            } else {
                items(filteredBookings) { booking ->
                    BookingItemCard(
                        booking = booking,
                        onViewDetails = { },
                        onChatClick = { viewModel.navigateTo(Screen.Chat) },
                        onPayNowClick = {
                            viewModel.proceedToPayment()
                        },
                        onCancelClick = { viewModel.cancelBooking(booking.id) }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun BookingItemCard(
    booking: BookingRecord,
    onViewDetails: () -> Unit,
    onChatClick: () -> Unit,
    onPayNowClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = VedikaSurface,
        border = BorderStroke(1.dp, VedikaCardBorder),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(3.dp, RoundedCornerShape(20.dp), spotColor = Color(0x0C000000))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Authentic Vedic Ritual Artwork
                Box(
                    modifier = Modifier
                        .size(76.dp)
                        .clip(RoundedCornerShape(14.dp))
                ) {
                    ServiceVisualArt(
                        serviceTitle = booking.serviceTitle,
                        modifier = Modifier.fillMaxSize(),
                        cornerRadius = 14.dp
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = booking.serviceTitle,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = VedikaTextDark,
                                fontSize = 17.sp
                            )
                        )

                        // Status Badge
                        StatusBadge(status = booking.status)
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "${booking.date} • ${booking.time}",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = VedikaTextDark,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        )
                    )

                    Text(
                        text = "${booking.panditName} • ${booking.location}",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = VedikaTextMuted,
                            fontSize = 12.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))
            HorizontalDivider(color = VedikaDivider, thickness = 0.8.dp)
            Spacer(modifier = Modifier.height(12.dp))

            // Actions row (comfortably tappable)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = onViewDetails,
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, VedikaCardBorder),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                    modifier = Modifier.height(40.dp)
                ) {
                    Text(
                        text = "View Details",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = VedikaTextDark,
                            fontSize = 14.sp
                        )
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    if (booking.status == BookingStatus.PENDING) {
                        Button(
                            onClick = onPayNowClick,
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = VedikaOrange),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                            modifier = Modifier.height(40.dp)
                        ) {
                            Text("Pay Now", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        }
                    } else if (booking.status == BookingStatus.CONFIRMED) {
                        Button(
                            onClick = onChatClick,
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = VedikaOrange),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                            modifier = Modifier.height(40.dp)
                        ) {
                            Icon(Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Chat", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StatusBadge(status: BookingStatus) {
    val (bgColor, textColor) = when (status) {
        BookingStatus.CONFIRMED -> Pair(VedikaGreenLight, VedikaGreen)
        BookingStatus.PENDING -> Pair(Color(0xFFFFF8E1), VedikaGold)
        BookingStatus.COMPLETED -> Pair(Color(0xFFE0E0E0), Color(0xFF616161))
        BookingStatus.CANCELLED -> Pair(Color(0xFFFFEBEE), VedikaRed)
    }

    Surface(
        shape = RoundedCornerShape(8.dp),
        color = bgColor
    ) {
        Text(
            text = status.displayName.uppercase(),
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.ExtraBold,
                color = textColor,
                fontSize = 11.sp,
                letterSpacing = 0.5.sp
            ),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

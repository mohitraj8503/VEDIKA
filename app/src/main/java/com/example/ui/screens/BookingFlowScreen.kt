package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import com.example.viewmodel.Screen
import com.example.viewmodel.VedikaViewModel

data class DateItem(
    val date: String,
    val dayName: String
)

@Composable
fun BookingFlowScreen(
    viewModel: VedikaViewModel,
    uiState: com.example.viewmodel.VedikaUiState,
    modifier: Modifier = Modifier
) {
    val draft = uiState.bookingDraft
    val service = draft.service ?: com.example.data.VedikaData.services.first()

    val dates = listOf(
        DateItem("24", "Fri"),
        DateItem("25", "Sat"),
        DateItem("26", "Sun"),
        DateItem("27", "Mon"),
        DateItem("28", "Tue"),
        DateItem("29", "Wed"),
        DateItem("30", "Thu")
    )

    val times = listOf("6:00 AM", "8:00 AM", "10:00 AM", "12:00 PM", "4:00 PM", "6:00 PM")

    var isInterCityToggle by remember { mutableStateOf(draft.isInterCity) }
    var originCity by remember { mutableStateOf(draft.originCity) }

    Scaffold(
        containerColor = VedikaCream,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { viewModel.navigateTo(Screen.ServiceDetail) }, modifier = Modifier.size(48.dp)) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = VedikaTextDark,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Book ${service.title}",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = VedikaTextDark,
                        fontSize = 20.sp
                    )
                )
            }
        },
        bottomBar = {
            Surface(
                color = VedikaSurface,
                shadowElevation = 14.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        viewModel.setInterCityOptions(
                            isInterCity = isInterCityToggle,
                            origin = originCity,
                            destination = uiState.selectedCity
                        )
                        viewModel.proceedToPayment()
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = VedikaOrange),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 14.dp)
                        .height(54.dp)
                ) {
                    Text(
                        text = "Continue",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 14.dp)
        ) {
            // Step Indicator: 1 Details -> 2 Date & Time -> 3 Payment
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StepBubble(stepNumber = "1", label = "Details", isActive = true)
                    HorizontalDivider(modifier = Modifier.weight(1f), color = VedikaOrange, thickness = 2.dp)
                    StepBubble(stepNumber = "2", label = "Date & Time", isActive = true)
                    HorizontalDivider(modifier = Modifier.weight(1f), color = VedikaCardBorder, thickness = 2.dp)
                    StepBubble(stepNumber = "3", label = "Payment", isActive = false)
                }
                Spacer(modifier = Modifier.height(18.dp))
            }

            // Select Date Header
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Select Date",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = VedikaTextDark,
                            fontSize = 19.sp
                        )
                    )
                    Text(
                        text = draft.selectedMonthYear,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = VedikaOrangeDark,
                            fontSize = 14.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            // Large Date horizontal cards (comfortably tappable)
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(dates) { item ->
                        val isSelected = draft.selectedDate == item.date
                        Surface(
                            onClick = { viewModel.selectBookingDate(item.date, item.dayName) },
                            shape = RoundedCornerShape(16.dp),
                            color = if (isSelected) VedikaOrange else VedikaSurface,
                            border = BorderStroke(1.dp, if (isSelected) VedikaOrange else VedikaCardBorder),
                            modifier = Modifier
                                .width(62.dp)
                                .height(78.dp)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = item.date,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 20.sp,
                                    color = if (isSelected) Color.White else VedikaTextDark
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = item.dayName,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = if (isSelected) Color.White else VedikaTextMedium
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(22.dp))
            }

            // Select Time Header
            item {
                Text(
                    text = "Select Time",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = VedikaTextDark,
                        fontSize = 19.sp
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            // Large Time Slots Grid
            item {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    val chunks = times.chunked(3)
                    chunks.forEach { rowTimes ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            rowTimes.forEach { slot ->
                                val isSelected = draft.selectedTime == slot
                                Surface(
                                    onClick = { viewModel.selectBookingTime(slot) },
                                    shape = RoundedCornerShape(14.dp),
                                    color = if (isSelected) VedikaOrange else VedikaSurface,
                                    border = BorderStroke(1.dp, if (isSelected) VedikaOrange else VedikaCardBorder),
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(48.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text(
                                            text = slot,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold,
                                            fontSize = 14.sp,
                                            color = if (isSelected) Color.White else VedikaTextDark
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(22.dp))
            }

            // Inter-City Travel Options
            item {
                Surface(
                    shape = RoundedCornerShape(18.dp),
                    color = VedikaSurface,
                    border = BorderStroke(1.dp, VedikaCardBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.DirectionsTransit,
                                    contentDescription = null,
                                    tint = VedikaOrange,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "Inter-City Pandit Travel",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = VedikaTextDark,
                                        fontSize = 16.sp
                                    )
                                )
                            }
                            Switch(
                                checked = isInterCityToggle,
                                onCheckedChange = { isInterCityToggle = it },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = VedikaOrange
                                )
                            )
                        }

                        if (isInterCityToggle) {
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Request a specialized Pandit from sacred hubs (Deoghar, Varanasi, Gaya). Includes travel coordination.",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = VedikaTextMedium,
                                    fontSize = 13.sp
                                )
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                listOf("Deoghar", "Varanasi", "Gaya").forEach { city ->
                                    val isChosen = originCity == city
                                    FilterChip(
                                        selected = isChosen,
                                        onClick = { originCity = city },
                                        label = { Text("From $city", fontSize = 13.sp, fontWeight = FontWeight.SemiBold) },
                                        colors = FilterChipDefaults.filterChipColors(
                                            selectedContainerColor = VedikaSaffronLight,
                                            selectedLabelColor = VedikaOrangeDark
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(22.dp))
            }

            // Puja Location Selector (Home vs Temple / Other)
            item {
                Text(
                    text = "Puja Location",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = VedikaTextDark,
                        fontSize = 19.sp
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    LocationTypeChip(
                        label = "My Home",
                        icon = Icons.Default.Home,
                        isSelected = draft.isHomeLocation,
                        onClick = { viewModel.setPujaLocationType(true) },
                        modifier = Modifier.weight(1f)
                    )
                    LocationTypeChip(
                        label = "Temple / Other",
                        icon = Icons.Default.Place,
                        isSelected = !draft.isHomeLocation,
                        onClick = { viewModel.setPujaLocationType(false) },
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(18.dp))
            }

            // Address Box
            item {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = VedikaSurface,
                    border = BorderStroke(1.dp, VedikaCardBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Service Address",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = VedikaTextMuted,
                                    fontSize = 12.sp
                                )
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = draft.address,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = VedikaTextDark,
                                    fontSize = 16.sp
                                )
                            )
                        }
                        Text(
                            text = "Change",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = VedikaOrange,
                                fontSize = 14.sp
                            ),
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StepBubble(
    stepNumber: String,
    label: String,
    isActive: Boolean
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            shape = CircleShape,
            color = if (isActive) VedikaOrange else VedikaCreamDark,
            border = BorderStroke(1.dp, if (isActive) VedikaOrange else VedikaCardBorder),
            modifier = Modifier.size(32.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = stepNumber,
                    color = if (isActive) Color.White else VedikaTextMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = if (isActive) FontWeight.Bold else FontWeight.Medium,
            color = if (isActive) VedikaTextDark else VedikaTextMuted
        )
    }
}

@Composable
private fun LocationTypeChip(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(14.dp),
        color = if (isSelected) VedikaSaffronLight else VedikaSurface,
        border = BorderStroke(1.5.dp, if (isSelected) VedikaOrange else VedikaCardBorder),
        modifier = modifier.height(52.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isSelected) VedikaOrange else VedikaTextMedium,
                modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = label,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                fontSize = 14.sp,
                color = if (isSelected) VedikaOrangeDark else VedikaTextDark
            )
        }
    }
}

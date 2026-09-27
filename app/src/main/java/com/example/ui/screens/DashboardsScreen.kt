package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.example.ui.components.VedikaBottomBar
import com.example.ui.components.VedikaNetworkImage
import com.example.ui.theme.*
import com.example.viewmodel.Screen
import com.example.viewmodel.VedikaViewModel

@Composable
fun ProviderDashboardScreen(
    viewModel: VedikaViewModel,
    uiState: com.example.viewmodel.VedikaUiState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        containerColor = VedikaCream,
        topBar = {
            Surface(color = VedikaCream, modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { viewModel.setBottomNavTab("Profile") }, modifier = Modifier.size(48.dp)) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = VedikaTextDark,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Text(
                        text = "Pandit Dashboard",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = VedikaTextDark,
                            fontSize = 20.sp
                        )
                    )
                }
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
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp)
        ) {
            // Header Profile Card with Real Photo
            item {
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = VedikaSurface,
                    border = BorderStroke(1.dp, VedikaCardBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(18.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(RoundedCornerShape(16.dp))
                        ) {
                            VedikaNetworkImage(
                                imageUrl = "https://images.unsplash.com/photo-1532375810709-75b1da00537c?auto=format&fit=crop&w=400&q=80",
                                contentDescription = "Acharya Vishnu Sharma",
                                modifier = Modifier.fillMaxSize(),
                                cornerRadius = 16.dp
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = "Namaste,",
                                style = MaterialTheme.typography.bodySmall.copy(color = VedikaTextMedium, fontSize = 13.sp)
                            )
                            Text(
                                text = "Acharya Vishnu Sharma",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = VedikaTextDark,
                                    fontSize = 17.sp
                                )
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Verified,
                                    contentDescription = null,
                                    tint = VedikaGreen,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Verified Pandit",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = VedikaGreen,
                                        fontSize = 12.sp
                                    )
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(18.dp))
            }

            // Metrics row: 12 Total Bookings, 5 Upcoming, ₹ 28,400 Total Earnings
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    MetricBox(
                        value = "12",
                        label = "Total Bookings",
                        modifier = Modifier.weight(1f)
                    )
                    MetricBox(
                        value = "5",
                        label = "Upcoming",
                        modifier = Modifier.weight(1f)
                    )
                    MetricBox(
                        value = "₹ 28,400",
                        label = "Total Earnings",
                        modifier = Modifier.weight(1.3f)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

            // Quick Actions List
            item {
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = VedikaSurface,
                    border = BorderStroke(1.dp, VedikaCardBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(vertical = 6.dp)) {
                        DashboardActionItem(
                            icon = Icons.Default.Inventory,
                            title = "Manage Services",
                            onClick = { }
                        )
                        HorizontalDivider(color = VedikaDivider, thickness = 0.6.dp)

                        DashboardActionItem(
                            icon = Icons.Default.EventAvailable,
                            title = "My Availability",
                            onClick = { }
                        )
                        HorizontalDivider(color = VedikaDivider, thickness = 0.6.dp)

                        DashboardActionItem(
                            icon = Icons.Default.Chat,
                            title = "Chat with Users",
                            onClick = { viewModel.navigateTo(Screen.Chat) }
                        )
                        HorizontalDivider(color = VedikaDivider, thickness = 0.6.dp)

                        DashboardActionItem(
                            icon = Icons.Default.Star,
                            title = "Reviews & Ratings",
                            onClick = { }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AdminDashboardScreen(
    viewModel: VedikaViewModel,
    uiState: com.example.viewmodel.VedikaUiState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        containerColor = VedikaCream,
        topBar = {
            Surface(color = VedikaCream, modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { viewModel.setBottomNavTab("Profile") }, modifier = Modifier.size(48.dp)) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = VedikaTextDark,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Text(
                        text = "Admin Dashboard",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = VedikaTextDark,
                            fontSize = 20.sp
                        )
                    )
                }
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
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp)
        ) {
            // Stats Grid 2x2
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    AdminStatCard(
                        value = "1,248",
                        label = "Total Users",
                        growth = "+12%",
                        modifier = Modifier.weight(1f)
                    )
                    AdminStatCard(
                        value = "320",
                        label = "Pandits",
                        growth = "+0%",
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    AdminStatCard(
                        value = "856",
                        label = "Bookings",
                        growth = "+10%",
                        modifier = Modifier.weight(1f)
                    )
                    AdminStatCard(
                        value = "₹ 12.4L",
                        label = "Revenue",
                        growth = "+25%",
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(22.dp))
            }

            // Recent Bookings Header
            item {
                Text(
                    text = "Recent Bookings",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = VedikaTextDark,
                        fontSize = 19.sp
                    ),
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }

            items(uiState.bookings) { booking ->
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = VedikaSurface,
                    border = BorderStroke(1.dp, VedikaCardBorder),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = booking.serviceTitle,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = VedikaTextDark,
                                    fontSize = 15.sp
                                )
                            )
                            Text(
                                text = "${booking.panditName} • ${booking.date}",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = VedikaTextMuted,
                                    fontSize = 12.sp
                                )
                            )
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "₹ ${booking.totalAmount}",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                    color = VedikaOrangeDark,
                                    fontSize = 16.sp
                                )
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            StatusBadge(status = booking.status)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MetricBox(
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = VedikaSurface,
        border = BorderStroke(1.dp, VedikaCardBorder),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(vertical = 14.dp, horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = VedikaTextDark,
                    fontSize = 19.sp
                )
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall.copy(
                    color = VedikaTextMuted,
                    fontSize = 11.sp
                )
            )
        }
    }
}

@Composable
private fun AdminStatCard(
    value: String,
    label: String,
    growth: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(18.dp),
        color = VedikaSurface,
        border = BorderStroke(1.dp, VedikaCardBorder),
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = value,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = VedikaTextDark,
                        fontSize = 22.sp
                    )
                )
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = VedikaGreenLight
                ) {
                    Text(
                        text = growth,
                        color = VedikaGreen,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium.copy(color = VedikaTextMuted, fontSize = 13.sp)
            )
        }
    }
}

@Composable
private fun DashboardActionItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = VedikaOrange,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.SemiBold,
                color = VedikaTextDark,
                fontSize = 15.sp
            ),
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = VedikaTextMuted,
            modifier = Modifier.size(22.dp)
        )
    }
}

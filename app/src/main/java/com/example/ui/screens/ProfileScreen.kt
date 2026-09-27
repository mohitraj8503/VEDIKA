package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.VedikaBottomBar
import com.example.ui.theme.*
import com.example.viewmodel.Screen
import com.example.viewmodel.VedikaViewModel

@Composable
fun ProfileScreen(
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
                        .padding(horizontal = 20.dp, vertical = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "My Profile",
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = VedikaTextDark,
                            fontSize = 26.sp
                        )
                    )

                    IconButton(onClick = { viewModel.navigateTo(Screen.MoreMenu) }, modifier = Modifier.size(48.dp)) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "More Menu",
                            tint = VedikaOrange,
                            modifier = Modifier.size(26.dp)
                        )
                    }
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
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp)
        ) {
            // User Info Header Card with Real Portrait
            item {
                Surface(
                    shape = RoundedCornerShape(22.dp),
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
                                .size(70.dp)
                                .clip(CircleShape)
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = listOf(VedikaOrange, VedikaGold)
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = uiState.userName.firstOrNull()?.toString() ?: "U",
                                style = MaterialTheme.typography.headlineMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontSize = 28.sp
                                )
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = uiState.userName,
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = VedikaTextDark,
                                    fontSize = 19.sp
                                )
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = uiState.userPhone,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = VedikaTextMedium,
                                    fontSize = 14.sp
                                )
                            )
                            Text(
                                text = uiState.userEmail,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = VedikaTextMuted,
                                    fontSize = 13.sp
                                )
                            )
                        }

                        IconButton(onClick = { }, modifier = Modifier.size(44.dp)) {
                            Icon(Icons.Default.Edit, contentDescription = "Edit Profile", tint = VedikaOrange, modifier = Modifier.size(20.dp))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(18.dp))
            }

            // Menu Items List (Larger typography, no emojis)
            item {
                Surface(
                    shape = RoundedCornerShape(22.dp),
                    color = VedikaSurface,
                    border = BorderStroke(1.dp, VedikaCardBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(vertical = 8.dp)) {
                        ProfileMenuItem(
                            icon = Icons.Default.CalendarMonth,
                            title = "My Bookings",
                            onClick = { viewModel.setBottomNavTab("Bookings") }
                        )
                        HorizontalDivider(color = VedikaDivider, thickness = 0.8.dp)

                        ProfileMenuItem(
                            icon = Icons.Default.FavoriteBorder,
                            title = "Saved Pandits",
                            onClick = { viewModel.navigateTo(Screen.PanditBooking) }
                        )
                        HorizontalDivider(color = VedikaDivider, thickness = 0.8.dp)

                        ProfileMenuItem(
                            icon = Icons.Default.CalendarMonth,
                            title = if (uiState.isHindi) "दैनिक पंचांग एवं शुभ मुहूर्त" else "Daily Panchang & Muhurat",
                            onClick = { viewModel.navigateTo(Screen.PanchangMuhurat) }
                        )
                        HorizontalDivider(color = VedikaDivider, thickness = 0.8.dp)

                        ProfileMenuItem(
                            icon = Icons.Default.SelfImprovement,
                            title = if (uiState.isHindi) "मंत्र जप एवं साधना (108 Mala)" else "Mantra Japa & Sadhana",
                            onClick = { viewModel.navigateTo(Screen.MantraJapa) }
                        )
                        HorizontalDivider(color = VedikaDivider, thickness = 0.8.dp)

                        ProfileMenuItem(
                            icon = Icons.Default.LocationOn,
                            title = "City: ${uiState.selectedCity} (Change)",
                            onClick = { viewModel.setBottomNavTab("Home") }
                        )
                        HorizontalDivider(color = VedikaDivider, thickness = 0.8.dp)

                        ProfileMenuItem(
                            icon = Icons.Default.Translate,
                            title = if (uiState.isHindi) "भाषा: हिन्दी (Tap to switch to English)" else "Language: English (हिन्दी में बदलें)",
                            onClick = { viewModel.toggleLanguage() }
                        )
                        HorizontalDivider(color = VedikaDivider, thickness = 0.8.dp)

                        ProfileMenuItem(
                            icon = Icons.Default.Notifications,
                            title = "Notifications",
                            onClick = { viewModel.navigateTo(Screen.Notifications) }
                        )
                        HorizontalDivider(color = VedikaDivider, thickness = 0.8.dp)

                        ProfileMenuItem(
                            icon = Icons.Default.Headphones,
                            title = "Vedic Support & Helpdesk",
                            onClick = { viewModel.navigateTo(Screen.Chat) }
                        )
                        HorizontalDivider(color = VedikaDivider, thickness = 0.8.dp)

                        ProfileMenuItem(
                            icon = Icons.Default.Settings,
                            title = "More Options & Vedic Calendar",
                            onClick = { viewModel.navigateTo(Screen.MoreMenu) }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

            // "Become a Pandit" Banner Card
            item {
                Surface(
                    onClick = { viewModel.navigateTo(Screen.ProviderDashboard) },
                    shape = RoundedCornerShape(20.dp),
                    color = VedikaSaffronLight,
                    border = BorderStroke(1.dp, VedikaOrange.copy(alpha = 0.3f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(18.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = VedikaOrange,
                            modifier = Modifier.size(48.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.PersonAdd,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Become a Pandit",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = VedikaOrangeDark,
                                    fontSize = 17.sp
                                )
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Join our verified pandit network & manage bookings.",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = VedikaTextMedium,
                                    fontSize = 13.sp
                                )
                            )
                        }
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = VedikaOrangeDark,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(18.dp))
            }

            // Quick App Switchers (Provider & Admin Portals)
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = { viewModel.navigateTo(Screen.ProviderDashboard) },
                        shape = RoundedCornerShape(14.dp),
                        border = BorderStroke(1.dp, VedikaCardBorder),
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                    ) {
                        Text("Provider View", color = VedikaTextDark, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                    }

                    OutlinedButton(
                        onClick = { viewModel.navigateTo(Screen.AdminDashboard) },
                        shape = RoundedCornerShape(14.dp),
                        border = BorderStroke(1.dp, VedikaCardBorder),
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                    ) {
                        Text("Admin View", color = VedikaTextDark, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileMenuItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 18.dp, vertical = 16.dp),
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
                fontSize = 16.sp
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

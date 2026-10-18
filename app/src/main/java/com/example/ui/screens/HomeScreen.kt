package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.data.VedikaData
import com.example.model.ServiceItem
import com.example.ui.components.*
import com.example.ui.theme.*
import com.example.viewmodel.Screen
import com.example.viewmodel.VedikaUiState
import com.example.viewmodel.VedikaViewModel

@Composable
fun HomeScreen(
    viewModel: VedikaViewModel,
    uiState: VedikaUiState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        containerColor = VedikaCream,
        topBar = {
            VedikaHomeTopBar(
                currentCity = uiState.selectedCity,
                onCitySelected = { viewModel.selectCity(it) },
                onNotificationClick = { viewModel.navigateTo(Screen.Notifications) },
                hasUnreadNotifications = uiState.notifications.any { it.isUnread },
                isHindi = uiState.isHindi,
                onToggleLanguage = { viewModel.toggleLanguage() }
            )
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
            contentPadding = PaddingValues(bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 1. Personalized Greeting Header with Devotee Portrait
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Good Morning\n${uiState.userName.split(" ").firstOrNull() ?: "Mohit"} 👋",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = VedikaTextDark,
                                fontSize = 26.sp,
                                lineHeight = 32.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "May your day be filled with\npeace and positivity ✨",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = VedikaTextMuted,
                                fontSize = 13.sp,
                                lineHeight = 18.sp
                            )
                        )
                    }

                    // Avatar with golden border & halo
                    Surface(
                        onClick = { viewModel.setBottomNavTab("Profile") },
                        shape = CircleShape,
                        color = Color(0xFFFFF3E0),
                        border = BorderStroke(2.dp, VedikaGold),
                        modifier = Modifier
                            .size(54.dp)
                            .shadow(4.dp, CircleShape, spotColor = Color(0x20000000))
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data("https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?auto=format&fit=crop&w=300&q=80")
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "Profile",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }

            // 2. Search Bar with Mic & Filter Icons
            item {
                Surface(
                    onClick = { viewModel.navigateTo(Screen.Search) },
                    shape = RoundedCornerShape(20.dp),
                    color = VedikaSurface,
                    border = BorderStroke(1.dp, VedikaCardBorder),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .height(54.dp)
                        .shadow(3.dp, RoundedCornerShape(20.dp), spotColor = Color(0x0C000000))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = VedikaTextMuted,
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Search puja, temple, pandit...",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = VedikaTextMuted,
                                fontSize = 14.sp
                            ),
                            modifier = Modifier.weight(1f)
                        )
                        // Mic icon
                        Icon(
                            imageVector = Icons.Default.Mic,
                            contentDescription = "Voice Search",
                            tint = VedikaTextMuted,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        // Sliders / filter icon
                        Icon(
                            imageVector = Icons.Default.Tune,
                            contentDescription = "Filter",
                            tint = VedikaOrangeDark,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            // 3. Golden Temple Promo Banner Card
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    Surface(
                        onClick = { viewModel.openServiceDetail("s1") },
                        shape = RoundedCornerShape(22.dp),
                        color = Color(0xFFFFF7ED),
                        border = BorderStroke(1.dp, Color(0xFFFFE0B2)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(6.dp, RoundedCornerShape(22.dp), spotColor = Color(0x18000000))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(175.dp)
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(
                                            Color(0xFFFFFAF2),
                                            Color(0xFFFFF3E0),
                                            Color(0xFFFFE6CC)
                                        )
                                    )
                                )
                        ) {
                            // Right side temple visual art illustration
                            Box(
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .fillMaxHeight()
                                    .width(170.dp)
                                    .padding(end = 4.dp, top = 8.dp, bottom = 8.dp)
                                    .clip(RoundedCornerShape(18.dp))
                            ) {
                                TempleVisualArt(
                                    templeName = "Baba Baidyanath Dham",
                                    modifier = Modifier.fillMaxSize(),
                                    cornerRadius = 18.dp
                                )
                            }

                            // Left side banner copy & "Book Now →" button
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth(0.62f)
                                    .padding(start = 18.dp, top = 18.dp, bottom = 18.dp),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(
                                        text = "Book Authentic\nPuja Services",
                                        style = MaterialTheme.typography.titleLarge.copy(
                                            fontWeight = FontWeight.ExtraBold,
                                            color = VedikaTextDark,
                                            fontSize = 20.sp,
                                            lineHeight = 25.sp
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = "From trusted pandits, right at your home or at sacred temples.",
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            color = VedikaTextMedium,
                                            fontSize = 12.sp,
                                            lineHeight = 16.sp
                                        ),
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }

                                // Orange gradient pill button
                                Box(
                                    modifier = Modifier
                                        .height(38.dp)
                                        .shadow(4.dp, RoundedCornerShape(19.dp), spotColor = Color(0x30FF6B00))
                                        .clip(RoundedCornerShape(19.dp))
                                        .background(
                                            brush = Brush.horizontalGradient(
                                                colors = listOf(Color(0xFFFF6D00), Color(0xFFFF4500))
                                            )
                                        )
                                        .clickable { viewModel.openServiceDetail("s1") }
                                        .padding(horizontal = 16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = "Book Now",
                                            style = MaterialTheme.typography.labelMedium.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = Color.White,
                                                fontSize = 13.sp
                                            )
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = "→",
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White,
                                            fontSize = 13.sp
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // Carousel dots below promo banner
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(7.dp)
                                .clip(CircleShape)
                                .background(VedikaOrange)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        repeat(3) {
                            Box(
                                modifier = Modifier
                                    .size(5.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFD6CBC0))
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                        }
                    }
                }
            }

            // 4. Quick Actions 2×4 Grid (8 rounded icon cards)
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    // Row 1: Puja Booking, Pandit Ji, Temple Darshan, Panchang
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        MockupActionIcon(
                            icon = Icons.Outlined.LocalFlorist,
                            label = "Puja Booking",
                            onClick = { viewModel.setBottomNavTab("Explore") }
                        )
                        MockupActionIcon(
                            icon = Icons.Outlined.Person,
                            label = "Pandit Ji",
                            onClick = { viewModel.navigateTo(Screen.PanditBooking) }
                        )
                        MockupActionIcon(
                            icon = Icons.Outlined.TempleHindu,
                            label = "Temple Darshan",
                            onClick = { viewModel.navigateTo(Screen.TempleDarshan) }
                        )
                        MockupActionIcon(
                            icon = Icons.Outlined.DateRange,
                            label = "Panchang",
                            onClick = { viewModel.setBottomNavTab("Panchang") }
                        )
                    }

                    // Row 2: Mantra Jaap, Aarti & Bhajan, Spiritual Articles, Festivals/Donate
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        MockupActionIcon(
                            icon = Icons.Outlined.SelfImprovement,
                            label = "Mantra Jaap",
                            onClick = { viewModel.navigateTo(Screen.MantraJapa) }
                        )
                        MockupActionIcon(
                            icon = Icons.Outlined.MenuBook,
                            label = "Spiritual Articles",
                            onClick = { viewModel.navigateTo(Screen.KathaBhajan) }
                        )
                        MockupActionIcon(
                            icon = Icons.Outlined.Festival,
                            label = "Festivals",
                            onClick = { viewModel.navigateTo(Screen.PujaSamagri) }
                        )
                        MockupActionIcon(
                            icon = Icons.Outlined.VolunteerActivism,
                            label = "Donate",
                            onClick = { viewModel.navigateTo(Screen.MoreMenu) }
                        )
                    }
                }
            }

            // 5. Popular Pujas Section Header with "View All →"
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Popular Pujas",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = VedikaTextDark,
                            fontSize = 18.sp
                        )
                    )

                    Row(
                        modifier = Modifier
                            .clickable { viewModel.setBottomNavTab("Explore") }
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "View All",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = VedikaOrangeDark,
                                fontSize = 13.sp
                            )
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = "→",
                            fontWeight = FontWeight.Bold,
                            color = VedikaOrangeDark,
                            fontSize = 13.sp
                        )
                    }
                }
            }

            // 6. Popular Puja Card (Satyanarayan Puja, Griha Pravesh)
            items(VedikaData.services.take(3)) { service ->
                PopularPujaCard(
                    service = service,
                    onClick = { viewModel.openServiceDetail(service.id) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
            }
        }
    }
}

@Composable
private fun MockupActionIcon(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(76.dp)
            .clickable(onClick = onClick)
    ) {
        Surface(
            shape = RoundedCornerShape(18.dp),
            color = Color(0xFFFFF2E6),
            border = BorderStroke(1.dp, Color(0xFFFFDEC2)),
            modifier = Modifier.size(56.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = VedikaOrangeDark,
                    modifier = Modifier.size(26.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Medium,
                color = VedikaTextDark,
                fontSize = 11.sp,
                lineHeight = 14.sp
            ),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            maxLines = 2
        )
    }
}

@Composable
private fun PopularPujaCard(
    service: ServiceItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(18.dp),
        color = VedikaSurface,
        border = BorderStroke(1.dp, VedikaCardBorder),
        modifier = modifier.shadow(3.dp, RoundedCornerShape(18.dp), spotColor = Color(0x0E000000))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Deity / Puja artwork thumbnail
            Box(
                modifier = Modifier
                    .size(68.dp)
                    .clip(RoundedCornerShape(14.dp))
            ) {
                ServiceVisualArt(
                    serviceTitle = service.title,
                    modifier = Modifier.fillMaxSize(),
                    cornerRadius = 14.dp
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            // Details
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = service.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = VedikaTextDark,
                        fontSize = 16.sp
                    )
                )
                Spacer(modifier = Modifier.height(2.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Rating",
                        tint = VedikaStarAmber,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "${service.rating} (${if (service.reviewCount >= 1000) String.format("%.1fk", service.reviewCount / 1000.0) else "${service.reviewCount}"} bookings)",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = VedikaTextMuted,
                            fontSize = 12.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = service.shortDescription,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = VedikaTextMedium,
                        fontSize = 12.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "₹${service.price}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = VedikaOrangeDark,
                        fontSize = 16.sp
                    )
                )
            }

            // Right chevron button
            Surface(
                shape = CircleShape,
                color = Color(0xFFFFF2E6),
                modifier = Modifier.size(32.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Open",
                        tint = VedikaOrangeDark,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

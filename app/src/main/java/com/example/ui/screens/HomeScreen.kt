package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.data.VedikaData
import com.example.model.FestivalEvent
import com.example.model.PanditProfile
import com.example.model.ServiceCategory
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
            contentPadding = PaddingValues(bottom = 36.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 1. Personalized Greeting with Large Headline
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
                            text = if (uiState.isHindi) "नमस्ते, ${uiState.userName.split(" ").firstOrNull() ?: ""}" else "Namaste, ${uiState.userName.split(" ").firstOrNull() ?: ""}",
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = VedikaOrangeDark,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = if (uiState.isHindi) "प्रामाणिक वैदिक सेवाएं\nएवं आचार्य बुकिंग" else "Divine Blessings\nAt Your Doorstep",
                            style = MaterialTheme.typography.headlineLarge.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = VedikaTextDark,
                                fontSize = 28.sp,
                                lineHeight = 36.sp
                            )
                        )
                    }

                    // Direct Profile Entry Avatar
                    Surface(
                        onClick = { viewModel.setBottomNavTab("Profile") },
                        shape = CircleShape,
                        color = VedikaCreamDark,
                        border = BorderStroke(2.dp, VedikaGold),
                        modifier = Modifier
                            .size(52.dp)
                            .shadow(3.dp, CircleShape, spotColor = Color(0x15000000))
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = uiState.userName.firstOrNull()?.toString() ?: "U",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = VedikaOrangeDark,
                                    fontSize = 20.sp
                                )
                            )
                        }
                    }
                }
            }

            // 2. Large Attractive Search Bar (Touch target 58dp)
            item {
                Surface(
                    onClick = { viewModel.navigateTo(Screen.Search) },
                    shape = RoundedCornerShape(20.dp),
                    color = VedikaSurface,
                    border = BorderStroke(1.2.dp, VedikaCardBorder),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .height(58.dp)
                        .shadow(4.dp, RoundedCornerShape(20.dp), spotColor = Color(0x10000000))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 18.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = VedikaOrange,
                            modifier = Modifier.size(26.dp)
                        )
                        Spacer(modifier = Modifier.width(14.dp))
                        Text(
                            text = if (uiState.isHindi) "पंडित, पूजा, मंदिर दर्शन, कथा खोजें..." else "Search for Pandit, Puja, Temple, Katha...",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = VedikaTextMuted,
                                fontSize = 15.sp
                            ),
                            modifier = Modifier.weight(1f)
                        )
                        Surface(
                            shape = CircleShape,
                            color = VedikaSaffronLight,
                            modifier = Modifier.size(34.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Tune,
                                    contentDescription = "Filter",
                                    tint = VedikaOrangeDark,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                }
            }

            // 3. Four Prominent Quick-Action Cards (Panchang, Japa Mala, Near Me, Temple Darshan)
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    HomeQuickActionCard(
                        title = if (uiState.isHindi) "पंचांग" else "Panchang",
                        subtitle = if (uiState.isHindi) "शुभ मुहूर्त" else "Muhurat",
                        icon = Icons.Default.CalendarMonth,
                        accentColor = VedikaOrange,
                        onClick = { viewModel.navigateTo(Screen.PanchangMuhurat) },
                        modifier = Modifier.weight(1f)
                    )
                    HomeQuickActionCard(
                        title = if (uiState.isHindi) "मंत्र जप" else "Jap Mala",
                        subtitle = if (uiState.isHindi) "108 मनका" else "108 Chants",
                        icon = Icons.Default.SelfImprovement,
                        accentColor = VedikaGold,
                        onClick = { viewModel.navigateTo(Screen.MantraJapa) },
                        modifier = Modifier.weight(1f)
                    )
                    HomeQuickActionCard(
                        title = if (uiState.isHindi) "नजदीकी" else "Near Me",
                        subtitle = if (uiState.isHindi) "आचार्य मैप" else "Pandit Map",
                        icon = Icons.Default.Place,
                        accentColor = VedikaMaroon,
                        onClick = { viewModel.navigateTo(Screen.NearbyMap) },
                        modifier = Modifier.weight(1f)
                    )
                    HomeQuickActionCard(
                        title = if (uiState.isHindi) "दर्शन" else "Darshan",
                        subtitle = if (uiState.isHindi) "VIP पास" else "VIP Pass",
                        icon = Icons.Default.TempleHindu,
                        accentColor = VedikaOrangeDark,
                        onClick = { viewModel.navigateTo(Screen.TempleDarshan) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // 4. Large Today's Panchang & Shubh Muhurat Card
            item {
                Surface(
                    onClick = { viewModel.navigateTo(Screen.PanchangMuhurat) },
                    shape = RoundedCornerShape(22.dp),
                    color = VedikaSurface,
                    border = BorderStroke(1.2.dp, VedikaCardBorder),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .shadow(4.dp, RoundedCornerShape(22.dp), spotColor = Color(0x10000000))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(Color(0xFFFFF9EE), Color(0xFFFFFFFF))
                                )
                            )
                            .padding(18.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Surface(
                                    shape = CircleShape,
                                    color = VedikaSaffronLight,
                                    modifier = Modifier.size(38.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Icon(
                                            imageVector = Icons.Default.WbSunny,
                                            contentDescription = null,
                                            tint = VedikaOrangeDark,
                                            modifier = Modifier.size(22.dp)
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(
                                        text = if (uiState.isHindi) "आज का पंचांग एवं शुभ मुहूर्त" else "Today's Panchang & Muhurat",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp,
                                        color = VedikaTextDark
                                    )
                                    Text(
                                        text = "${VedikaData.todayPanchang.dateString} • ${uiState.selectedCity}",
                                        fontSize = 13.sp,
                                        color = VedikaTextMedium
                                    )
                                }
                            }

                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = Color(0xFFE8F5E9)
                            ) {
                                Text(
                                    text = "अभिजित मुहूर्त",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    color = Color(0xFF2E7D32),
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))
                        HorizontalDivider(color = VedikaDivider, thickness = 0.8.dp)
                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "तिथि: ${VedikaData.todayPanchang.tithi}",
                                    fontSize = 14.sp,
                                    color = VedikaTextDark,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "नक्षत्र: ${VedikaData.todayPanchang.nakshatra} • ${VedikaData.todayPanchang.yoga}",
                                    fontSize = 12.sp,
                                    color = VedikaTextMedium
                                )
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(VedikaSaffronLight)
                                    .padding(horizontal = 10.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = "View All",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = VedikaOrangeDark
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = null,
                                    tint = VedikaOrangeDark,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
            }

            // 5. Majestic Full-Width Promotional Devotional Hero Banner (Real Photography)
            item {
                Surface(
                    onClick = { viewModel.startBooking("s1") },
                    shape = RoundedCornerShape(24.dp),
                    color = VedikaSurface,
                    border = BorderStroke(1.dp, VedikaCardBorder),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .shadow(6.dp, RoundedCornerShape(24.dp), spotColor = Color(0x18000000))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(210.dp)
                    ) {
                        // Real High-Resolution Devotional Photo
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("https://images.unsplash.com/photo-1609137144813-7d9921338f24?auto=format&fit=crop&w=1200&q=80")
                                .crossfade(true)
                                .build(),
                            contentDescription = "Auspicious Puja",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                        // Rich spiritual gradient overlay for pristine text contrast
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(
                                            Color(0xF59A2E00),
                                            Color(0xD9C2410C),
                                            Color(0x70000000)
                                        )
                                    )
                                )
                        )

                        // Banner Content
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(22.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = Color(0x33FFFFFF)
                                ) {
                                    Text(
                                        text = "AUSPICIOUS VEDIC CEREMONIES",
                                        color = Color(0xFFFFF9C4),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = "Bring Divine Grace\n& Peace To Your Home",
                                    style = MaterialTheme.typography.titleLarge.copy(
                                        fontWeight = FontWeight.ExtraBold,
                                        color = Color.White,
                                        fontSize = 24.sp,
                                        lineHeight = 31.sp
                                    )
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Verified Purohits with Complete Pure Samagri Kit",
                                    color = Color(0xFFFFF3E0),
                                    fontSize = 13.sp
                                )
                            }

                            Surface(
                                shape = RoundedCornerShape(16.dp),
                                color = Color.White,
                                modifier = Modifier.height(44.dp)
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Book Griha Pravesh",
                                        style = MaterialTheme.typography.labelLarge.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = VedikaOrangeDark,
                                            fontSize = 15.sp
                                        )
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                        contentDescription = null,
                                        tint = VedikaOrangeDark,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // 6. Section Heading: Explore Categories
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (uiState.isHindi) "श्रेणियां खोजें" else "Explore Categories",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = VedikaTextDark,
                            fontSize = 21.sp
                        )
                    )
                    Text(
                        text = if (uiState.isHindi) "सभी देखें" else "View All",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = VedikaOrange,
                            fontSize = 15.sp
                        ),
                        modifier = Modifier
                            .clickable { viewModel.navigateTo(Screen.Explore) }
                            .padding(4.dp)
                    )
                }
            }

            // Categories Grid (8 Custom Tiles with Comfortable Spacing)
            item {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    val rows = VedikaData.categories.chunked(4)
                    rows.forEach { rowCategories ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            rowCategories.forEach { category ->
                                CategoryIconTile(
                                    category = category,
                                    onClick = {
                                        when (category.id) {
                                            "1" -> viewModel.navigateTo(Screen.PanditBooking)
                                            "3" -> viewModel.navigateTo(Screen.KathaBhajan)
                                            "4" -> viewModel.navigateTo(Screen.TempleDarshan)
                                            "7" -> viewModel.navigateTo(Screen.PujaSamagri)
                                            else -> {
                                                viewModel.selectCategory(category.name)
                                                viewModel.navigateTo(Screen.Explore)
                                            }
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }

            // 7. Popular Services Section
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (uiState.isHindi) "लोकप्रिय पूजा सेवाएं" else "Popular Services",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = VedikaTextDark,
                            fontSize = 21.sp
                        )
                    )
                    Text(
                        text = if (uiState.isHindi) "सभी देखें" else "See All",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = VedikaOrange,
                            fontSize = 15.sp
                        ),
                        modifier = Modifier
                            .clickable { viewModel.navigateTo(Screen.Explore) }
                            .padding(4.dp)
                    )
                }
            }

            // Popular Services Horizontal Carousel (Large Photo Cards)
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(VedikaData.services) { service ->
                        PopularServicePhotoCard(
                            service = service,
                            isFavorite = uiState.favoriteServiceIds.contains(service.id),
                            onFavoriteClick = { viewModel.toggleFavoriteService(service.id) },
                            onClick = { viewModel.openServiceDetail(service.id) },
                            onBookClick = { viewModel.startBooking(service.id) }
                        )
                    }
                }
            }

            // 8. Recommended Vedic Pandits Section (With Real Photos, Ratings, Experience)
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = if (uiState.isHindi) "अनुशंसित वैदिक आचार्य" else "Recommended Pandits",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = VedikaTextDark,
                                fontSize = 21.sp
                            )
                        )
                        Text(
                            text = "Verified Gurukula Scholars & Purohits",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = VedikaTextMedium,
                                fontSize = 13.sp
                            )
                        )
                    }
                    Text(
                        text = "View All",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = VedikaOrange,
                            fontSize = 15.sp
                        ),
                        modifier = Modifier
                            .clickable { viewModel.navigateTo(Screen.PanditBooking) }
                            .padding(4.dp)
                    )
                }
            }

            // Pandits Horizontal Carousel
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(VedikaData.pandits) { pandit ->
                        PanditHomeCard(
                            pandit = pandit,
                            onBookClick = {
                                viewModel.startBooking("s1")
                            },
                            onClick = {
                                viewModel.navigateTo(Screen.PanditBooking)
                            }
                        )
                    }
                }
            }

            // 9. Upcoming Festivals & Vrats Section
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (uiState.isHindi) "आगामी पर्व एवं त्योहार" else "Upcoming Festivals",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = VedikaTextDark,
                            fontSize = 21.sp
                        )
                    )
                    Text(
                        text = "Calendar",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = VedikaOrange,
                            fontSize = 15.sp
                        ),
                        modifier = Modifier
                            .clickable { viewModel.navigateTo(Screen.PanchangMuhurat) }
                            .padding(4.dp)
                    )
                }
            }

            items(VedikaData.upcomingFestivals.take(2)) { festival ->
                HomeFestivalCard(
                    festival = festival,
                    onBookClick = {
                        viewModel.startBooking(festival.recommendedPujaId)
                    }
                )
            }

            // 10. Inter-City Booking Highlight Card
            item {
                Surface(
                    onClick = { viewModel.navigateTo(Screen.PanditBooking) },
                    shape = RoundedCornerShape(22.dp),
                    color = VedikaSurface,
                    border = BorderStroke(1.2.dp, VedikaCardBorder),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .shadow(4.dp, RoundedCornerShape(22.dp), spotColor = Color(0x10000000))
                ) {
                    Row(
                        modifier = Modifier.padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = VedikaSaffronLight,
                            modifier = Modifier.size(56.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.DirectionsTransit,
                                    contentDescription = null,
                                    tint = VedikaOrange,
                                    modifier = Modifier.size(28.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Inter-City Pandit Booking",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = VedikaTextDark,
                                    fontSize = 17.sp
                                )
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Need a priest from Deoghar or Varanasi for your town? We coordinate travel and stay.",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = VedikaTextMedium,
                                    fontSize = 13.sp,
                                    lineHeight = 18.sp
                                )
                            )
                        }
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = VedikaOrange,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HomeQuickActionCard(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    accentColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(18.dp),
        color = VedikaSurface,
        border = BorderStroke(1.2.dp, VedikaCardBorder),
        modifier = modifier
            .height(84.dp)
            .shadow(3.dp, RoundedCornerShape(18.dp), spotColor = Color(0x0C000000))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp, vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Surface(
                shape = CircleShape,
                color = accentColor.copy(alpha = 0.12f),
                modifier = Modifier.size(34.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        tint = accentColor,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = VedikaTextDark,
                    fontSize = 13.sp
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = VedikaTextMuted,
                    fontSize = 10.sp
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun CategoryIconTile(
    category: ServiceCategory,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(84.dp)
            .clickable(onClick = onClick)
            .padding(vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = VedikaCreamDark,
            border = BorderStroke(1.dp, VedikaCardBorder),
            modifier = Modifier.size(64.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                CategoryVectorIcon(
                    iconType = category.iconType,
                    tint = VedikaOrangeDark,
                    modifier = Modifier.size(34.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = category.name,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = VedikaTextDark,
                fontSize = 13.sp,
                lineHeight = 16.sp
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}

@Composable
fun PopularServicePhotoCard(
    service: ServiceItem,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onClick: () -> Unit,
    onBookClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(22.dp),
        color = VedikaSurface,
        border = BorderStroke(1.2.dp, VedikaCardBorder),
        modifier = Modifier
            .width(260.dp)
            .shadow(5.dp, RoundedCornerShape(22.dp), spotColor = Color(0x12000000))
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(155.dp)
            ) {
                // Real Authentic Vedic Service Visual
                VedikaServiceImage(
                    service = service,
                    modifier = Modifier.fillMaxSize(),
                    cornerRadius = 22.dp
                )

                // Favorite icon button on top right (touch target 48dp)
                IconButton(
                    onClick = onFavoriteClick,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(Color(0xD9FFFFFF))
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) VedikaRed else VedikaTextDark,
                        modifier = Modifier.size(22.dp)
                    )
                }

                // Verified Badge on bottom left
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = VedikaSurface,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 9.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = VedikaAmber,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${service.rating} (${service.reviewCount})",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = VedikaTextDark,
                                fontSize = 12.sp
                            )
                        )
                    }
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = service.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = VedikaTextDark,
                        fontSize = 18.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${service.durationHours} • ${service.panditCount} Pandit",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = VedikaTextMedium,
                        fontSize = 13.sp
                    )
                )
                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "₹ ${service.price}",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = VedikaOrangeDark,
                                fontSize = 20.sp
                            )
                        )
                        Text(
                            text = "onwards",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = VedikaTextMuted,
                                fontSize = 11.sp
                            )
                        )
                    }
                    Button(
                        onClick = onBookClick,
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = VedikaOrange),
                        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
                        modifier = Modifier.height(42.dp)
                    ) {
                        Text("Book", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun PanditHomeCard(
    pandit: PanditProfile,
    onBookClick: () -> Unit,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(22.dp),
        color = VedikaSurface,
        border = BorderStroke(1.2.dp, VedikaCardBorder),
        modifier = Modifier
            .width(240.dp)
            .shadow(4.dp, RoundedCornerShape(22.dp), spotColor = Color(0x10000000))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(62.dp)
                        .clip(CircleShape)
                ) {
                    VedikaPanditImage(
                        pandit = pandit,
                        modifier = Modifier.fillMaxSize(),
                        cornerRadius = 31.dp
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = pandit.name,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = VedikaTextDark,
                                fontSize = 16.sp
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.Default.Verified,
                            contentDescription = "Verified",
                            tint = VedikaOrange,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    Text(
                        text = "${pandit.experienceYears}+ Yrs Exp",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = VedikaTextMedium,
                            fontSize = 12.sp
                        )
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = VedikaAmber,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = "${pandit.rating} (${pandit.reviewCount})",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = VedikaTextDark,
                                fontSize = 12.sp
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = pandit.specializations.joinToString(" • "),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = VedikaTextMedium,
                    fontSize = 12.sp
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "₹ ${pandit.basePrice}",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = VedikaOrangeDark,
                            fontSize = 17.sp
                        )
                    )
                    Text(
                        text = "Dakshina",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = VedikaTextMuted,
                            fontSize = 11.sp
                        )
                    )
                }
                Button(
                    onClick = onBookClick,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = VedikaOrange),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                    modifier = Modifier.height(38.dp)
                ) {
                    Text("Select", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }
            }
        }
    }
}

@Composable
fun HomeFestivalCard(
    festival: FestivalEvent,
    onBookClick: () -> Unit
) {
    Surface(
        onClick = onBookClick,
        shape = RoundedCornerShape(20.dp),
        color = VedikaSurface,
        border = BorderStroke(1.dp, VedikaCardBorder),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .shadow(3.dp, RoundedCornerShape(20.dp), spotColor = Color(0x0C000000))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = VedikaSaffronLight,
                modifier = Modifier.size(54.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Celebration,
                        contentDescription = null,
                        tint = VedikaOrangeDark,
                        modifier = Modifier.size(26.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = festival.name,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = VedikaTextDark,
                            fontSize = 16.sp
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = VedikaAmber
                    ) {
                        Text(
                            text = "In ${festival.daysRemaining}d",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "${festival.date} • ${festival.tithi}",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = VedikaTextMedium,
                        fontSize = 12.sp
                    )
                )
            }
            Button(
                onClick = onBookClick,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = VedikaOrange),
                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
                modifier = Modifier.height(36.dp)
            ) {
                Text("Book", fontWeight = FontWeight.Bold, fontSize = 13.sp)
            }
        }
    }
}

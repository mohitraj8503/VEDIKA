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
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.VedikaData
import com.example.model.PanditProfile
import com.example.ui.components.VedikaBottomBar
import com.example.ui.components.VedikaChildTopBar
import com.example.ui.components.VedikaPanditImage
import com.example.ui.theme.*
import com.example.viewmodel.Screen
import com.example.viewmodel.VedikaViewModel

@Composable
fun PanditBookingScreen(
    viewModel: VedikaViewModel,
    uiState: com.example.viewmodel.VedikaUiState,
    modifier: Modifier = Modifier
) {
    val filterTabs = listOf("All", "Nearby", "Top Rated", "Price: Low to High")
    var selectedFilter by remember { mutableStateOf("All") }

    val sortedPandits = remember(selectedFilter) {
        when (selectedFilter) {
            "Nearby" -> VedikaData.pandits.sortedBy { it.distanceKm }
            "Top Rated" -> VedikaData.pandits.sortedByDescending { it.rating }
            "Price: Low to High" -> VedikaData.pandits.sortedBy { it.basePrice }
            else -> VedikaData.pandits
        }
    }

    Scaffold(
        containerColor = VedikaCream,
        topBar = {
            VedikaChildTopBar(
                title = if (uiState.isHindi) "पंडित एवं आचार्य बुकिंग" else "Book Verified Pandits",
                onBackClick = { viewModel.navigateTo(Screen.Home) },
                actions = {
                    IconButton(onClick = { viewModel.navigateTo(Screen.NearbyMap) }, modifier = Modifier.size(48.dp)) {
                        Icon(
                            imageVector = Icons.Default.Map,
                            contentDescription = "Map View",
                            tint = VedikaOrange,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
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
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // City / Inter-City Info Banner
            item {
                Surface(
                    shape = RoundedCornerShape(18.dp),
                    color = VedikaCreamDark,
                    border = BorderStroke(1.2.dp, VedikaCardBorder),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = VedikaSaffronLight,
                            modifier = Modifier.size(36.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.LocationOn,
                                    contentDescription = null,
                                    tint = VedikaOrangeDark,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Serving ${uiState.selectedCity} & Outstation",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = VedikaTextDark,
                                    fontSize = 14.sp
                                )
                            )
                            Text(
                                text = "Rigveda, Yajurveda & Gurukula certified purohits",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = VedikaTextMedium,
                                    fontSize = 12.sp
                                )
                            )
                        }
                    }
                }
            }

            // Filter Chips
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    items(filterTabs) { tab ->
                        val isSelected = selectedFilter == tab
                        Surface(
                            onClick = { selectedFilter = tab },
                            shape = RoundedCornerShape(20.dp),
                            color = if (isSelected) VedikaOrange else VedikaSurface,
                            border = BorderStroke(1.2.dp, if (isSelected) VedikaOrange else VedikaCardBorder),
                            modifier = Modifier.height(40.dp)
                        ) {
                            Box(
                                modifier = Modifier.padding(horizontal = 18.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = tab,
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        color = if (isSelected) Color.White else VedikaTextDark,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                        }
                    }
                }
            }

            // Pandit Listing Cards
            items(sortedPandits) { pandit ->
                PanditProfileCard(
                    pandit = pandit,
                    isFavorite = uiState.favoritePanditIds.contains(pandit.id),
                    onFavoriteClick = { viewModel.toggleFavoritePandit(pandit.id) },
                    onBookClick = {
                        viewModel.startBooking()
                    },
                    onChatClick = {
                        viewModel.navigateTo(Screen.Chat)
                    }
                )
            }
        }
    }
}

@Composable
fun PanditProfileCard(
    pandit: PanditProfile,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onBookClick: () -> Unit,
    onChatClick: () -> Unit
) {
    Surface(
        onClick = onBookClick,
        shape = RoundedCornerShape(22.dp),
        color = VedikaSurface,
        border = BorderStroke(1.2.dp, VedikaCardBorder),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .shadow(4.dp, RoundedCornerShape(22.dp), spotColor = Color(0x0E000000))
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(verticalAlignment = Alignment.Top) {
                // Authentic Vedic Acharya Portrait
                Box(
                    modifier = Modifier
                        .size(88.dp)
                        .clip(RoundedCornerShape(20.dp))
                ) {
                    VedikaPanditImage(
                        pandit = pandit,
                        modifier = Modifier.fillMaxSize(),
                        cornerRadius = 20.dp
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    // Name & Favorite
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = pandit.name,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = VedikaTextDark,
                                fontSize = 18.sp
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f)
                        )

                        IconButton(
                            onClick = onFavoriteClick,
                            modifier = Modifier.size(36.dp)
                        ) {
                            Icon(
                                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "Favorite",
                                tint = if (isFavorite) VedikaRed else VedikaTextMuted,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    }

                    // Verification Badge
                    if (pandit.isVerified) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 2.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Verified,
                                contentDescription = "Verified",
                                tint = VedikaGreen,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Verified Purohit",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = VedikaGreen,
                                    fontSize = 12.sp
                                )
                            )
                        }
                    }

                    // Rating & Reviews
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 2.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = VedikaAmber,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${pandit.rating} (${pandit.reviewCount} reviews)",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = VedikaTextDark,
                                fontSize = 13.sp
                            )
                        )
                    }

                    // Experience & City
                    Text(
                        text = "${pandit.experienceYears}+ Years Exp • ${pandit.city}",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = VedikaTextMedium,
                            fontSize = 13.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Bio preview
            Text(
                text = pandit.bio,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = VedikaTextMedium,
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Specialization tags
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                pandit.specializations.take(3).forEach { tag ->
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = VedikaCreamDark
                    ) {
                        Text(
                            text = tag,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = VedikaTextDark,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Price and Actions
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "₹ ${pandit.basePrice}",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = VedikaOrangeDark,
                            fontSize = 20.sp
                        )
                    )
                    Text(
                        text = "Dakshina onwards",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = VedikaTextMuted,
                            fontSize = 11.sp
                        )
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    OutlinedButton(
                        onClick = onChatClick,
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.2.dp, VedikaOrange),
                        modifier = Modifier.height(42.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ChatBubbleOutline,
                            contentDescription = "Chat",
                            tint = VedikaOrange,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Chat", color = VedikaOrangeDark, fontWeight = FontWeight.Bold)
                    }

                    Button(
                        onClick = onBookClick,
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = VedikaOrange),
                        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
                        modifier = Modifier.height(42.dp)
                    ) {
                        Text("Book Pandit", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                }
            }
        }
    }
}

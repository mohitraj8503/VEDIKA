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
import com.example.model.ServiceItem
import com.example.ui.components.VedikaBottomBar
import com.example.ui.components.VedikaHomeTopBar
import com.example.ui.components.VedikaServiceImage
import com.example.ui.theme.*
import com.example.viewmodel.Screen
import com.example.viewmodel.VedikaViewModel

@Composable
fun ExploreScreen(
    viewModel: VedikaViewModel,
    uiState: com.example.viewmodel.VedikaUiState,
    modifier: Modifier = Modifier
) {
    val filterTabs = listOf("All", "Puja & Havan", "Katha & Bhajan", "Sanskar", "Spiritual Experts")
    var selectedFilter by remember { mutableStateOf(if (uiState.selectedCategory != "All") uiState.selectedCategory else "All") }

    val filteredServices = remember(selectedFilter, uiState.searchQuery) {
        VedikaData.services.filter { service ->
            val matchesFilter = when (selectedFilter) {
                "All" -> true
                "Puja & Havan" -> service.category.contains("Puja") || service.category.contains("Havan")
                "Katha & Bhajan" -> service.category.contains("Katha") || service.title.contains("Katha")
                "Sanskar" -> service.category.contains("Sanskar")
                else -> true
            }
            val matchesSearch = if (uiState.searchQuery.isBlank()) true
            else service.title.contains(uiState.searchQuery, ignoreCase = true) ||
                    service.shortDescription.contains(uiState.searchQuery, ignoreCase = true)

            matchesFilter && matchesSearch
        }
    }

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
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Screen Title & Subtitle
            item {
                Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)) {
                    Text(
                        text = if (uiState.isHindi) "वैदिक सेवाएं खोजें" else "Explore Vedic Services",
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = VedikaTextDark,
                            fontSize = 28.sp
                        )
                    )
                    Text(
                        text = "Authentic ritual ceremonies performed by certified purohits",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = VedikaTextMedium,
                            fontSize = 14.sp
                        )
                    )
                }
            }

            // Search Bar (56dp height)
            item {
                OutlinedTextField(
                    value = uiState.searchQuery,
                    onValueChange = { viewModel.setSearchQuery(it) },
                    placeholder = { Text("Search by ritual name, pandit, or deity...", fontSize = 15.sp) },
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = null, tint = VedikaOrange, modifier = Modifier.size(24.dp))
                    },
                    trailingIcon = {
                        if (uiState.searchQuery.isNotEmpty()) {
                            IconButton(onClick = { viewModel.setSearchQuery("") }) {
                                Icon(Icons.Default.Close, contentDescription = "Clear", tint = VedikaTextMuted)
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = VedikaOrange,
                        unfocusedBorderColor = VedikaCardBorder,
                        focusedContainerColor = VedikaSurface,
                        unfocusedContainerColor = VedikaSurface
                    ),
                    singleLine = true
                )
            }

            // Filter Tabs Row
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
                            modifier = Modifier.height(42.dp)
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

            // Empty state if no results found
            if (filteredServices.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 48.dp, horizontal = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = VedikaSaffronLight,
                            modifier = Modifier.size(80.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.SearchOff,
                                    contentDescription = null,
                                    tint = VedikaOrange,
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "No Services Found",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = VedikaTextDark
                            )
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Try searching for 'Griha Pravesh', 'Satyanarayan', or 'Rudrabhishek'.",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = VedikaTextMedium
                            )
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                viewModel.setSearchQuery("")
                                selectedFilter = "All"
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = VedikaOrange),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Reset Filters", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            } else {
                items(filteredServices) { service ->
                    ExploreServiceRowCard(
                        service = service,
                        isFavorite = uiState.favoriteServiceIds.contains(service.id),
                        onFavoriteClick = { viewModel.toggleFavoriteService(service.id) },
                        onClick = { viewModel.openServiceDetail(service.id) },
                        onBookClick = { viewModel.startBooking(service.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun ExploreServiceRowCard(
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
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .shadow(4.dp, RoundedCornerShape(22.dp), spotColor = Color(0x0E000000))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Real Authentic Vedic Photography Thumbnail
            Box(
                modifier = Modifier
                    .size(112.dp)
                    .clip(RoundedCornerShape(18.dp))
            ) {
                VedikaServiceImage(
                    service = service,
                    modifier = Modifier.fillMaxSize(),
                    cornerRadius = 18.dp
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Details
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = service.title,
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

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = VedikaAmber,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${service.rating} (${service.reviewCount})",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = VedikaTextDark,
                            fontSize = 13.sp
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = VedikaSaffronLight
                    ) {
                        Text(
                            text = "${service.panditCount} Pandit",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = VedikaOrangeDark,
                                fontSize = 11.sp
                            ),
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = service.durationHours,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = VedikaTextMedium,
                        fontSize = 13.sp
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "₹ ${service.price}",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = VedikaOrangeDark,
                            fontSize = 19.sp
                        )
                    )

                    Button(
                        onClick = onBookClick,
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = VedikaOrange),
                        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 6.dp),
                        modifier = Modifier.height(38.dp)
                    ) {
                        Text("Book", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                }
            }
        }
    }
}

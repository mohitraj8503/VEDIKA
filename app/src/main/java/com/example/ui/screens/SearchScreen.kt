package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.VedikaData
import com.example.ui.theme.*
import com.example.viewmodel.Screen
import com.example.viewmodel.VedikaViewModel

@Composable
fun SearchScreen(
    viewModel: VedikaViewModel,
    uiState: com.example.viewmodel.VedikaUiState,
    modifier: Modifier = Modifier
) {
    var query by remember { mutableStateOf(uiState.searchQuery.ifBlank { "Satyanarayan Katha" }) }
    val filters = listOf("All", "Pandit", "Puja", "Temple", "Events")
    var selectedFilter by remember { mutableStateOf("All") }

    val popularSuggestions = listOf(
        "Pandit in Deoghar",
        "Pandit from Deoghar for Bhagalpur",
        "Griha Pravesh Puja",
        "Rudrabhishek",
        "Satyanarayan Katha",
        "Bhajan Kirtan",
        "Puja Samagri"
    )

    val searchResults = remember(query, selectedFilter) {
        val q = query.trim().lowercase()
        val matchingServices = VedikaData.services.filter {
            it.title.lowercase().contains(q) || it.category.lowercase().contains(q) || it.shortDescription.lowercase().contains(q)
        }
        val matchingPandits = VedikaData.pandits.filter {
            it.name.lowercase().contains(q) || it.specializations.any { s -> s.lowercase().contains(q) } || it.city.lowercase().contains(q)
        }
        Pair(matchingServices, matchingPandits)
    }

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
                    IconButton(onClick = { viewModel.setBottomNavTab("Home") }, modifier = Modifier.size(48.dp)) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = VedikaTextDark,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    OutlinedTextField(
                        value = query,
                        onValueChange = {
                            query = it
                            viewModel.setSearchQuery(it)
                        },
                        placeholder = { Text("Search for Pandit, Puja, Temple...", fontSize = 15.sp) },
                        leadingIcon = {
                            Icon(Icons.Default.Search, contentDescription = null, tint = VedikaOrange, modifier = Modifier.size(22.dp))
                        },
                        trailingIcon = {
                            if (query.isNotEmpty()) {
                                IconButton(onClick = {
                                    query = ""
                                    viewModel.setSearchQuery("")
                                }, modifier = Modifier.size(40.dp)) {
                                    Icon(Icons.Default.Close, contentDescription = "Clear", tint = VedikaTextMuted, modifier = Modifier.size(20.dp))
                                }
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(54.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = VedikaOrange,
                            unfocusedBorderColor = VedikaCardBorder,
                            focusedContainerColor = VedikaSurface,
                            unfocusedContainerColor = VedikaSurface
                        ),
                        singleLine = true
                    )
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp)
        ) {
            // Filter Pills
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(bottom = 12.dp)
                ) {
                    items(filters) { filter ->
                        val isSelected = selectedFilter == filter
                        Surface(
                            onClick = { selectedFilter = filter },
                            shape = RoundedCornerShape(20.dp),
                            color = if (isSelected) VedikaOrange else VedikaSurface,
                            border = BorderStroke(1.dp, if (isSelected) VedikaOrange else VedikaCardBorder),
                            modifier = Modifier.height(38.dp)
                        ) {
                            Box(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = filter,
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

            // Quick Suggestions Chips
            item {
                Text(
                    text = "Popular Searches",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = VedikaTextMuted
                    ),
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(vertical = 6.dp)
                ) {
                    items(popularSuggestions) { suggestion ->
                        Surface(
                            onClick = {
                                query = suggestion
                                viewModel.setSearchQuery(suggestion)
                            },
                            shape = RoundedCornerShape(14.dp),
                            color = VedikaCreamDark,
                            border = BorderStroke(1.dp, VedikaCardBorder)
                        ) {
                            Text(
                                text = suggestion,
                                fontSize = 12.sp,
                                color = VedikaTextDark,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            // Results Section
            val (services, pandits) = searchResults

            if (services.isNotEmpty()) {
                item {
                    Text(
                        text = "Matching Pujas & Rituals",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = VedikaTextDark
                        ),
                        modifier = Modifier.padding(vertical = 6.dp)
                    )
                }

                items(services) { service ->
                    ExploreServiceRowCard(
                        service = service,
                        isFavorite = uiState.favoriteServiceIds.contains(service.id),
                        onFavoriteClick = { viewModel.toggleFavoriteService(service.id) },
                        onClick = { viewModel.openServiceDetail(service.id) },
                        onBookClick = { viewModel.startBooking(service.id) }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            if (pandits.isNotEmpty()) {
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Verified Pandits",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = VedikaTextDark
                        ),
                        modifier = Modifier.padding(vertical = 6.dp)
                    )
                }

                items(pandits) { pandit ->
                    PanditProfileCard(
                        pandit = pandit,
                        isFavorite = uiState.favoritePanditIds.contains(pandit.id),
                        onFavoriteClick = { viewModel.toggleFavoritePandit(pandit.id) },
                        onBookClick = { viewModel.startBooking() },
                        onChatClick = { viewModel.navigateTo(Screen.Chat) }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

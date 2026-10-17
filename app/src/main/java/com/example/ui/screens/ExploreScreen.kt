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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
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
import com.example.ui.components.ServiceVisualArt
import com.example.ui.components.VedikaBottomBar
import com.example.ui.theme.*
import com.example.viewmodel.Screen
import com.example.viewmodel.VedikaUiState
import com.example.viewmodel.VedikaViewModel

@Composable
fun ExploreScreen(
    viewModel: VedikaViewModel,
    uiState: VedikaUiState,
    modifier: Modifier = Modifier
) {
    val categoryTabs = listOf("All", "Griha Puja", "Sanskar", "Temple Puja", "Special")
    var selectedCategory by remember { mutableStateOf("All") }

    val filteredServices = remember(selectedCategory) {
        if (selectedCategory == "All") {
            VedikaData.services
        } else {
            VedikaData.services.filter { it.category.equals(selectedCategory, ignoreCase = true) }
        }
    }

    Scaffold(
        containerColor = VedikaCream,
        topBar = {
            Surface(
                color = VedikaCream,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { viewModel.setBottomNavTab("Home") },
                            modifier = Modifier.size(40.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = VedikaTextDark,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        Text(
                            text = "Puja Services",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = VedikaTextDark,
                                fontSize = 20.sp
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 8.dp)
                        )

                        IconButton(
                            onClick = { viewModel.navigateTo(Screen.Search) },
                            modifier = Modifier.size(40.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                tint = VedikaTextDark,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = "Book trusted pandits for authentic rituals",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = VedikaTextMuted,
                            fontSize = 13.sp
                        ),
                        modifier = Modifier.padding(start = 48.dp)
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
            contentPadding = PaddingValues(bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Horizontal Category Filter Pills
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.padding(vertical = 6.dp)
                ) {
                    items(categoryTabs) { tab ->
                        val isSelected = selectedCategory == tab
                        Surface(
                            onClick = { selectedCategory = tab },
                            shape = RoundedCornerShape(20.dp),
                            color = if (isSelected) VedikaOrange else Color(0xFFF7F2EB),
                            border = BorderStroke(
                                1.dp,
                                if (isSelected) VedikaOrange else Color(0xFFEBE3D5)
                            ),
                            modifier = Modifier.height(38.dp)
                        ) {
                            Box(
                                modifier = Modifier.padding(horizontal = 18.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = tab,
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        color = if (isSelected) Color.White else VedikaTextMedium,
                                        fontSize = 13.sp
                                    )
                                )
                            }
                        }
                    }
                }
            }

            // List of Puja Service Cards
            items(filteredServices) { service ->
                PujaServiceMockupCard(
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
private fun PujaServiceMockupCard(
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
            // Rounded square deity / sacred artwork thumbnail
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(14.dp))
            ) {
                ServiceVisualArt(
                    serviceTitle = service.title,
                    modifier = Modifier.fillMaxSize(),
                    cornerRadius = 14.dp
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            // Info column
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = service.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = VedikaTextDark,
                        fontSize = 16.sp
                    )
                )

                Spacer(modifier = Modifier.height(3.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Rating",
                        tint = VedikaStarAmber,
                        modifier = Modifier.size(13.dp)
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

            // Right chevron icon button
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

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
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Videocam
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
import com.example.data.VedikaData
import com.example.model.TempleItem
import com.example.ui.components.VedikaBottomBar
import com.example.ui.components.VedikaChildTopBar
import com.example.ui.components.VedikaTempleImage
import com.example.ui.theme.*
import com.example.viewmodel.Screen
import com.example.viewmodel.VedikaViewModel

@Composable
fun TempleDarshanScreen(
    viewModel: VedikaViewModel,
    uiState: com.example.viewmodel.VedikaUiState,
    modifier: Modifier = Modifier
) {
    val filters = listOf("All Temples", "Jyotirlinga", "Devi Peeth", "Vishnu Dham", "VIP Passes")
    var selectedFilter by remember { mutableStateOf("All Temples") }

    Scaffold(
        containerColor = VedikaCream,
        topBar = {
            VedikaChildTopBar(
                title = if (uiState.isHindi) "पवित्र मंदिर दर्शन एवं VIP पास" else "Temple Darshan & Passes",
                onBackClick = { viewModel.navigateTo(Screen.Home) },
                actions = {
                    IconButton(onClick = { viewModel.navigateTo(Screen.Search) }, modifier = Modifier.size(48.dp)) {
                        Icon(Icons.Default.Search, contentDescription = "Search", tint = VedikaTextDark, modifier = Modifier.size(24.dp))
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Filter pills
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    items(filters) { filter ->
                        val isSelected = selectedFilter == filter
                        Surface(
                            onClick = { selectedFilter = filter },
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

            // Temple Cards
            items(VedikaData.temples) { temple ->
                TempleCard(
                    temple = temple,
                    onBookDarshan = {
                        viewModel.startBooking("s1")
                    }
                )
            }
        }
    }
}

@Composable
fun TempleCard(
    temple: TempleItem,
    onBookDarshan: () -> Unit
) {
    Surface(
        onClick = onBookDarshan,
        shape = RoundedCornerShape(24.dp),
        color = VedikaSurface,
        border = BorderStroke(1.2.dp, VedikaCardBorder),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .shadow(5.dp, RoundedCornerShape(24.dp), spotColor = Color(0x12000000))
    ) {
        Column {
            // Real Authentic Mandir Photography & Architecture
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                VedikaTempleImage(
                    temple = temple,
                    modifier = Modifier.fillMaxSize(),
                    cornerRadius = 24.dp
                )

                // Live Aarti Badge on top right
                if (temple.hasLiveAarti) {
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = Color(0xD9B91C1C),
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Videocam,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Live Darshan",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontSize = 11.sp
                                )
                            )
                        }
                    }
                }

                // Deity tag on bottom left
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xD9FFFFFF),
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(12.dp)
                ) {
                    Text(
                        text = temple.deity,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = VedikaTextDark,
                            fontSize = 12.sp
                        ),
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }
            }

            Column(modifier = Modifier.padding(18.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = temple.name,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = VedikaTextDark,
                            fontSize = 20.sp
                        )
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = VedikaAmber,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${temple.rating} (${temple.reviewCountString})",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = VedikaTextDark,
                                fontSize = 14.sp
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Place,
                        contentDescription = null,
                        tint = VedikaOrange,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${temple.location}, ${temple.state} • ${temple.timing}",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = VedikaTextMedium,
                            fontSize = 13.sp
                        )
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Tags Row & Action
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        temple.tags.take(2).forEach { tag ->
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = VedikaCreamDark
                            ) {
                                Text(
                                    text = tag,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = VedikaTextDark,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                )
                            }
                        }
                    }

                    Button(
                        onClick = onBookDarshan,
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = VedikaOrange),
                        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
                        modifier = Modifier.height(42.dp)
                    ) {
                        Text("Book VIP Pass", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                }
            }
        }
    }
}

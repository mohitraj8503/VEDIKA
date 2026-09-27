package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.VedikaData
import com.example.model.ServiceItem
import com.example.ui.components.VedikaServiceImage
import com.example.ui.theme.*
import com.example.viewmodel.Screen
import com.example.viewmodel.VedikaViewModel

@Composable
fun ServiceDetailScreen(
    viewModel: VedikaViewModel,
    uiState: com.example.viewmodel.VedikaUiState,
    modifier: Modifier = Modifier
) {
    val service = remember(uiState.selectedServiceId) {
        VedikaData.services.find { it.id == uiState.selectedServiceId } ?: VedikaData.services.first()
    }

    var selectedTab by remember { mutableStateOf("Overview") }
    val isFavorite = uiState.favoriteServiceIds.contains(service.id)

    Scaffold(
        containerColor = VedikaCream,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { viewModel.navigateTo(Screen.Home) }, modifier = Modifier.size(48.dp)) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = VedikaTextDark,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    IconButton(onClick = { }, modifier = Modifier.size(48.dp)) {
                        Icon(
                            imageVector = Icons.Outlined.Share,
                            contentDescription = "Share",
                            tint = VedikaTextDark,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    IconButton(onClick = { viewModel.toggleFavoriteService(service.id) }, modifier = Modifier.size(48.dp)) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if (isFavorite) VedikaRed else VedikaTextDark,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
            }
        },
        bottomBar = {
            Surface(
                color = VedikaSurface,
                shadowElevation = 14.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "₹ ${service.price}",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = VedikaTextDark,
                                fontSize = 24.sp
                            )
                        )
                        Text(
                            text = "All inclusive pricing",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = VedikaTextMuted,
                                fontSize = 12.sp
                            )
                        )
                    }

                    Button(
                        onClick = { viewModel.startBooking(service.id) },
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = VedikaOrange),
                        modifier = Modifier
                            .width(190.dp)
                            .height(52.dp)
                    ) {
                        Text(
                            text = "Book Now",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 16.sp
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            // Real Hero Photography with Play Overlay and Page Indicator
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .height(240.dp)
                ) {
                    VedikaServiceImage(
                        service = service,
                        modifier = Modifier.fillMaxSize(),
                        cornerRadius = 22.dp
                    )

                    // Play Video Overlay
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(Color(0x99000000)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Play Ritual Video",
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    // 1/8 Page indicator
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color(0xB3000000),
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(14.dp)
                    ) {
                        Text(
                            text = "1/8",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            // Title, Subtitle, and Price
            item {
                Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)) {
                    Text(
                        text = service.title,
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = VedikaTextDark,
                            fontSize = 25.sp,
                            lineHeight = 32.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = service.shortDescription,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = VedikaTextMedium,
                            fontSize = 15.sp,
                            lineHeight = 22.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Price Row
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "₹ ${service.price}",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = VedikaTextDark,
                                fontSize = 26.sp
                            )
                        )
                        Text(
                            text = "₹ ${service.originalPrice}",
                            style = MaterialTheme.typography.titleLarge.copy(
                                color = VedikaTextMuted,
                                textDecoration = TextDecoration.LineThrough,
                                fontSize = 18.sp
                            )
                        )
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = VedikaGreenLight,
                            modifier = Modifier.height(28.dp)
                        ) {
                            Box(
                                modifier = Modifier.padding(horizontal = 10.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${service.discountPercent}% OFF",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = VedikaGreen,
                                        fontSize = 13.sp
                                    )
                                )
                            }
                        }
                    }
                }
            }

            // Specs Row (Duration, Pandit, Samagri)
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    SpecBox(
                        title = "Duration",
                        value = service.durationHours,
                        icon = Icons.Default.Schedule,
                        modifier = Modifier.weight(1f)
                    )
                    SpecBox(
                        title = "Pandit",
                        value = "${service.panditCount} Pandit",
                        icon = Icons.Default.Person,
                        modifier = Modifier.weight(1f)
                    )
                    SpecBox(
                        title = "Samagri",
                        value = if (service.samagriIncluded) "Included" else "Extra",
                        icon = Icons.Default.Inventory2,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Rating & Verification Row
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = VedikaAmber,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "${service.rating}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = VedikaTextDark
                        )
                        Text(
                            text = " (${service.reviewCount} reviews)",
                            fontSize = 14.sp,
                            color = VedikaTextMedium
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = VedikaGreenLight
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Verified,
                                contentDescription = null,
                                tint = VedikaGreen,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Verified Pandit",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = VedikaGreen,
                                    fontSize = 13.sp
                                )
                            )
                        }
                    }
                }
            }

            // Tabs: Overview | What's Included | Process | Checklist | Reviews
            item {
                val tabs = listOf("Overview", "What's Included", "Process", "Checklist", "Reviews")
                Surface(
                    color = VedikaCreamDark,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        tabs.forEach { tab ->
                            val isSelected = selectedTab == tab
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .clickable { selectedTab = tab }
                                    .padding(horizontal = 8.dp, vertical = 10.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = tab,
                                        style = MaterialTheme.typography.labelLarge.copy(
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                            color = if (isSelected) VedikaOrange else VedikaTextMedium,
                                            fontSize = 13.sp
                                        )
                                    )
                                    if (isSelected) {
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Box(
                                            modifier = Modifier
                                                .width(28.dp)
                                                .height(3.dp)
                                                .background(VedikaOrange)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Tab Content Body
            item {
                Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 18.dp)) {
                    when (selectedTab) {
                        "Overview" -> {
                            Text(
                                text = service.overview,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    color = VedikaTextDark,
                                    lineHeight = 24.sp,
                                    fontSize = 15.sp
                                )
                            )
                        }
                        "What's Included" -> {
                            service.whatsIncluded.forEach { item ->
                                Row(
                                    modifier = Modifier.padding(vertical = 6.dp),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle,
                                        contentDescription = null,
                                        tint = VedikaOrange,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = item,
                                        style = MaterialTheme.typography.bodyLarge.copy(
                                            color = VedikaTextDark,
                                            fontSize = 15.sp
                                        )
                                    )
                                }
                            }
                        }
                        "Process" -> {
                            service.ritualProcess.forEachIndexed { index, step ->
                                Row(
                                    modifier = Modifier.padding(vertical = 8.dp),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Surface(
                                        shape = CircleShape,
                                        color = VedikaOrange,
                                        modifier = Modifier.size(28.dp)
                                    ) {
                                        Box(contentAlignment = Alignment.Center) {
                                            Text(
                                                text = "${index + 1}",
                                                color = Color.White,
                                                fontSize = 13.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Text(
                                        text = step,
                                        style = MaterialTheme.typography.bodyLarge.copy(
                                            color = VedikaTextDark,
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 15.sp
                                        )
                                    )
                                }
                            }
                        }
                        "Checklist" -> {
                            Column {
                                Text(
                                    text = "Ritual Preparation Checklist (तैयारी सूची)",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = VedikaTextDark,
                                        fontSize = 16.sp
                                    )
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Keep these items ready before Pandit ji arrives. Check off items as you arrange them.",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = VedikaTextMedium,
                                        fontSize = 13.sp
                                    )
                                )
                                Spacer(modifier = Modifier.height(14.dp))

                                // Household arranged items
                                Text(
                                    text = "ARRANGED AT HOME (घर पर व्यवस्था):",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = VedikaOrangeDark,
                                        fontSize = 12.sp
                                    )
                                )
                                Spacer(modifier = Modifier.height(8.dp))

                                uiState.preparationChecklist.filter { !it.isProvidedByPandit }.forEach { item ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(10.dp))
                                            .clickable { viewModel.toggleChecklistItem(item.id) }
                                            .padding(vertical = 6.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Checkbox(
                                            checked = item.isChecked,
                                            onCheckedChange = { viewModel.toggleChecklistItem(item.id) },
                                            colors = CheckboxDefaults.colors(checkedColor = VedikaOrange)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(
                                                text = item.title,
                                                fontWeight = if (item.isChecked) FontWeight.Normal else FontWeight.SemiBold,
                                                color = if (item.isChecked) VedikaTextMuted else VedikaTextDark,
                                                fontSize = 14.sp
                                            )
                                            Text(
                                                text = item.quantity,
                                                color = VedikaTextMedium,
                                                fontSize = 12.sp
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(16.dp))
                                HorizontalDivider(color = VedikaDivider, thickness = 0.8.dp)
                                Spacer(modifier = Modifier.height(14.dp))

                                // Pandit brought items
                                Text(
                                    text = "PROVIDED BY VEDIKA PANDIT (आचार्य जी लाएंगे):",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = VedikaGreen,
                                        fontSize = 12.sp
                                    )
                                )
                                Spacer(modifier = Modifier.height(8.dp))

                                uiState.preparationChecklist.filter { it.isProvidedByPandit }.forEach { item ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 6.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Verified,
                                            contentDescription = null,
                                            tint = VedikaGreen,
                                            modifier = Modifier.size(20.dp)
                                        )
                                        Spacer(modifier = Modifier.width(12.dp))
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(
                                                text = item.title,
                                                fontWeight = FontWeight.Medium,
                                                color = VedikaTextDark,
                                                fontSize = 14.sp
                                            )
                                            Text(
                                                text = item.quantity,
                                                color = VedikaTextMedium,
                                                fontSize = 12.sp
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        "Reviews" -> {
                            Text(
                                text = "Based on ${service.reviewCount} verified ceremonies across Jharkhand, Bihar, and UP. Rated 4.9 out of 5 stars by families for punctuality, purity of Sanskrit chanting, and clear guidance.",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    color = VedikaTextDark,
                                    lineHeight = 24.sp,
                                    fontSize = 15.sp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SpecBox(
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
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
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = VedikaOrange,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = VedikaTextMuted,
                    fontSize = 12.sp
                )
            )
            Text(
                text = value,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = VedikaTextDark,
                    fontSize = 14.sp
                )
            )
        }
    }
}

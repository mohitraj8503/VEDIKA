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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.VedikaData
import com.example.model.FestivalEvent
import com.example.model.PanchangDay
import com.example.model.ShubhMuhuratItem
import com.example.ui.components.VedikaBottomBar
import com.example.ui.theme.*
import com.example.viewmodel.Screen
import com.example.viewmodel.VedikaUiState
import com.example.viewmodel.VedikaViewModel

@Composable
fun PanchangMuhuratScreen(
    viewModel: VedikaViewModel,
    uiState: VedikaUiState,
    modifier: Modifier = Modifier
) {
    val panchang: PanchangDay = if (uiState.activePanchangDay == "tomorrow") {
        VedikaData.tomorrowPanchang
    } else {
        VedikaData.todayPanchang
    }

    Scaffold(
        containerColor = VedikaCream,
        topBar = {
            Surface(
                color = VedikaCream,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { viewModel.setBottomNavTab("Home") },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = VedikaTextDark,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (uiState.isHindi) "दैनिक पंचांग एवं शुभ मुहूर्त" else "Daily Panchang & Muhurat",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = VedikaTextDark,
                                fontSize = 19.sp
                            )
                        )
                        Text(
                            text = "${uiState.selectedCity} • ${panchang.samvatYear}",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = VedikaTextMedium,
                                fontSize = 12.sp
                            )
                        )
                    }
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = VedikaSaffronLight,
                        modifier = Modifier
                            .clickable { viewModel.navigateTo(Screen.MantraJapa) }
                            .padding(end = 8.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.SelfImprovement,
                                contentDescription = null,
                                tint = VedikaOrangeDark,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Japa",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = VedikaOrangeDark,
                                    fontSize = 12.sp
                                )
                            )
                        }
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
            contentPadding = PaddingValues(horizontal = 18.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Day Switcher Tabs: Today vs Tomorrow
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(VedikaSurface)
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    val isToday = uiState.activePanchangDay == "today"
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = if (isToday) VedikaOrange else Color.Transparent,
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp)
                            .clickable { viewModel.setActivePanchangDay("today") }
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = if (uiState.isHindi) "आज का पंचांग (Today)" else "Today's Panchang",
                                color = if (isToday) Color.White else VedikaTextDark,
                                fontWeight = if (isToday) FontWeight.Bold else FontWeight.Medium,
                                fontSize = 14.sp
                            )
                        }
                    }

                    val isTomorrow = uiState.activePanchangDay == "tomorrow"
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = if (isTomorrow) VedikaOrange else Color.Transparent,
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp)
                            .clickable { viewModel.setActivePanchangDay("tomorrow") }
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = if (uiState.isHindi) "कल का पंचांग (Tomorrow)" else "Tomorrow's Panchang",
                                color = if (isTomorrow) Color.White else VedikaTextDark,
                                fontWeight = if (isTomorrow) FontWeight.Bold else FontWeight.Medium,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }

            // Hero Card: Date, Tithi & Sun Timings
            item {
                Surface(
                    shape = RoundedCornerShape(22.dp),
                    color = VedikaSurface,
                    border = BorderStroke(1.dp, VedikaCardBorder),
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(4.dp, RoundedCornerShape(22.dp), spotColor = Color(0x10000000))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(Color(0xFFFFF8E1), Color(0xFFFFFFFF))
                                )
                            )
                            .padding(20.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = panchang.dateString,
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = VedikaTextDark,
                                        fontSize = 18.sp
                                    )
                                )
                                Text(
                                    text = panchang.dayName,
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontWeight = FontWeight.SemiBold,
                                        color = VedikaOrangeDark,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = VedikaSaffronLight
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.WbSunny,
                                        contentDescription = null,
                                        tint = VedikaOrange,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "${panchang.sunrise} - ${panchang.sunset}",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = VedikaTextDark
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        HorizontalDivider(color = VedikaDivider, thickness = 0.8.dp)
                        Spacer(modifier = Modifier.height(14.dp))

                        // Grid of 4 Core Vedic Coordinates
                        Row(modifier = Modifier.fillMaxWidth()) {
                            PanchangCell(
                                title = "TITHI (तिथि)",
                                value = panchang.tithi,
                                icon = Icons.Default.Brightness4,
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            PanchangCell(
                                title = "NAKSHATRA (नक्षत्र)",
                                value = panchang.nakshatra,
                                icon = Icons.Default.Star,
                                modifier = Modifier.weight(1f)
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(modifier = Modifier.fillMaxWidth()) {
                            PanchangCell(
                                title = "YOGA (योग)",
                                value = panchang.yoga,
                                icon = Icons.Default.Grain,
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            PanchangCell(
                                title = "MOON SIGN (चन्द्र राशि)",
                                value = panchang.moonSign,
                                icon = Icons.Default.NightlightRound,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }

            // Auspicious vs Inauspicious Timings Card
            item {
                Surface(
                    shape = RoundedCornerShape(22.dp),
                    color = VedikaSurface,
                    border = BorderStroke(1.dp, VedikaCardBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Text(
                            text = "Auspicious & Inauspicious Kaal (शुभ / अशुभ काल)",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = VedikaTextDark,
                                fontSize = 16.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(14.dp))

                        // Abhijit Muhurat (Auspicious)
                        TimingRow(
                            label = "Abhijit Muhurat (अभिजित मुहूर्त)",
                            timing = panchang.abhijitMuhurat,
                            isAuspicious = true,
                            badgeText = "Best for Pujas"
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        // Amrit Kaal
                        TimingRow(
                            label = "Amrit Kaal (अमृत काल)",
                            timing = panchang.amritKaal,
                            isAuspicious = true,
                            badgeText = "Highly Auspicious"
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        // Brahma Muhurat
                        TimingRow(
                            label = "Brahma Muhurat (ब्रह्म मुहूर्त)",
                            timing = panchang.brahmaMuhurat,
                            isAuspicious = true,
                            badgeText = "Meditation & Sadhana"
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        // Rahu Kaal (Avoid)
                        TimingRow(
                            label = "Rahu Kaal (राहु काल)",
                            timing = panchang.rahuKaal,
                            isAuspicious = false,
                            badgeText = "Avoid New Work"
                        )
                    }
                }
            }

            // Section: Auspicious Shubh Muhurats List
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Shubh Muhurat Calendar (शुभ मुहूर्त)",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = VedikaTextDark,
                            fontSize = 18.sp
                        )
                    )
                }
            }

            items(VedikaData.shubhMuhurats) { muhurat ->
                MuhuratCard(
                    muhurat = muhurat,
                    onBookClick = {
                        viewModel.startBooking(muhurat.linkedServiceId)
                    }
                )
            }

            // Section: Upcoming Festivals & Vrats
            item {
                Text(
                    text = "Upcoming Festivals & Vrats (आगामी पर्व)",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = VedikaTextDark,
                        fontSize = 18.sp
                    ),
                    modifier = Modifier.padding(top = 10.dp)
                )
            }

            items(VedikaData.upcomingFestivals) { festival ->
                FestivalCard(
                    festival = festival,
                    onBookSpecialPuja = {
                        viewModel.startBooking(festival.recommendedPujaId)
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun PanchangCell(
    title: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = VedikaCreamDark,
        border = BorderStroke(1.dp, VedikaCardBorder),
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = VedikaOrange,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = VedikaTextMedium,
                        fontSize = 11.sp
                    )
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = VedikaTextDark,
                    fontSize = 13.sp,
                    lineHeight = 17.sp
                )
            )
        }
    }
}

@Composable
private fun TimingRow(
    label: String,
    timing: String,
    isAuspicious: Boolean,
    badgeText: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(if (isAuspicious) Color(0xFFF1F8E9) else Color(0xFFFFEBEE))
            .padding(horizontal = 14.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = if (isAuspicious) Color(0xFF2E7D32) else Color(0xFFC62828),
                    fontSize = 12.sp
                )
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = timing,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = VedikaTextDark,
                    fontSize = 14.sp
                )
            )
        }
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = if (isAuspicious) Color(0xFFDCEDC8) else Color(0xFFFFCDD2)
        ) {
            Text(
                text = badgeText,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = if (isAuspicious) Color(0xFF1B5E20) else Color(0xFFB71C1C),
                    fontSize = 11.sp
                ),
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}

@Composable
private fun MuhuratCard(
    muhurat: ShubhMuhuratItem,
    onBookClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(18.dp),
        color = VedikaSurface,
        border = BorderStroke(1.dp, VedikaCardBorder),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(18.dp), spotColor = Color(0x0A000000))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = VedikaSaffronLight
                ) {
                    Text(
                        text = muhurat.category,
                        color = VedikaOrangeDark,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }
                Text(
                    text = muhurat.bestTime,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = VedikaTextMedium,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = muhurat.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = VedikaTextDark,
                    fontSize = 16.sp
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = muhurat.significance,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = VedikaTextMedium,
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                )
            )

            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Auspicious Dates: ",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = VedikaTextDark
                )
                Text(
                    text = muhurat.dates.joinToString(" • "),
                    fontSize = 12.sp,
                    color = VedikaOrangeDark,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onBookClick,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = VedikaOrange),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
            ) {
                Text("Book Pandit for this Muhurat", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }
        }
    }
}

@Composable
private fun FestivalCard(
    festival: FestivalEvent,
    onBookSpecialPuja: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(18.dp),
        color = VedikaSurface,
        border = BorderStroke(1.dp, VedikaCardBorder),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(18.dp), spotColor = Color(0x0A000000))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = festival.name,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = VedikaTextDark,
                            fontSize = 16.sp
                        )
                    )
                    Text(
                        text = festival.hindiName,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = VedikaGold,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    )
                }
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = VedikaAmber
                ) {
                    Text(
                        text = "In ${festival.daysRemaining} Days",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = null,
                    tint = VedikaOrange,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "${festival.date} • ${festival.tithi}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = VedikaTextMedium
                )
            }

            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = festival.description,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = VedikaTextMedium,
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                )
            )

            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(
                onClick = onBookSpecialPuja,
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, VedikaOrange),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            ) {
                Text(
                    text = "Book Festive Puja & Samagri",
                    color = VedikaOrangeDark,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
        }
    }
}

package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                        .padding(horizontal = 14.dp, vertical = 10.dp)
                        .windowInsetsPadding(WindowInsets.statusBars),
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
                        text = "Today's Panchang",
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
                        onClick = { },
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Share,
                            contentDescription = "Share",
                            tint = VedikaTextDark,
                            modifier = Modifier.size(22.dp)
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
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 1. Sunrise Temple Hero Banner
            item {
                Surface(
                    shape = RoundedCornerShape(22.dp),
                    color = Color(0xFFFFFAF2),
                    border = BorderStroke(1.dp, Color(0xFFFFDEC2)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(5.dp, RoundedCornerShape(22.dp), spotColor = Color(0x18000000))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                    ) {
                        SunriseTempleArt(modifier = Modifier.fillMaxSize())

                        // Soft left text overlay
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(20.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "18 October 2026",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color(0xFF2B231D),
                                    fontSize = 20.sp
                                )
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Sunday",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    color = VedikaOrangeDark,
                                    fontSize = 15.sp
                                )
                            )
                        }
                    }
                }
            }

            // 2. Four Core Vedic Elements: Tithi, Nakshatra, Yoga, Karana
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    PanchangCoordinateCircle(
                        icon = Icons.Default.WbSunny,
                        label = "Tithi",
                        value = "Ekadashi",
                        sub = "(till 11:32 AM)",
                        modifier = Modifier.weight(1f)
                    )
                    PanchangCoordinateCircle(
                        icon = Icons.Default.BrightnessAuto,
                        label = "Nakshatra",
                        value = "Rohini",
                        sub = "(till 02:15 PM)",
                        modifier = Modifier.weight(1f)
                    )
                    PanchangCoordinateCircle(
                        icon = Icons.Default.SelfImprovement,
                        label = "Yoga",
                        value = "Siddhi",
                        sub = "(till 10:45 AM)",
                        modifier = Modifier.weight(1f)
                    )
                    PanchangCoordinateCircle(
                        icon = Icons.Default.DarkMode,
                        label = "Karana",
                        value = "Bava",
                        sub = "(till 11:32 AM)",
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // 3. Auspicious Timings Section
            item {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(text = "🔅", fontSize = 16.sp)
                        Text(
                            text = "Auspicious Timings",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = VedikaTextDark,
                                fontSize = 16.sp
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = VedikaSurface,
                        border = BorderStroke(1.dp, VedikaCardBorder),
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(3.dp, RoundedCornerShape(20.dp), spotColor = Color(0x0C000000))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            TimingRow(title = "Brahma Muhurta", time = "04:52 AM - 05:40 AM")
                            HorizontalDivider(color = VedikaDivider, thickness = 0.8.dp)
                            TimingRow(title = "Abhijit Muhurta", time = "11:48 AM - 12:36 PM", isHighlight = true)
                            HorizontalDivider(color = VedikaDivider, thickness = 0.8.dp)
                            TimingRow(title = "Vijay Muhurta", time = "02:18 PM - 03:06 PM")
                            HorizontalDivider(color = VedikaDivider, thickness = 0.8.dp)
                            TimingRow(title = "Godhuli Muhurta", time = "05:42 PM - 06:18 PM")
                        }
                    }
                }
            }

            // 4. "Today's Thought" Section with Diya Illustration
            item {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(text = "🪔", fontSize = 16.sp)
                        Text(
                            text = "Today's Thought",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = VedikaTextDark,
                                fontSize = 16.sp
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = Color(0xFFFFF9F0),
                        border = BorderStroke(1.dp, Color(0xFFFFDEC2)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(3.dp, RoundedCornerShape(20.dp), spotColor = Color(0x0C000000))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = "“Faith is the bridge between where you are and where you want to be.”",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                        fontWeight = FontWeight.Medium,
                                        color = Color(0xFF4A3428),
                                        fontSize = 14.sp,
                                        lineHeight = 21.sp
                                    )
                                )
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            // Glowing brass diya artwork
                            Box(
                                modifier = Modifier
                                    .size(76.dp)
                                    .clip(RoundedCornerShape(16.dp))
                            ) {
                                SacredDiyaCanvas(modifier = Modifier.fillMaxSize())
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PanchangCoordinateCircle(
    icon: ImageVector,
    label: String,
    value: String,
    sub: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(horizontal = 2.dp)
    ) {
        Surface(
            shape = CircleShape,
            color = Color(0xFFFFF2E6),
            border = BorderStroke(1.dp, Color(0xFFFFDEC2)),
            modifier = Modifier.size(50.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = VedikaOrangeDark,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = VedikaTextDark,
                fontSize = 12.sp
            )
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.SemiBold,
                color = VedikaOrangeDark,
                fontSize = 11.sp
            )
        )
        Text(
            text = sub,
            style = MaterialTheme.typography.labelSmall.copy(
                color = VedikaTextMuted,
                fontSize = 9.sp
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun TimingRow(
    title: String,
    time: String,
    isHighlight: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Outlined.AccessTime,
                contentDescription = null,
                tint = if (isHighlight) VedikaOrangeDark else VedikaTextMuted,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = if (isHighlight) FontWeight.Bold else FontWeight.Medium,
                    color = if (isHighlight) VedikaOrangeDark else VedikaTextDark,
                    fontSize = 13.sp
                )
            )
        }

        Text(
            text = time,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.SemiBold,
                color = if (isHighlight) VedikaOrangeDark else VedikaTextMedium,
                fontSize = 13.sp
            )
        )
    }
}

@Composable
private fun SunriseTempleArt(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // Sky gradient
        drawRect(
            brush = Brush.horizontalGradient(
                colors = listOf(Color(0xFFFFF3E0), Color(0xFFFFE0B2), Color(0xFFFFCC80))
            ),
            size = Size(w, h)
        )

        // Rising Sun
        val sunCenter = Offset(w * 0.72f, h * 0.42f)
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color(0xFFFFFDE7), Color(0xFFFFD54F), Color(0x00FF8F00)),
                center = sunCenter,
                radius = w * 0.28f
            ),
            radius = w * 0.28f,
            center = sunCenter
        )
        drawCircle(Color(0xFFFFEE58), radius = w * 0.08f, center = sunCenter)

        // Temple silhouette on riverbank (right side)
        val templePath = Path().apply {
            moveTo(w * 0.88f, h * 0.20f)
            lineTo(w * 0.94f, h * 0.50f)
            lineTo(w * 0.98f, h * 0.85f)
            lineTo(w * 0.78f, h * 0.85f)
            lineTo(w * 0.82f, h * 0.50f)
            close()
        }
        drawPath(
            path = templePath,
            brush = Brush.verticalGradient(
                colors = listOf(Color(0xFFD7A76E), Color(0xFF9E6533))
            )
        )

        // Dhwaja flag
        val flag = Path().apply {
            moveTo(w * 0.88f, h * 0.16f)
            lineTo(w * 0.93f, h * 0.18f)
            lineTo(w * 0.88f, h * 0.20f)
            close()
        }
        drawPath(flag, color = Color(0xFFFF5722))
    }
}

@Composable
private fun SacredDiyaCanvas(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // Radiant halo
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color(0xFFFFD54F), Color(0xFFFF9800), Color(0x00FF5722)),
                center = Offset(w * 0.5f, h * 0.45f),
                radius = w * 0.45f
            ),
            radius = w * 0.45f,
            center = Offset(w * 0.5f, h * 0.45f)
        )

        // Brass Diya Base
        val diyaBowl = Path().apply {
            moveTo(w * 0.22f, h * 0.65f)
            quadraticBezierTo(w * 0.50f, h * 0.92f, w * 0.78f, h * 0.65f)
            lineTo(w * 0.84f, h * 0.60f)
            lineTo(w * 0.16f, h * 0.60f)
            close()
        }
        drawPath(
            path = diyaBowl,
            brush = Brush.verticalGradient(
                listOf(Color(0xFFFFD54F), Color(0xFFC78200), Color(0xFF8D5B00))
            )
        )

        // Flame
        val flame = Path().apply {
            moveTo(w * 0.50f, h * 0.15f)
            cubicTo(w * 0.38f, h * 0.35f, w * 0.40f, h * 0.55f, w * 0.50f, h * 0.60f)
            cubicTo(w * 0.60f, h * 0.55f, w * 0.62f, h * 0.35f, w * 0.50f, h * 0.15f)
            close()
        }
        drawPath(
            path = flame,
            brush = Brush.verticalGradient(
                listOf(Color(0xFFFFF9C4), Color(0xFFFFB300), Color(0xFFFF5722))
            )
        )

        // Inner glowing white core
        drawCircle(Color(0xFFFFFDE7), radius = w * 0.06f, center = Offset(w * 0.50f, h * 0.46f))
    }
}

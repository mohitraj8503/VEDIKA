package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.VedikaData
import com.example.model.PanditProfile
import com.example.ui.components.PanditVisualArt
import com.example.ui.components.VedikaBottomBar
import com.example.ui.theme.*
import com.example.viewmodel.Screen
import com.example.viewmodel.VedikaViewModel

@Composable
fun NearbyMapScreen(
    viewModel: VedikaViewModel,
    uiState: com.example.viewmodel.VedikaUiState,
    modifier: Modifier = Modifier
) {
    var selectedPandit by remember { mutableStateOf(VedikaData.pandits.first()) }
    var selectedCategoryTab by remember { mutableStateOf("Pandit") }

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
                    IconButton(onClick = { viewModel.navigateTo(Screen.PanditBooking) }, modifier = Modifier.size(48.dp)) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = VedikaTextDark,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Text(
                        text = "Nearby Services",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = VedikaTextDark,
                            fontSize = 20.sp
                        )
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
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Interactive Map Canvas
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF4EFE6))
            ) {
                val w = size.width
                val h = size.height

                // Sacred holy river
                val riverPath = Path().apply {
                    moveTo(0f, h * 0.35f)
                    cubicTo(w * 0.3f, h * 0.45f, w * 0.6f, h * 0.28f, w, h * 0.40f)
                    lineTo(w, h * 0.48f)
                    cubicTo(w * 0.6f, h * 0.36f, w * 0.3f, h * 0.53f, 0f, h * 0.43f)
                    close()
                }
                drawPath(path = riverPath, color = Color(0xFFCCE5FF))

                // Roads grid network
                drawLine(Color(0xFFE2DDD3), Offset(0f, h * 0.2f), Offset(w, h * 0.2f), strokeWidth = 14f)
                drawLine(Color(0xFFFFFDF8), Offset(0f, h * 0.2f), Offset(w, h * 0.2f), strokeWidth = 8f)

                drawLine(Color(0xFFE2DDD3), Offset(0f, h * 0.65f), Offset(w, h * 0.65f), strokeWidth = 14f)
                drawLine(Color(0xFFFFFDF8), Offset(0f, h * 0.65f), Offset(w, h * 0.65f), strokeWidth = 8f)

                drawLine(Color(0xFFE2DDD3), Offset(w * 0.45f, 0f), Offset(w * 0.45f, h), strokeWidth = 16f)
                drawLine(Color(0xFFFFFDF8), Offset(w * 0.45f, 0f), Offset(w * 0.45f, h), strokeWidth = 10f)

                drawLine(Color(0xFFE2DDD3), Offset(w * 0.1f, 0f), Offset(w * 0.9f, h), strokeWidth = 12f)
                drawLine(Color(0xFFFFFDF8), Offset(w * 0.1f, 0f), Offset(w * 0.9f, h), strokeWidth = 6f)

                // User current location pulsing blue dot
                drawCircle(color = Color(0x332196F3), radius = 32f, center = Offset(w * 0.48f, h * 0.52f))
                drawCircle(color = Color(0xFF2196F3), radius = 10f, center = Offset(w * 0.48f, h * 0.52f))
                drawCircle(color = Color.White, radius = 5f, center = Offset(w * 0.48f, h * 0.52f))
            }

            // Top Filter Row on map (52dp height buttons)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 14.dp)
                    .align(Alignment.TopCenter),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                listOf("Pandit", "Temple", "Puja").forEach { tab ->
                    val isSelected = selectedCategoryTab == tab
                    Surface(
                        onClick = { selectedCategoryTab = tab },
                        shape = RoundedCornerShape(20.dp),
                        color = if (isSelected) VedikaOrange else VedikaSurface,
                        shadowElevation = 4.dp,
                        modifier = Modifier.height(40.dp)
                    ) {
                        Box(modifier = Modifier.padding(horizontal = 18.dp), contentAlignment = Alignment.Center) {
                            Text(
                                text = tab,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold,
                                fontSize = 14.sp,
                                color = if (isSelected) Color.White else VedikaTextDark
                            )
                        }
                    }
                }
            }

            // Map Pins
            MapPinMarker(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 70.dp, top = 130.dp),
                title = "Acharya Vishnu Sharma",
                isSelected = selectedPandit.id == "p1",
                onClick = { selectedPandit = VedikaData.pandits[0] }
            )

            MapPinMarker(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 60.dp, bottom = 60.dp),
                title = "Pandit Rajesh Mishra",
                isSelected = selectedPandit.id == "p2",
                onClick = { selectedPandit = VedikaData.pandits[1] }
            )

            MapPinMarker(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 50.dp, top = 90.dp),
                title = "Acharya Suresh Tiwari",
                isSelected = selectedPandit.id == "p3",
                onClick = { selectedPandit = VedikaData.pandits[2] }
            )

            // Bottom Selected Provider Details Card with Real Photo
            Surface(
                shape = RoundedCornerShape(22.dp),
                color = VedikaSurface,
                border = BorderStroke(1.dp, VedikaCardBorder),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.BottomCenter)
                    .shadow(14.dp, RoundedCornerShape(22.dp), spotColor = Color(0x1F000000))
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(RoundedCornerShape(14.dp))
                        ) {
                            PanditVisualArt(
                                panditName = selectedPandit.name,
                                modifier = Modifier.fillMaxSize(),
                                cornerRadius = 14.dp
                            )
                        }
                        Spacer(modifier = Modifier.width(14.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = selectedPandit.name,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = VedikaTextDark,
                                    fontSize = 17.sp
                                )
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Star, contentDescription = null, tint = VedikaAmber, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(
                                    text = "${selectedPandit.rating} (${selectedPandit.reviewCount})",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = VedikaTextDark
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "• ${selectedPandit.distanceKm} km away",
                                    fontSize = 12.sp,
                                    color = VedikaGreen,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text(
                                text = selectedPandit.specializations.joinToString(" • "),
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = VedikaTextMedium,
                                    fontSize = 13.sp
                                ),
                                maxLines = 1
                            )
                        }

                        Button(
                            onClick = { viewModel.startBooking() },
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = VedikaOrange),
                            modifier = Modifier.height(40.dp)
                        ) {
                            Text("Book", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MapPinMarker(
    modifier: Modifier = Modifier,
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = if (isSelected) VedikaOrangeDark else VedikaSurface,
            border = BorderStroke(1.dp, if (isSelected) VedikaOrange else VedikaCardBorder),
            shadowElevation = if (isSelected) 6.dp else 2.dp
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = null,
                    tint = if (isSelected) Color.White else VedikaOrange,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = title.split(" ").take(2).joinToString(" "),
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) Color.White else VedikaTextDark,
                        fontSize = 11.sp
                    )
                )
            }
        }
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = null,
            tint = if (isSelected) VedikaOrangeDark else VedikaOrange,
            modifier = Modifier
                .size(18.dp)
                .offset(y = (-4).dp)
        )
    }
}

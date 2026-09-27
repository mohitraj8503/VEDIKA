package com.example.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
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
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.VedikaData
import com.example.model.MantraItem
import com.example.ui.components.VedikaBottomBar
import com.example.ui.theme.*
import com.example.viewmodel.Screen
import com.example.viewmodel.VedikaUiState
import com.example.viewmodel.VedikaViewModel

@Composable
fun MantraJapaScreen(
    viewModel: VedikaViewModel,
    uiState: VedikaUiState,
    modifier: Modifier = Modifier
) {
    val activeMantra = VedikaData.vedicMantras.find { it.id == uiState.selectedMantraId }
        ?: VedikaData.vedicMantras.first()

    val progress = (uiState.japaCounter.toFloat() / 108f).coerceIn(0f, 1f)
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(180),
        label = "mala_progress"
    )

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
                            text = if (uiState.isHindi) "मंत्र जप एवं साधना" else "Mantra Japa & Sadhana",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = VedikaTextDark,
                                fontSize = 19.sp
                            )
                        )
                        Text(
                            text = "108 मनका रुद्राक्ष जप माला",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = VedikaTextMedium,
                                fontSize = 12.sp
                            )
                        )
                    }
                    IconButton(
                        onClick = { viewModel.resetJapa() },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Reset Counter",
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
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Mantra Selector Chips
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(VedikaData.vedicMantras) { mantra ->
                        val isSelected = mantra.id == activeMantra.id
                        Surface(
                            onClick = { viewModel.selectMantra(mantra.id) },
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
                                    text = mantra.name,
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        color = if (isSelected) Color.White else VedikaTextDark,
                                        fontSize = 13.sp
                                    )
                                )
                            }
                        }
                    }
                }
            }

            // Sacred Japa Mala Circular Counter Dial
            item {
                Surface(
                    shape = RoundedCornerShape(28.dp),
                    color = VedikaSurface,
                    border = BorderStroke(1.dp, VedikaCardBorder),
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(4.dp, RoundedCornerShape(28.dp), spotColor = Color(0x10000000))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp, horizontal = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier.size(230.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            // Circular Rudraksha Beads Mala Canvas
                            Canvas(modifier = Modifier.fillMaxSize()) {
                                val w = size.width
                                val h = size.height
                                val radius = w * 0.40f
                                val center = Offset(w / 2, h / 2)

                                // Track circle
                                drawCircle(
                                    color = Color(0xFFF3E5D8),
                                    radius = radius,
                                    center = center,
                                    style = Stroke(width = 16f)
                                )

                                // Progress Arc
                                drawArc(
                                    brush = Brush.sweepGradient(
                                        listOf(VedikaOrange, VedikaGold, VedikaOrangeDark, VedikaOrange)
                                    ),
                                    startAngle = -90f,
                                    sweepAngle = animatedProgress * 360f,
                                    useCenter = false,
                                    topLeft = Offset(center.x - radius, center.y - radius),
                                    size = Size(radius * 2, radius * 2),
                                    style = Stroke(width = 16f, cap = StrokeCap.Round)
                                )

                                // 108 decorative dots around the mala ring
                                for (i in 0 until 24) {
                                    val angle = (i * (360f / 24f) - 90f) * (Math.PI / 180f)
                                    val bx = center.x + radius * kotlin.math.cos(angle).toFloat()
                                    val by = center.y + radius * kotlin.math.sin(angle).toFloat()
                                    drawCircle(
                                        color = Color(0xFF8D6E63),
                                        radius = 4f,
                                        center = Offset(bx, by)
                                    )
                                }
                            }

                            // Center Counter & Tap target
                            Surface(
                                shape = CircleShape,
                                color = VedikaCreamDark,
                                border = BorderStroke(2.dp, VedikaOrange),
                                modifier = Modifier
                                    .size(150.dp)
                                    .clickable { viewModel.incrementJapa() }
                                    .shadow(6.dp, CircleShape, spotColor = Color(0x20000000))
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "${uiState.japaCounter}",
                                        style = MaterialTheme.typography.displaySmall.copy(
                                            fontWeight = FontWeight.ExtraBold,
                                            color = VedikaOrangeDark,
                                            fontSize = 42.sp
                                        )
                                    )
                                    Text(
                                        text = "of 108",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = VedikaTextMedium,
                                            fontSize = 12.sp
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "TAP TO JAPA",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = VedikaGold,
                                            fontSize = 11.sp
                                        )
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(18.dp))

                        // Rounds Completed info
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.AllInclusive,
                                contentDescription = null,
                                tint = VedikaGold,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Mala Rounds Completed: ${uiState.totalJapaRounds}",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = VedikaTextDark,
                                    fontSize = 14.sp
                                )
                            )
                        }
                    }
                }
            }

            // Big Action Button: Japa Bead Count
            item {
                Button(
                    onClick = { viewModel.incrementJapa() },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = VedikaOrange),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .shadow(4.dp, RoundedCornerShape(16.dp), spotColor = Color(0x20E65100))
                ) {
                    Icon(
                        imageVector = Icons.Default.TouchApp,
                        contentDescription = null,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Jap Karein (+1 Manqa)",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }

            // Mantra Sacred Text Card
            item {
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = VedikaSurface,
                    border = BorderStroke(1.dp, VedikaCardBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = activeMantra.name,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = VedikaTextDark,
                                    fontSize = 17.sp
                                )
                            )
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = VedikaSaffronLight
                            ) {
                                Text(
                                    text = activeMantra.deity,
                                    color = VedikaOrangeDark,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Sacred Devanagari Script
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0xFFFFF8E1),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = activeMantra.mantraDevanagari,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = VedikaOrangeDark,
                                    fontSize = 17.sp,
                                    lineHeight = 26.sp,
                                    textAlign = TextAlign.Center
                                ),
                                modifier = Modifier.padding(16.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Transliteration
                        Text(
                            text = activeMantra.transliteration,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = VedikaTextMedium,
                                fontSize = 13.sp,
                                lineHeight = 19.sp,
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                            )
                        )

                        Spacer(modifier = Modifier.height(14.dp))
                        HorizontalDivider(color = VedikaDivider, thickness = 0.8.dp)
                        Spacer(modifier = Modifier.height(12.dp))

                        // Meaning
                        Text(
                            text = "DIVINE MEANING (अर्थ)",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = VedikaGold,
                                fontSize = 11.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = activeMantra.meaning,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = VedikaTextDark,
                                fontSize = 13.sp,
                                lineHeight = 19.sp
                            )
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // Spiritual Benefits
                        Text(
                            text = "SPIRITUAL BENEFITS (लाभ)",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = VedikaGreen,
                                fontSize = 11.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = activeMantra.benefits,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = VedikaTextMedium,
                                fontSize = 13.sp,
                                lineHeight = 18.sp
                            )
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

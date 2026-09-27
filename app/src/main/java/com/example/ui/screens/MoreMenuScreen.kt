package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.VedikaBottomBar
import com.example.ui.theme.*
import com.example.viewmodel.Screen
import com.example.viewmodel.VedikaViewModel

@Composable
fun MoreMenuScreen(
    viewModel: VedikaViewModel,
    uiState: com.example.viewmodel.VedikaUiState,
    modifier: Modifier = Modifier
) {
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
                    IconButton(onClick = { viewModel.setBottomNavTab("Profile") }, modifier = Modifier.size(48.dp)) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = VedikaTextDark,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Text(
                        text = "More Options",
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
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp)
        ) {
            item {
                Surface(
                    shape = RoundedCornerShape(22.dp),
                    color = VedikaSurface,
                    border = BorderStroke(1.dp, VedikaCardBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(vertical = 8.dp)) {
                        ProfileMenuItem(
                            icon = Icons.Default.CalendarMonth,
                            title = if (uiState.isHindi) "दैनिक पंचांग एवं कैलेंडर" else "Religious Calendar & Panchang",
                            onClick = { viewModel.navigateTo(Screen.PanchangMuhurat) }
                        )
                        HorizontalDivider(color = VedikaDivider, thickness = 0.8.dp)

                        ProfileMenuItem(
                            icon = Icons.Default.Celebration,
                            title = if (uiState.isHindi) "त्योहार एवं शुभ मुहूर्त" else "Festival & Shubh Muhurat",
                            onClick = { viewModel.navigateTo(Screen.PanchangMuhurat) }
                        )
                        HorizontalDivider(color = VedikaDivider, thickness = 0.8.dp)

                        ProfileMenuItem(
                            icon = Icons.Default.SelfImprovement,
                            title = if (uiState.isHindi) "मंत्र जप एवं साधना (108 Mala)" else "Mantra Japa & Sadhana (108 Mala)",
                            onClick = { viewModel.navigateTo(Screen.MantraJapa) }
                        )
                        HorizontalDivider(color = VedikaDivider, thickness = 0.8.dp)

                        ProfileMenuItem(
                            icon = Icons.Default.TempleHindu,
                            title = if (uiState.isHindi) "मंदिर दर्शन एवं VIP पास" else "Temple Darshan & Passes",
                            onClick = { viewModel.navigateTo(Screen.TempleDarshan) }
                        )
                        HorizontalDivider(color = VedikaDivider, thickness = 0.8.dp)

                        ProfileMenuItem(
                            icon = Icons.Default.Inventory2,
                            title = if (uiState.isHindi) "पूजा सामग्री भंडार" else "Puja Samagri Store",
                            onClick = { viewModel.navigateTo(Screen.PujaSamagri) }
                        )
                        HorizontalDivider(color = VedikaDivider, thickness = 0.8.dp)

                        ProfileMenuItem(
                            icon = Icons.Default.AutoAwesome,
                            title = if (uiState.isHindi) "वास्तु एवं ज्योतिष परामर्श" else "Vastu & Astrology Consultation",
                            onClick = {
                                viewModel.selectCategory("Spiritual Experts")
                                viewModel.navigateTo(Screen.Explore)
                            }
                        )
                        HorizontalDivider(color = VedikaDivider, thickness = 0.8.dp)

                        ProfileMenuItem(
                            icon = Icons.Default.Translate,
                            title = if (uiState.isHindi) "भाषा बदलें (वर्तमान: हिन्दी)" else "Change Language (Current: English)",
                            onClick = { viewModel.toggleLanguage() }
                        )
                        HorizontalDivider(color = VedikaDivider, thickness = 0.8.dp)

                        ProfileMenuItem(
                            icon = Icons.Default.Info,
                            title = if (uiState.isHindi) "वेदिका के बारे में" else "About VEDIKA",
                            onClick = { viewModel.navigateTo(Screen.Explore) }
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "VEDIKA v1.0.0",
                        fontSize = 13.sp,
                        color = VedikaTextMuted
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "परंपरा, आपके द्वार।",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = VedikaGold
                    )
                }
            }
        }
    }
}

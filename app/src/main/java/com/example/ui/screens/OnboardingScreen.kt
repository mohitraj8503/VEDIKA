package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ui.components.PanditVisualArt
import com.example.ui.components.SacredHavanArtwork
import com.example.ui.components.SamagriVisualArt
import com.example.ui.components.TempleVisualArt
import com.example.ui.theme.*

data class OnboardingStep(
    val title: String,
    val description: String,
    val imageUrl: String
)

@Composable
fun OnboardingScreen(
    onFinish: () -> Unit,
    modifier: Modifier = Modifier
) {
    val steps = listOf(
        OnboardingStep(
            title = "Experience\nAuthentic Vedic\nTraditions",
            description = "Book Puja, Havan, Katha, Temple Darshan and more with trusted Pandits near you.",
            imageUrl = "https://images.unsplash.com/photo-1609137144813-7d9921338f24?auto=format&fit=crop&w=1000&q=80"
        ),
        OnboardingStep(
            title = "Verified &\nExperienced\nPandits",
            description = "Perform your sacred rituals with certified, verified, and deeply experienced Vedic pandits.",
            imageUrl = "https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?auto=format&fit=crop&w=1000&q=80"
        ),
        OnboardingStep(
            title = "All Spiritual\nServices in\nOne App",
            description = "From daily puja to special life-cycle ceremonies — everything organized at your fingertips.",
            imageUrl = "https://images.unsplash.com/photo-1600100397608-f010f443b76a?auto=format&fit=crop&w=1000&q=80"
        ),
        OnboardingStep(
            title = "Find Trusted\nPandits Near You",
            description = "Choose from top rated pandits in your city or sacred pilgrimage hubs with transparent pricing.",
            imageUrl = "https://images.unsplash.com/photo-1605371924599-2d0365da1ae0?auto=format&fit=crop&w=1000&q=80"
        )
    )

    var currentStepIndex by remember { mutableIntStateOf(0) }
    val step = steps[currentStepIndex]

    Scaffold(
        containerColor = VedikaCream,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 14.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Surface(
                    onClick = onFinish,
                    shape = RoundedCornerShape(12.dp),
                    color = VedikaCreamDark,
                    border = BorderStroke(1.dp, VedikaCardBorder)
                ) {
                    Text(
                        text = "Skip",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = VedikaTextDark,
                            fontSize = 14.sp
                        ),
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // High-Resolution Devotional Photo Card with fallback
            Box(
                modifier = Modifier
                    .weight(1.3f)
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
                    .clip(RoundedCornerShape(26.dp))
                    .shadow(8.dp, RoundedCornerShape(26.dp), spotColor = Color(0x18000000))
            ) {
                // Fallback artwork
                when (currentStepIndex) {
                    0 -> SacredHavanArtwork(modifier = Modifier.fillMaxSize(), height = 260.dp)
                    1 -> PanditVisualArt(panditName = "Acharya Vishnu Sharma", modifier = Modifier.fillMaxSize(), cornerRadius = 26.dp)
                    2 -> TempleVisualArt(templeName = "Baba Baidyanath Dham", modifier = Modifier.fillMaxSize(), cornerRadius = 26.dp)
                    else -> SamagriVisualArt(productName = "Complete Puja Samagri Kit", modifier = Modifier.fillMaxSize(), cornerRadius = 26.dp)
                }

                // Real photo overlay
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(step.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = step.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Soft warm bottom gradient
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color(0x50000000))
                            )
                        )
                )
            }

            // Headline & Description (Large prominent typography)
            Column(
                modifier = Modifier
                    .weight(0.9f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = step.title,
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = VedikaTextDark,
                        fontSize = 30.sp,
                        lineHeight = 38.sp
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = step.description,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = VedikaTextMedium,
                        fontSize = 16.sp,
                        lineHeight = 24.sp
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }

            // Bottom Nav with Indicators and Next Button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 36.dp, top = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Page Indicator Dots
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    steps.indices.forEach { index ->
                        Box(
                            modifier = Modifier
                                .size(
                                    width = if (index == currentStepIndex) 30.dp else 10.dp,
                                    height = 10.dp
                                )
                                .clip(CircleShape)
                                .background(
                                    if (index == currentStepIndex) VedikaOrange else VedikaCardBorder
                                )
                        )
                    }
                }

                // Next Button with Icon (54dp height)
                Button(
                    onClick = {
                        if (currentStepIndex < steps.lastIndex) {
                            currentStepIndex++
                        } else {
                            onFinish()
                        }
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = VedikaOrange),
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
                    modifier = Modifier
                        .height(54.dp)
                        .shadow(4.dp, RoundedCornerShape(16.dp), spotColor = Color(0x25E65100))
                ) {
                    Text(
                        text = if (currentStepIndex == steps.lastIndex) "Get Started" else "Next",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

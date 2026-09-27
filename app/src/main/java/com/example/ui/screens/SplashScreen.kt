package com.example.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.TempleBannerArtwork
import com.example.ui.components.VedikaLogoBadge
import com.example.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Pulse animation for spiritual resonance
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.98f,
        targetValue = 1.02f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
        delay(2600)
        onSplashFinished()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable { onSplashFinished() }
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFF9800),
                        Color(0xFFFF6D00),
                        Color(0xFFE65100),
                        Color(0xFF8B2500)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        // Distant temple silhouettes at bottom
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .alpha(0.35f)
        ) {
            TempleBannerArtwork(name = "Temple Horizon", height = 240.dp)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(horizontal = 28.dp)
                .scale(scale)
        ) {
            // Sacred Vedika Emblem
            VedikaLogoBadge(size = 140.dp, showShadow = true)

            Spacer(modifier = Modifier.height(28.dp))

            // Brand Title VEDIKA
            Text(
                text = "VEDIKA",
                fontSize = 42.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Serif,
                letterSpacing = 4.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Sacred Tagline
            Text(
                text = "परंपरा, आपके द्वार।",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFFFF9C4),
                letterSpacing = 1.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Subtitle
            Text(
                text = "India's Vedic & Spiritual\nServices Marketplace",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFFFFE0B2),
                textAlign = TextAlign.Center,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Indicator dots
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(
                    modifier = Modifier
                        .size(24.dp, 6.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                )
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(Color(0x66FFFFFF))
                )
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(Color(0x66FFFFFF))
                )
            }
        }
    }
}

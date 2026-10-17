package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ui.theme.*

@Composable
fun OnboardingScreen(
    onFinish: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(VedikaCream)
    ) {
        // Upper section: Majestic Temple backdrop at sunset with devotee
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.55f)
        ) {
            // Visual Vedic Temple Canvas background
            TempleDevoteeBackdropArt(
                modifier = Modifier.fillMaxSize()
            )

            // Devotional high-res photo blend
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://images.unsplash.com/photo-1544717305-2782549b5136?auto=format&fit=crop&w=1200&q=80")
                    .crossfade(true)
                    .build(),
                contentDescription = "Sacred Temple",
                contentScale = ContentScale.Crop,
                alpha = 0.25f,
                modifier = Modifier.fillMaxSize()
            )

            // Warm gradient fade transitioning softly into cream bottom
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color(0x22FBF8F2),
                                Color(0xCCFBF8F2),
                                VedikaCream
                            ),
                            startY = 150f
                        )
                    )
            )
        }

        // Lower content section: Brand emblem, title, 3 feature capsules, CTA button
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .windowInsetsPadding(WindowInsets.systemBars),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Spacer(modifier = Modifier.weight(0.9f))

            // Triple Flame Lotus Divine Emblem
            TripleFlameLotusEmblem(
                modifier = Modifier.size(72.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // "VEDIKA" Brand Title
            Text(
                text = "VEDIKA",
                style = MaterialTheme.typography.displayMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Serif,
                    letterSpacing = 4.sp,
                    color = Color(0xFF3E2723),
                    fontSize = 36.sp
                )
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Tagline: "Bringing You Closer to Your Faith"
            Text(
                text = "Bringing You Closer\nto Your Faith",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium,
                    color = VedikaTextMedium,
                    fontSize = 17.sp,
                    lineHeight = 24.sp
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Sacred Om Symbol
            Text(
                text = "ॐ",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = VedikaOrangeDark,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 3 Feature Capsule Badges in a horizontal row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                FeaturePillCard(
                    iconText = "🪔",
                    title = "Authentic\nPuja Services",
                    modifier = Modifier.weight(1f)
                )
                FeaturePillCard(
                    iconText = "🧘‍♂️",
                    title = "Verified\nPandits",
                    modifier = Modifier.weight(1f)
                )
                FeaturePillCard(
                    iconText = "🛕",
                    title = "Trusted\nTemples",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Large Radiant Pill CTA Button: "Get Started →"
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .shadow(12.dp, RoundedCornerShape(28.dp), spotColor = Color(0x55FF6B00))
                    .clip(RoundedCornerShape(28.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFFFF6D00),
                                Color(0xFFFF5722),
                                Color(0xFFE64A19)
                            )
                        )
                    )
                    .clickable(onClick = onFinish),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Get Started",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 17.sp,
                            letterSpacing = 0.5.sp
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Arrow",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Secondary Action: "Explore as Guest"
            TextButton(
                onClick = onFinish,
                modifier = Modifier.padding(bottom = 20.dp)
            ) {
                Text(
                    text = "Explore as Guest",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = VedikaOrangeDark,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                )
            }
        }
    }
}

@Composable
private fun FeaturePillCard(
    iconText: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.height(78.dp),
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFF7F2EB),
        border = BorderStroke(1.dp, Color(0xFFEDE4D6))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp, horizontal = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = iconText,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF4E342E),
                    fontSize = 11.sp,
                    lineHeight = 14.sp
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun TripleFlameLotusEmblem(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // Outer radiant aura
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFFFFD54F), Color(0xFFFF9800), Color(0x00FF6D00)),
                    center = Offset(w * 0.5f, h * 0.5f),
                    radius = w * 0.5f
                ),
                radius = w * 0.5f,
                center = Offset(w * 0.5f, h * 0.5f)
            )

            // Center flame
            val centerFlame = Path().apply {
                moveTo(w * 0.50f, h * 0.08f)
                cubicTo(w * 0.40f, h * 0.32f, w * 0.38f, h * 0.58f, w * 0.50f, h * 0.88f)
                cubicTo(w * 0.62f, h * 0.58f, w * 0.60f, h * 0.32f, w * 0.50f, h * 0.08f)
                close()
            }
            drawPath(
                path = centerFlame,
                brush = Brush.verticalGradient(
                    listOf(Color(0xFFFFF9C4), Color(0xFFFFB300), Color(0xFFE65100))
                )
            )

            // Left curved flame
            val leftFlame = Path().apply {
                moveTo(w * 0.50f, h * 0.88f)
                cubicTo(w * 0.28f, h * 0.72f, w * 0.16f, h * 0.48f, w * 0.30f, h * 0.26f)
                cubicTo(w * 0.26f, h * 0.50f, w * 0.40f, h * 0.70f, w * 0.50f, h * 0.88f)
                close()
            }
            drawPath(
                path = leftFlame,
                brush = Brush.verticalGradient(
                    listOf(Color(0xFFFFCA28), Color(0xFFFF7043), Color(0xFFD84315))
                )
            )

            // Right curved flame
            val rightFlame = Path().apply {
                moveTo(w * 0.50f, h * 0.88f)
                cubicTo(w * 0.72f, h * 0.72f, w * 0.84f, h * 0.48f, w * 0.70f, h * 0.26f)
                cubicTo(w * 0.74f, h * 0.50f, w * 0.60f, h * 0.70f, w * 0.50f, h * 0.88f)
                close()
            }
            drawPath(
                path = rightFlame,
                brush = Brush.verticalGradient(
                    listOf(Color(0xFFFFCA28), Color(0xFFFF7043), Color(0xFFD84315))
                )
            )

            // Inner white radiant bindu
            drawCircle(
                color = Color(0xFFFFFDE7),
                radius = w * 0.09f,
                center = Offset(w * 0.50f, h * 0.58f)
            )
        }
    }
}

@Composable
fun TempleDevoteeBackdropArt(
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // 1. Divine twilight sky gradient (warm peach to radiant gold)
        drawRect(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFFFFCC80),
                    Color(0xFFFFE0B2),
                    Color(0xFFFFF3E0),
                    Color(0xFFFBF8F2)
                )
            ),
            size = Size(w, h)
        )

        // 2. Solar radiant halo in sky
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color(0xFFFFFDE7), Color(0xFFFFE082), Color(0x00FFA726)),
                center = Offset(w * 0.5f, h * 0.35f),
                radius = w * 0.48f
            ),
            radius = w * 0.48f,
            center = Offset(w * 0.5f, h * 0.35f)
        )

        // 3. Ancient sandstone Temple Shikhara towers
        // Center main shikhara
        val centerSpire = Path().apply {
            moveTo(w * 0.50f, h * 0.08f)
            lineTo(w * 0.56f, h * 0.28f)
            lineTo(w * 0.62f, h * 0.44f)
            lineTo(w * 0.68f, h * 0.56f)
            lineTo(w * 0.32f, h * 0.56f)
            lineTo(w * 0.38f, h * 0.44f)
            lineTo(w * 0.44f, h * 0.28f)
            close()
        }
        drawPath(
            path = centerSpire,
            brush = Brush.verticalGradient(
                colors = listOf(Color(0xFFD7A76E), Color(0xFFBD8650), Color(0xFF8D5B2F))
            )
        )

        // Saffron Dhwaja (Flag) atop central shikhara
        drawLine(
            color = Color(0xFFBF360C),
            start = Offset(w * 0.50f, h * 0.04f),
            end = Offset(w * 0.50f, h * 0.09f),
            strokeWidth = 3f
        )
        val flag = Path().apply {
            moveTo(w * 0.50f, h * 0.04f)
            lineTo(w * 0.58f, h * 0.06f)
            lineTo(w * 0.50f, h * 0.08f)
            close()
        }
        drawPath(flag, color = Color(0xFFFF6D00))

        // Left sub-shikhara
        val leftSpire = Path().apply {
            moveTo(w * 0.26f, h * 0.20f)
            lineTo(w * 0.34f, h * 0.40f)
            lineTo(w * 0.38f, h * 0.56f)
            lineTo(w * 0.16f, h * 0.56f)
            lineTo(w * 0.20f, h * 0.38f)
            close()
        }
        drawPath(
            path = leftSpire,
            brush = Brush.verticalGradient(
                colors = listOf(Color(0xFFD19F68), Color(0xFF9E6533))
            )
        )

        // Right sub-shikhara
        val rightSpire = Path().apply {
            moveTo(w * 0.74f, h * 0.20f)
            lineTo(w * 0.84f, h * 0.38f)
            lineTo(w * 0.84f, h * 0.56f)
            lineTo(w * 0.62f, h * 0.56f)
            lineTo(w * 0.66f, h * 0.40f)
            close()
        }
        drawPath(
            path = rightSpire,
            brush = Brush.verticalGradient(
                colors = listOf(Color(0xFFD19F68), Color(0xFF9E6533))
            )
        )

        // Temple pillared mandapa base
        drawRect(
            brush = Brush.verticalGradient(
                colors = listOf(Color(0xFFBD8650), Color(0xFF8D5B2F))
            ),
            topLeft = Offset(w * 0.14f, h * 0.56f),
            size = Size(w * 0.72f, h * 0.10f)
        )

        // Sacred Ghat / Steps
        for (i in 0..3) {
            val stepY = h * (0.66f + i * 0.035f)
            val stepW = w * (0.80f + i * 0.06f)
            val stepX = (w - stepW) / 2
            drawRect(
                color = if (i % 2 == 0) Color(0xFFDEB887) else Color(0xFFD2A374),
                topLeft = Offset(stepX, stepY),
                size = Size(stepW, h * 0.035f)
            )
        }

        // River reflection shimmer
        drawRect(
            brush = Brush.verticalGradient(
                colors = listOf(Color(0x88FFE082), Color(0x33FFB74D), Color(0x00FBF8F2))
            ),
            topLeft = Offset(0f, h * 0.80f),
            size = Size(w, h * 0.20f)
        )
    }
}

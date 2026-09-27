package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@Composable
fun SacredHavanArtwork(
    modifier: Modifier = Modifier,
    height: Dp = 180.dp
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF2C1810), Color(0xFF4A1E0D), Color(0xFF1E0C06))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // Warm holy glow around havan
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0x99FFA726), Color(0x44FF7043), Color(0x00D84315)),
                    center = Offset(w * 0.5f, h * 0.55f),
                    radius = w * 0.45f
                ),
                radius = w * 0.45f,
                center = Offset(w * 0.5f, h * 0.55f)
            )

            // Havan Kund (Stepped copper altar)
            // Bottom tier
            drawRect(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF8D4016), Color(0xFFD36829), Color(0xFF8D4016))
                ),
                topLeft = Offset(w * 0.25f, h * 0.72f),
                size = Size(w * 0.5f, h * 0.08f)
            )
            // Middle tier
            drawRect(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF9E481A), Color(0xFFE27433), Color(0xFF9E481A))
                ),
                topLeft = Offset(w * 0.30f, h * 0.65f),
                size = Size(w * 0.4f, h * 0.07f)
            )

            // Sacred Agni Flames
            val mainFlame = Path().apply {
                moveTo(w * 0.5f, h * 0.25f)
                cubicTo(w * 0.42f, h * 0.38f, w * 0.40f, h * 0.52f, w * 0.45f, h * 0.65f)
                lineTo(w * 0.55f, h * 0.65f)
                cubicTo(w * 0.60f, h * 0.52f, w * 0.58f, h * 0.38f, w * 0.5f, h * 0.25f)
                close()
            }
            drawPath(
                path = mainFlame,
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFF9C4), Color(0xFFFFCA28), Color(0xFFFF7043))
                )
            )

            // Left secondary flame
            val leftFlame = Path().apply {
                moveTo(w * 0.44f, h * 0.36f)
                cubicTo(w * 0.36f, h * 0.46f, w * 0.36f, h * 0.58f, w * 0.42f, h * 0.65f)
                lineTo(w * 0.48f, h * 0.65f)
                cubicTo(w * 0.44f, h * 0.56f, w * 0.42f, h * 0.46f, w * 0.44f, h * 0.36f)
                close()
            }
            drawPath(
                path = leftFlame,
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFEE58), Color(0xFFFF8A65))
                )
            )

            // Right secondary flame
            val rightFlame = Path().apply {
                moveTo(w * 0.56f, h * 0.36f)
                cubicTo(w * 0.64f, h * 0.46f, w * 0.64f, h * 0.58f, w * 0.58f, h * 0.65f)
                lineTo(w * 0.52f, h * 0.65f)
                cubicTo(w * 0.56f, h * 0.56f, w * 0.58f, h * 0.46f, w * 0.56f, h * 0.36f)
                close()
            }
            drawPath(
                path = rightFlame,
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFEE58), Color(0xFFFF8A65))
                )
            )

            // Spark embers
            drawCircle(Color(0xFFFFD54F), 3f, Offset(w * 0.46f, h * 0.20f))
            drawCircle(Color(0xFFFFB74D), 2.5f, Offset(w * 0.53f, h * 0.17f))
            drawCircle(Color(0xFFFF7043), 2f, Offset(w * 0.40f, h * 0.26f))
            drawCircle(Color(0xFFFFCA28), 3f, Offset(w * 0.60f, h * 0.24f))
        }
    }
}

@Composable
fun PanditAvatar(
    name: String,
    modifier: Modifier = Modifier,
    size: Dp = 64.dp
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFF3E0), Color(0xFFFFE0B2), Color(0xFFFFCC80))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = this.size.width
            val h = this.size.height

            // Saffron angavastram drape
            val cloth = Path().apply {
                moveTo(0f, h)
                lineTo(w * 0.25f, h * 0.65f)
                lineTo(w * 0.75f, h * 0.65f)
                lineTo(w, h)
                close()
            }
            drawPath(cloth, color = Color(0xFFFF7043))

            // Pandit neck & head
            drawCircle(
                color = Color(0xFFECC29C),
                radius = w * 0.24f,
                center = Offset(w * 0.5f, h * 0.42f)
            )

            // Tilak & Chandan Tripundra on forehead
            drawLine(
                color = Color(0xFFFFF9C4),
                start = Offset(w * 0.42f, h * 0.32f),
                end = Offset(w * 0.58f, h * 0.32f),
                strokeWidth = 2.5f
            )
            drawLine(
                color = Color(0xFFD32F2F),
                start = Offset(w * 0.50f, h * 0.28f),
                end = Offset(w * 0.50f, h * 0.37f),
                strokeWidth = 3f,
                cap = StrokeCap.Round
            )

            // Sacred Rudraksha bead chain
            for (i in 0..6) {
                val beadX = w * (0.32f + i * 0.06f)
                val beadY = h * (0.64f + kotlin.math.sin(i * 0.5f) * 0.04f)
                drawCircle(color = Color(0xFF5D4037), radius = 2.5f, center = Offset(beadX, beadY))
            }
        }
    }
}

@Composable
fun TempleBannerArtwork(
    name: String,
    modifier: Modifier = Modifier,
    height: Dp = 150.dp
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFE65100), Color(0xFFEF6C00), Color(0xFFFFA726))
                )
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // Glowing holy morning sun
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFFFFF9C4), Color(0xFFFFE082), Color(0x00FFA726)),
                    center = Offset(w * 0.75f, h * 0.35f),
                    radius = w * 0.35f
                ),
                radius = w * 0.35f,
                center = Offset(w * 0.75f, h * 0.35f)
            )

            // Multiple Shikhara Temple Silhouette
            val templePath = Path().apply {
                moveTo(w * 0.05f, h)
                lineTo(w * 0.15f, h * 0.60f)
                lineTo(w * 0.22f, h * 0.40f)
                lineTo(w * 0.25f, h * 0.25f) // Kalash tip 1
                lineTo(w * 0.28f, h * 0.40f)
                lineTo(w * 0.35f, h * 0.55f)
                lineTo(w * 0.42f, h * 0.30f)
                lineTo(w * 0.48f, h * 0.15f) // Main tallest central Shikhara
                lineTo(w * 0.54f, h * 0.30f)
                lineTo(w * 0.62f, h * 0.48f)
                lineTo(w * 0.70f, h * 0.35f)
                lineTo(w * 0.75f, h * 0.22f) // Kalash tip 3
                lineTo(w * 0.80f, h * 0.42f)
                lineTo(w * 0.90f, h * 0.65f)
                lineTo(w * 0.98f, h)
                close()
            }
            drawPath(
                path = templePath,
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF6D2409), Color(0xFF3B1204))
                )
            )

            // Saffron Flag at main shikhara
            val flag = Path().apply {
                moveTo(w * 0.48f, h * 0.15f)
                lineTo(w * 0.55f, h * 0.12f)
                lineTo(w * 0.48f, h * 0.08f)
                close()
            }
            drawPath(flag, color = Color(0xFFFF5722))
        }
    }
}

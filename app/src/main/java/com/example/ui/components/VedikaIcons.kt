package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.model.CategoryIconType
import com.example.ui.theme.*

@Composable
fun CategoryVectorIcon(
    iconType: CategoryIconType,
    modifier: Modifier = Modifier,
    tint: Color = VedikaOrangeDark
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        when (iconType) {
            CategoryIconType.PANDIT_BOOKING -> {
                // Sacred Acharya / Priest icon: Head, tilak, and traditional angavastram drape
                // Head
                drawCircle(color = tint, radius = w * 0.22f, center = Offset(w * 0.5f, h * 0.30f))
                // Tilak
                drawLine(
                    color = Color(0xFFFFF9C4),
                    start = Offset(w * 0.5f, h * 0.20f),
                    end = Offset(w * 0.5f, h * 0.32f),
                    strokeWidth = w * 0.06f,
                    cap = StrokeCap.Round
                )
                // Shoulders & Angavastram drape
                val body = Path().apply {
                    moveTo(w * 0.15f, h * 0.90f)
                    cubicTo(w * 0.15f, h * 0.60f, w * 0.30f, h * 0.52f, w * 0.50f, h * 0.52f)
                    cubicTo(w * 0.70f, h * 0.52f, w * 0.85f, h * 0.60f, w * 0.85f, h * 0.90f)
                    close()
                }
                drawPath(body, color = tint)
                // Sacred Janeu diagonal thread
                drawLine(
                    color = Color.White,
                    start = Offset(w * 0.34f, h * 0.60f),
                    end = Offset(w * 0.68f, h * 0.88f),
                    strokeWidth = w * 0.045f,
                    cap = StrokeCap.Round
                )
            }

            CategoryIconType.PUJA_HAVAN -> {
                // Sacred Havan Kund & Flame
                // Stepped Havan Altar base
                drawRoundRect(
                    color = tint,
                    topLeft = Offset(w * 0.18f, h * 0.74f),
                    size = Size(w * 0.64f, h * 0.16f),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(4f, 4f)
                )
                drawRoundRect(
                    color = tint,
                    topLeft = Offset(w * 0.26f, h * 0.64f),
                    size = Size(w * 0.48f, h * 0.12f),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(3f, 3f)
                )
                // Pavitra Agni Flames
                val flame = Path().apply {
                    moveTo(w * 0.50f, h * 0.14f)
                    cubicTo(w * 0.36f, h * 0.30f, w * 0.34f, h * 0.46f, w * 0.42f, h * 0.62f)
                    lineTo(w * 0.58f, h * 0.62f)
                    cubicTo(w * 0.66f, h * 0.46f, w * 0.64f, h * 0.30f, w * 0.50f, h * 0.14f)
                    close()
                }
                drawPath(flame, color = tint)
                // Inner golden flame
                val innerFlame = Path().apply {
                    moveTo(w * 0.50f, h * 0.32f)
                    cubicTo(w * 0.44f, h * 0.42f, w * 0.44f, h * 0.52f, w * 0.48f, h * 0.62f)
                    lineTo(w * 0.52f, h * 0.62f)
                    cubicTo(w * 0.56f, h * 0.52f, w * 0.56f, h * 0.42f, w * 0.50f, h * 0.32f)
                    close()
                }
                drawPath(innerFlame, color = Color(0xFFFFD54F))
            }

            CategoryIconType.KATHA_BHAJAN -> {
                // Sacred Scriptural Granth / Pothi & Musical Swar
                val bookPath = Path().apply {
                    // Left page
                    moveTo(w * 0.50f, h * 0.34f)
                    cubicTo(w * 0.36f, h * 0.30f, w * 0.20f, h * 0.34f, w * 0.14f, h * 0.38f)
                    lineTo(w * 0.14f, h * 0.78f)
                    cubicTo(w * 0.20f, h * 0.74f, w * 0.36f, h * 0.70f, w * 0.50f, h * 0.74f)
                    close()
                }
                drawPath(bookPath, color = tint)
                val rightBookPath = Path().apply {
                    // Right page
                    moveTo(w * 0.50f, h * 0.34f)
                    cubicTo(w * 0.64f, h * 0.30f, w * 0.80f, h * 0.34f, w * 0.86f, h * 0.38f)
                    lineTo(w * 0.86f, h * 0.78f)
                    cubicTo(w * 0.80f, h * 0.74f, w * 0.64f, h * 0.70f, w * 0.50f, h * 0.74f)
                    close()
                }
                drawPath(rightBookPath, color = tint)
                // Center spine binding
                drawRect(color = Color(0xFFFFF9C4), topLeft = Offset(w * 0.48f, h * 0.34f), size = Size(w * 0.04f, h * 0.42f))
                // Top musical note
                drawCircle(color = tint, radius = w * 0.06f, center = Offset(w * 0.72f, h * 0.22f))
                drawLine(color = tint, start = Offset(w * 0.77f, h * 0.22f), end = Offset(w * 0.77f, h * 0.12f), strokeWidth = w * 0.04f)
            }

            CategoryIconType.TEMPLE_DARSHAN -> {
                // Mandir / Temple Architecture with Shikhara, Kalash, and Dhwaja
                val temple = Path().apply {
                    // Base steps
                    moveTo(w * 0.12f, h * 0.90f)
                    lineTo(w * 0.88f, h * 0.90f)
                    lineTo(w * 0.84f, h * 0.78f)
                    lineTo(w * 0.16f, h * 0.78f)
                    close()
                    // Mandapa walls
                    moveTo(w * 0.22f, h * 0.78f)
                    lineTo(w * 0.78f, h * 0.78f)
                    lineTo(w * 0.78f, h * 0.52f)
                    lineTo(w * 0.22f, h * 0.52f)
                    close()
                    // Central Shikhara spire
                    moveTo(w * 0.26f, h * 0.52f)
                    lineTo(w * 0.50f, h * 0.16f)
                    lineTo(w * 0.74f, h * 0.52f)
                    close()
                }
                drawPath(temple, color = tint)
                // Sanctum doorway arch
                val door = Path().apply {
                    moveTo(w * 0.40f, h * 0.78f)
                    lineTo(w * 0.40f, h * 0.62f)
                    cubicTo(w * 0.40f, h * 0.56f, w * 0.60f, h * 0.56f, w * 0.60f, h * 0.62f)
                    lineTo(w * 0.60f, h * 0.78f)
                    close()
                }
                drawPath(door, color = Color(0xFFFFF3E0))
                // Flag at top
                drawLine(color = tint, start = Offset(w * 0.50f, h * 0.16f), end = Offset(w * 0.50f, h * 0.06f), strokeWidth = w * 0.04f)
                val flag = Path().apply {
                    moveTo(w * 0.50f, h * 0.06f)
                    lineTo(w * 0.68f, h * 0.10f)
                    lineTo(w * 0.50f, h * 0.14f)
                    close()
                }
                drawPath(flag, color = Color(0xFFFF5722))
            }

            CategoryIconType.RELIGIOUS_EVENTS -> {
                // Event Festive Chhatra / Mandap canopy
                val canopy = Path().apply {
                    moveTo(w * 0.15f, h * 0.48f)
                    quadraticBezierTo(w * 0.50f, h * 0.15f, w * 0.85f, h * 0.48f)
                    lineTo(w * 0.75f, h * 0.55f)
                    lineTo(w * 0.25f, h * 0.55f)
                    close()
                }
                drawPath(canopy, color = tint)
                // Decorative pillars
                drawLine(color = tint, start = Offset(w * 0.28f, h * 0.55f), end = Offset(w * 0.28f, h * 0.88f), strokeWidth = w * 0.06f, cap = StrokeCap.Round)
                drawLine(color = tint, start = Offset(w * 0.72f, h * 0.55f), end = Offset(w * 0.72f, h * 0.88f), strokeWidth = w * 0.06f, cap = StrokeCap.Round)
                // Auspicious Kalash in center
                drawCircle(color = tint, radius = w * 0.10f, center = Offset(w * 0.50f, h * 0.74f))
            }

            CategoryIconType.SANSKAR -> {
                // Child blessings / Sacred Life-Cycle ceremony (Lotus & Baby Swaddle symbol)
                // Base lotus petals
                val lotusLeft = Path().apply {
                    moveTo(w * 0.50f, h * 0.86f)
                    cubicTo(w * 0.25f, h * 0.86f, w * 0.15f, h * 0.70f, w * 0.28f, h * 0.60f)
                    cubicTo(w * 0.36f, h * 0.72f, w * 0.44f, h * 0.80f, w * 0.50f, h * 0.86f)
                    close()
                }
                drawPath(lotusLeft, color = tint)
                val lotusRight = Path().apply {
                    moveTo(w * 0.50f, h * 0.86f)
                    cubicTo(w * 0.75f, h * 0.86f, w * 0.85f, h * 0.70f, w * 0.72f, h * 0.60f)
                    cubicTo(w * 0.64f, h * 0.72f, w * 0.56f, h * 0.80f, w * 0.50f, h * 0.86f)
                    close()
                }
                drawPath(lotusRight, color = tint)
                // Central auspicious Kalash & Coconut
                drawCircle(color = tint, radius = w * 0.18f, center = Offset(w * 0.50f, h * 0.56f))
                // Tilak mark
                drawCircle(color = Color(0xFFD32F2F), radius = w * 0.04f, center = Offset(w * 0.50f, h * 0.54f))
                // Mango leaves
                val leaf = Path().apply {
                    moveTo(w * 0.50f, h * 0.38f)
                    lineTo(w * 0.42f, h * 0.24f)
                    lineTo(w * 0.50f, h * 0.14f)
                    lineTo(w * 0.58f, h * 0.24f)
                    close()
                }
                drawPath(leaf, color = tint)
            }

            CategoryIconType.PUJA_SAMAGRI -> {
                // Sacred Puja Thali with Diya, Kalash, and Flowers
                // Thali platter
                drawOval(
                    color = tint,
                    topLeft = Offset(w * 0.12f, h * 0.48f),
                    size = Size(w * 0.76f, h * 0.40f)
                )
                // Inner rim
                drawOval(
                    color = Color(0xFFFFF8E1),
                    topLeft = Offset(w * 0.18f, h * 0.52f),
                    size = Size(w * 0.64f, h * 0.32f)
                )
                // Brass Kalash on thali
                val kalash = Path().apply {
                    moveTo(w * 0.40f, h * 0.58f)
                    cubicTo(w * 0.34f, h * 0.42f, w * 0.38f, h * 0.32f, w * 0.44f, h * 0.26f)
                    lineTo(w * 0.56f, h * 0.26f)
                    cubicTo(w * 0.62f, h * 0.32f, w * 0.66f, h * 0.42f, w * 0.60f, h * 0.58f)
                    close()
                }
                drawPath(kalash, color = tint)
                // Coconut top
                drawCircle(color = tint, radius = w * 0.09f, center = Offset(w * 0.50f, h * 0.22f))
                // Small Diya flame on right
                drawCircle(color = Color(0xFFFF9800), radius = w * 0.06f, center = Offset(w * 0.68f, h * 0.64f))
            }

            CategoryIconType.SPIRITUAL_EXPERTS -> {
                // Jyotish Chakra / Astrological Star / Wisdom symbol
                val cx = w * 0.5f
                val cy = h * 0.5f
                val r = w * 0.36f
                // Sacred ring
                drawCircle(color = tint, radius = r, style = Stroke(width = w * 0.06f))
                // 8 Directional rays / spokes
                for (i in 0 until 8) {
                    val angle = (i * Math.PI / 4).toFloat()
                    val cos = kotlin.math.cos(angle)
                    val sin = kotlin.math.sin(angle)
                    drawLine(
                        color = tint,
                        start = Offset(cx, cy),
                        end = Offset(cx + r * cos, cy + r * sin),
                        strokeWidth = w * 0.05f
                    )
                }
                // Center Bindu
                drawCircle(color = Color(0xFFFFD54F), radius = w * 0.12f, center = Offset(cx, cy))
            }
        }
    }
}

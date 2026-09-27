package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

/**
 * Ultra-lightweight, zero-latency, authentic Indian Vedic visual artwork engine.
 * Renders instantly (0ms network delay, 0KB bandwidth) with 100% relevant,
 * culturally accurate visual representations for services, pandits, temples,
 * katha-bhajans, and sacred samagri.
 */

@Composable
fun ServiceVisualArt(
    serviceTitle: String,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 16.dp
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(getServiceBackgroundGradient(serviceTitle)),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            when {
                serviceTitle.contains("Griha Pravesh", ignoreCase = true) -> {
                    drawGrihaPraveshArt(w, h)
                }
                serviceTitle.contains("Satyanarayan", ignoreCase = true) -> {
                    drawSatyanarayanArt(w, h)
                }
                serviceTitle.contains("Rudrabhishek", ignoreCase = true) -> {
                    drawRudrabhishekArt(w, h)
                }
                serviceTitle.contains("Mundan", ignoreCase = true) -> {
                    drawMundanArt(w, h)
                }
                serviceTitle.contains("Namkaran", ignoreCase = true) -> {
                    drawNamkaranArt(w, h)
                }
                serviceTitle.contains("Vastu", ignoreCase = true) -> {
                    drawVastuArt(w, h)
                }
                else -> {
                    drawGenericHavanArt(w, h)
                }
            }
        }
    }
}

private fun getServiceBackgroundGradient(title: String): Brush {
    return when {
        title.contains("Rudrabhishek", ignoreCase = true) -> Brush.verticalGradient(
            colors = listOf(Color(0xFF1A237E), Color(0xFF283593), Color(0xFF0D1B2A))
        )
        title.contains("Satyanarayan", ignoreCase = true) -> Brush.verticalGradient(
            colors = listOf(Color(0xFFE65100), Color(0xFFF57C00), Color(0xFFFFB300))
        )
        title.contains("Griha Pravesh", ignoreCase = true) -> Brush.verticalGradient(
            colors = listOf(Color(0xFFBF360C), Color(0xFFE64A19), Color(0xFFFF8A65))
        )
        title.contains("Mundan", ignoreCase = true) -> Brush.verticalGradient(
            colors = listOf(Color(0xFF4A148C), Color(0xFF6A1B9A), Color(0xFF8E24AA))
        )
        title.contains("Namkaran", ignoreCase = true) -> Brush.verticalGradient(
            colors = listOf(Color(0xFF004D40), Color(0xFF00695C), Color(0xFF00897B))
        )
        title.contains("Vastu", ignoreCase = true) -> Brush.verticalGradient(
            colors = listOf(Color(0xFF3E2723), Color(0xFF4E342E), Color(0xFF6D4C41))
        )
        else -> Brush.verticalGradient(
            colors = listOf(Color(0xFFBF360C), Color(0xFFE65100), Color(0xFFD84315))
        )
    }
}

// 1. Griha Pravesh Art: Auspicious Kalash with mango leaves & coconut with swastika + glowing lamps
private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawGrihaPraveshArt(w: Float, h: Float) {
    // Warm holy glow
    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(Color(0xFFFFD54F), Color(0xFFFF9800), Color(0x00FF5722)),
            center = Offset(w * 0.5f, h * 0.55f),
            radius = w * 0.45f
        ),
        radius = w * 0.45f,
        center = Offset(w * 0.5f, h * 0.55f)
    )

    // Base Brass Kalash body
    val kalashCenter = Offset(w * 0.5f, h * 0.68f)
    val kalashRadius = w * 0.20f
    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(Color(0xFFFFE082), Color(0xFFFFB300), Color(0xFFFF8F00), Color(0xFFB26A00)),
            center = Offset(kalashCenter.x - kalashRadius * 0.3f, kalashCenter.y - kalashRadius * 0.3f),
            radius = kalashRadius * 1.2f
        ),
        radius = kalashRadius,
        center = kalashCenter
    )

    // Kalash rim / neck
    drawRoundRect(
        brush = Brush.horizontalGradient(
            colors = listOf(Color(0xFFFFA000), Color(0xFFFFE57F), Color(0xFFFFA000))
        ),
        topLeft = Offset(w * 0.38f, h * 0.48f),
        size = Size(w * 0.24f, h * 0.08f),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(6f, 6f)
    )

    // Red sacred mauli thread around Kalash neck
    drawLine(
        color = Color(0xFFD32F2F),
        start = Offset(w * 0.38f, h * 0.52f),
        end = Offset(w * 0.62f, h * 0.52f),
        strokeWidth = 5f
    )

    // 5 Mango leaves (Aam ke patte) fan out
    val leafColor = Color(0xFF2E7D32)
    val leafLight = Color(0xFF4CAF50)

    // Left outer leaf
    val leftLeaf = Path().apply {
        moveTo(w * 0.40f, h * 0.48f)
        cubicTo(w * 0.22f, h * 0.42f, w * 0.18f, h * 0.30f, w * 0.25f, h * 0.22f)
        cubicTo(w * 0.32f, h * 0.30f, w * 0.38f, h * 0.38f, w * 0.42f, h * 0.48f)
        close()
    }
    drawPath(leftLeaf, brush = Brush.linearGradient(listOf(leafLight, leafColor)))

    // Right outer leaf
    val rightLeaf = Path().apply {
        moveTo(w * 0.60f, h * 0.48f)
        cubicTo(w * 0.78f, h * 0.42f, w * 0.82f, h * 0.30f, w * 0.75f, h * 0.22f)
        cubicTo(w * 0.68f, h * 0.30f, w * 0.62f, h * 0.38f, w * 0.58f, h * 0.48f)
        close()
    }
    drawPath(rightLeaf, brush = Brush.linearGradient(listOf(leafLight, leafColor)))

    // Central leaves
    val centerLeftLeaf = Path().apply {
        moveTo(w * 0.44f, h * 0.48f)
        cubicTo(w * 0.32f, h * 0.35f, w * 0.34f, h * 0.22f, w * 0.42f, h * 0.15f)
        cubicTo(w * 0.46f, h * 0.25f, w * 0.47f, h * 0.36f, w * 0.46f, h * 0.48f)
        close()
    }
    drawPath(centerLeftLeaf, brush = Brush.linearGradient(listOf(leafLight, leafColor)))

    val centerRightLeaf = Path().apply {
        moveTo(w * 0.56f, h * 0.48f)
        cubicTo(w * 0.68f, h * 0.35f, w * 0.66f, h * 0.22f, w * 0.58f, h * 0.15f)
        cubicTo(w * 0.54f, h * 0.25f, w * 0.53f, h * 0.36f, w * 0.54f, h * 0.48f)
        close()
    }
    drawPath(centerRightLeaf, brush = Brush.linearGradient(listOf(leafLight, leafColor)))

    // Auspicious Coconut (Nariyal) sitting on top
    val coconutCenter = Offset(w * 0.5f, h * 0.38f)
    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(Color(0xFF8D6E63), Color(0xFF5D4037), Color(0xFF3E2723)),
            center = Offset(coconutCenter.x - 5f, coconutCenter.y - 5f),
            radius = w * 0.13f
        ),
        radius = w * 0.12f,
        center = coconutCenter
    )

    // Red chunri draping over coconut
    val redChunri = Path().apply {
        moveTo(w * 0.42f, h * 0.40f)
        cubicTo(w * 0.46f, h * 0.30f, w * 0.54f, h * 0.30f, w * 0.58f, h * 0.40f)
        lineTo(w * 0.50f, h * 0.43f)
        close()
    }
    drawPath(redChunri, color = Color(0xFFD32F2F))

    // Golden Swastika symbol on Kalash
    val scX = kalashCenter.x
    val scY = kalashCenter.y
    val arm = w * 0.05f
    val strokeW = 4f
    val swastikaColor = Color(0xFFD32F2F)
    drawLine(swastikaColor, Offset(scX - arm, scY), Offset(scX + arm, scY), strokeWidth = strokeW)
    drawLine(swastikaColor, Offset(scX, scY - arm), Offset(scX, scY + arm), strokeWidth = strokeW)
    drawLine(swastikaColor, Offset(scX - arm, scY), Offset(scX - arm, scY - arm * 0.7f), strokeWidth = strokeW)
    drawLine(swastikaColor, Offset(scX + arm, scY), Offset(scX + arm, scY + arm * 0.7f), strokeWidth = strokeW)
    drawLine(swastikaColor, Offset(scX, scY - arm), Offset(scX + arm * 0.7f, scY - arm), strokeWidth = strokeW)
    drawLine(swastikaColor, Offset(scX, scY + arm), Offset(scX - arm * 0.7f, scY + arm), strokeWidth = strokeW)
}

// 2. Satyanarayan Katha Art: Lord Vishnu sacred altar, Granth, Tulsi, Diya & golden aura
private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawSatyanarayanArt(w: Float, h: Float) {
    // Divine Sudarshan / Golden solar aura
    for (i in 0..11) {
        val angle = i * (Math.PI * 2 / 12)
        val x2 = (w * 0.5f + Math.cos(angle) * w * 0.42f).toFloat()
        val y2 = (h * 0.45f + Math.sin(angle) * h * 0.42f).toFloat()
        drawLine(
            color = Color(0x33FFD54F),
            start = Offset(w * 0.5f, h * 0.45f),
            end = Offset(x2, y2),
            strokeWidth = 3f
        )
    }

    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(Color(0xFFFFF9C4), Color(0xFFFFD54F), Color(0x00FF8F00)),
            center = Offset(w * 0.5f, h * 0.45f),
            radius = w * 0.35f
        ),
        radius = w * 0.35f,
        center = Offset(w * 0.5f, h * 0.45f)
    )

    // Sacred Pothi / Granth Book on Vyas Peeth
    val bookLeft = Path().apply {
        moveTo(w * 0.22f, h * 0.62f)
        lineTo(w * 0.48f, h * 0.68f)
        lineTo(w * 0.48f, h * 0.82f)
        lineTo(w * 0.22f, h * 0.76f)
        close()
    }
    drawPath(bookLeft, color = Color(0xFFFFE082))

    val bookRight = Path().apply {
        moveTo(w * 0.78f, h * 0.62f)
        lineTo(w * 0.52f, h * 0.68f)
        lineTo(w * 0.52f, h * 0.82f)
        lineTo(w * 0.78f, h * 0.76f)
        close()
    }
    drawPath(bookRight, color = Color(0xFFFFD54F))

    // Red sacred silk wrap
    drawLine(
        color = Color(0xFFD32F2F),
        start = Offset(w * 0.48f, h * 0.68f),
        end = Offset(w * 0.52f, h * 0.68f),
        strokeWidth = 8f
    )

    // Peacock feather (Mor Pankh) atop the sacred Granth
    val featherPath = Path().apply {
        moveTo(w * 0.50f, h * 0.65f)
        cubicTo(w * 0.48f, h * 0.50f, w * 0.54f, h * 0.38f, w * 0.62f, h * 0.28f)
        cubicTo(w * 0.68f, h * 0.34f, w * 0.62f, h * 0.48f, w * 0.50f, h * 0.65f)
        close()
    }
    drawPath(featherPath, brush = Brush.radialGradient(
        colors = listOf(Color(0xFF0091EA), Color(0xFF004D40), Color(0xFF00C853)),
        center = Offset(w * 0.58f, h * 0.34f),
        radius = w * 0.12f
    ))
    // Peacock eye
    drawCircle(Color(0xFFFFD600), w * 0.03f, Offset(w * 0.58f, h * 0.34f))
    drawCircle(Color(0xFF0D47A1), w * 0.015f, Offset(w * 0.58f, h * 0.34f))

    // Glowing Diya on the left
    drawRoundRect(
        color = Color(0xFFD84315),
        topLeft = Offset(w * 0.12f, h * 0.72f),
        size = Size(w * 0.14f, h * 0.06f),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(10f, 10f)
    )
    val flame = Path().apply {
        moveTo(w * 0.19f, h * 0.72f)
        cubicTo(w * 0.16f, h * 0.66f, w * 0.17f, h * 0.58f, w * 0.19f, h * 0.54f)
        cubicTo(w * 0.21f, h * 0.58f, w * 0.22f, h * 0.66f, w * 0.19f, h * 0.72f)
        close()
    }
    drawPath(flame, brush = Brush.verticalGradient(listOf(Color(0xFFFFF59D), Color(0xFFFF9800))))
}

// 3. Rudrabhishek Art: Sacred Shiva Lingam, Bilva Patra, Milk Stream & Trishul with Damru
private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawRudrabhishekArt(w: Float, h: Float) {
    // Divine deep blue & golden cosmic glow
    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(Color(0x8080D8FF), Color(0x4040C4FF), Color(0x0000B0FF)),
            center = Offset(w * 0.5f, h * 0.55f),
            radius = w * 0.45f
        ),
        radius = w * 0.45f,
        center = Offset(w * 0.5f, h * 0.55f)
    )

    // Shiva Linga base (Yoni Peetham)
    val basePeeth = Path().apply {
        moveTo(w * 0.20f, h * 0.75f)
        lineTo(w * 0.80f, h * 0.75f)
        lineTo(w * 0.85f, h * 0.84f)
        lineTo(w * 0.15f, h * 0.84f)
        close()
    }
    drawPath(basePeeth, brush = Brush.verticalGradient(listOf(Color(0xFF424242), Color(0xFF212121))))

    // Linga cylindrical body
    val lingaWidth = w * 0.28f
    val lingaHeight = h * 0.38f
    val lingaLeft = (w - lingaWidth) / 2
    val lingaTop = h * 0.40f

    drawRoundRect(
        brush = Brush.horizontalGradient(
            colors = listOf(Color(0xFF1E1E1E), Color(0xFF424242), Color(0xFF616161), Color(0xFF212121))
        ),
        topLeft = Offset(lingaLeft, lingaTop),
        size = Size(lingaWidth, lingaHeight),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(lingaWidth / 2, lingaWidth / 2)
    )

    // Tripundra (Three holy lines of sacred ash / Vibhuti)
    val tpLeft = w * 0.41f
    val tpRight = w * 0.59f
    for (i in 0..2) {
        val y = h * (0.48f + i * 0.025f)
        drawLine(
            color = Color(0xFFE0E0E0),
            start = Offset(tpLeft, y),
            end = Offset(tpRight, y),
            strokeWidth = 3f,
            cap = StrokeCap.Round
        )
    }

    // Red Bindu (Kumkum) in center of Tripundra
    drawCircle(
        color = Color(0xFFD32F2F),
        radius = 4.5f,
        center = Offset(w * 0.50f, h * 0.505f)
    )

    // Continuous divine stream of holy Ganga milk abhishek from Dharapatra above
    drawLine(
        brush = Brush.verticalGradient(
            colors = listOf(Color(0xCCFFFFFF), Color(0xEEFFFFFF), Color(0x99B3E5FC))
        ),
        start = Offset(w * 0.50f, h * 0.18f),
        end = Offset(w * 0.50f, h * 0.40f),
        strokeWidth = 4f
    )

    // Dharapatra copper pot above
    val dharaPot = Path().apply {
        moveTo(w * 0.42f, h * 0.12f)
        lineTo(w * 0.58f, h * 0.12f)
        lineTo(w * 0.54f, h * 0.18f)
        lineTo(w * 0.46f, h * 0.18f)
        close()
    }
    drawPath(dharaPot, color = Color(0xFFFFB300))

    // Green Bilva Patra (Bel Patra leaf) on the Lingam
    val belPatraCenter = Offset(w * 0.50f, h * 0.42f)
    drawCircle(Color(0xFF2E7D32), 6f, Offset(belPatraCenter.x - 7f, belPatraCenter.y))
    drawCircle(Color(0xFF2E7D32), 6f, Offset(belPatraCenter.x + 7f, belPatraCenter.y))
    drawCircle(Color(0xFF388E3C), 7f, Offset(belPatraCenter.x, belPatraCenter.y - 7f))

    // Golden Trishul silhouette on the right side
    val triX = w * 0.82f
    drawLine(Color(0xFFFFD54F), Offset(triX, h * 0.28f), Offset(triX, h * 0.85f), strokeWidth = 3.5f)
    // Trishul prongs
    val trishulProngs = Path().apply {
        moveTo(triX, h * 0.22f)
        lineTo(triX, h * 0.32f)
        moveTo(triX - 14f, h * 0.25f)
        cubicTo(triX - 14f, h * 0.32f, triX, h * 0.32f, triX, h * 0.32f)
        cubicTo(triX, h * 0.32f, triX + 14f, h * 0.32f, triX + 14f, h * 0.25f)
    }
    drawPath(trishulProngs, color = Color(0xFFFFD54F), style = Stroke(width = 3.5f))
}

// 4. Mundan Sanskar Art: Auspicious child samskara altar with turmeric bowl, sandalwood, brass diya & garland
private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawMundanArt(w: Float, h: Float) {
    // Soft auspicious purple & warm gold circle
    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(Color(0xFFFFE082), Color(0xFFFFB300), Color(0x00FF8F00)),
            center = Offset(w * 0.5f, h * 0.5f),
            radius = w * 0.40f
        ),
        radius = w * 0.40f,
        center = Offset(w * 0.5f, h * 0.5f)
    )

    // Ceremonial Brass Katori with pure Haldi (Turmeric) and Sandalwood
    val bowlCenter = Offset(w * 0.5f, h * 0.65f)
    drawRoundRect(
        brush = Brush.horizontalGradient(
            colors = listOf(Color(0xFFFFB300), Color(0xFFFFE082), Color(0xFFFF8F00))
        ),
        topLeft = Offset(w * 0.32f, h * 0.58f),
        size = Size(w * 0.36f, h * 0.16f),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(18f, 18f)
    )
    // Turmeric paste inside
    drawCircle(
        color = Color(0xFFFFC107),
        radius = w * 0.14f,
        center = Offset(bowlCenter.x, bowlCenter.y - 10f)
    )

    // Sacred scissors with red mauli
    val scisLeft = Offset(w * 0.24f, h * 0.42f)
    val scisRight = Offset(w * 0.42f, h * 0.52f)
    drawLine(Color(0xFFE0E0E0), scisLeft, scisRight, strokeWidth = 4f)
    drawLine(Color(0xFFE0E0E0), Offset(w * 0.42f, h * 0.42f), Offset(w * 0.24f, h * 0.52f), strokeWidth = 4f)
    // Red thread tie
    drawCircle(Color(0xFFD32F2F), 6f, Offset(w * 0.33f, h * 0.47f))

    // Marigold flower garland (Genda phool)
    for (i in 0..7) {
        val gx = w * (0.18f + i * 0.09f)
        val gy = h * (0.82f - kotlin.math.sin(i * 0.45f) * 0.06f)
        val col = if (i % 2 == 0) Color(0xFFFF9800) else Color(0xFFFFEB3B)
        drawCircle(color = col, radius = w * 0.038f, center = Offset(gx, gy))
    }
}

// 5. Namkaran Sanskar Art: Astrological scroll, Peacock feather, cradle motif & swastika
private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawNamkaranArt(w: Float, h: Float) {
    // Holy green & gold Vedic radiance
    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(Color(0xFF80CBC4), Color(0xFF00897B), Color(0x00004D40)),
            center = Offset(w * 0.5f, h * 0.5f),
            radius = w * 0.42f
        ),
        radius = w * 0.42f,
        center = Offset(w * 0.5f, h * 0.5f)
    )

    // Parchment / Kundali Nakshatra scroll
    val scrollPath = Path().apply {
        moveTo(w * 0.24f, h * 0.32f)
        lineTo(w * 0.76f, h * 0.32f)
        lineTo(w * 0.72f, h * 0.76f)
        lineTo(w * 0.28f, h * 0.76f)
        close()
    }
    drawPath(scrollPath, color = Color(0xFFFFF8E1))

    // Sacred Swastika & Devanagari auspicious character on scroll
    val scX = w * 0.50f
    val scY = h * 0.48f
    val arm = w * 0.06f
    drawLine(Color(0xFFD32F2F), Offset(scX - arm, scY), Offset(scX + arm, scY), strokeWidth = 3f)
    drawLine(Color(0xFFD32F2F), Offset(scX, scY - arm), Offset(scX, scY + arm), strokeWidth = 3f)

    // Golden dots in 4 quadrants
    drawCircle(Color(0xFFD32F2F), 2.5f, Offset(scX - arm * 0.5f, scY - arm * 0.5f))
    drawCircle(Color(0xFFD32F2F), 2.5f, Offset(scX + arm * 0.5f, scY - arm * 0.5f))
    drawCircle(Color(0xFFD32F2F), 2.5f, Offset(scX - arm * 0.5f, scY + arm * 0.5f))
    drawCircle(Color(0xFFD32F2F), 2.5f, Offset(scX + arm * 0.5f, scY + arm * 0.5f))

    // Cradle arch
    val cradlePath = Path().apply {
        moveTo(w * 0.30f, h * 0.76f)
        cubicTo(w * 0.40f, h * 0.86f, w * 0.60f, h * 0.86f, w * 0.70f, h * 0.76f)
    }
    drawPath(cradlePath, color = Color(0xFFFFD54F), style = Stroke(width = 4f))
}

// 6. Vastu Shanti Art: Vastu Mandala geometry, copper Pyramidal yantra, and Agni flame
private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawVastuArt(w: Float, h: Float) {
    val cx = w * 0.5f
    val cy = h * 0.52f

    // 8-directional radial rays
    for (i in 0..7) {
        val rad = i * (Math.PI / 4)
        val ex = (cx + Math.cos(rad) * w * 0.36f).toFloat()
        val ey = (cy + Math.sin(rad) * h * 0.36f).toFloat()
        drawLine(Color(0x44FFB74D), Offset(cx, cy), Offset(ex, ey), strokeWidth = 2f)
    }

    // Outer Mandala Square
    val side = w * 0.44f
    drawRect(
        color = Color(0xFFFFB74D),
        topLeft = Offset(cx - side / 2, cy - side / 2),
        size = Size(side, side),
        style = Stroke(width = 3f)
    )

    // Inner Diamond
    val diamond = Path().apply {
        moveTo(cx, cy - side / 2)
        lineTo(cx + side / 2, cy)
        lineTo(cx, cy + side / 2)
        lineTo(cx - side / 2, cy)
        close()
    }
    drawPath(diamond, color = Color(0xFFFF8A65), style = Stroke(width = 2.5f))

    // Central Agni / Copper Yantra Pyramid
    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(Color(0xFFFFD54F), Color(0xFFFF7043), Color(0xFFD84315)),
            center = Offset(cx, cy),
            radius = side * 0.35f
        ),
        radius = side * 0.35f,
        center = Offset(cx, cy)
    )

    // Sacred Bindu center
    drawCircle(Color(0xFFFFF9C4), 5f, Offset(cx, cy))
}

// 7. Generic Havan Art fallback
private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawGenericHavanArt(w: Float, h: Float) {
    // Warm radial aura
    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(Color(0xFFFFD54F), Color(0xFFFF7043), Color(0x00BF360C)),
            center = Offset(w * 0.5f, h * 0.55f),
            radius = w * 0.45f
        ),
        radius = w * 0.45f,
        center = Offset(w * 0.5f, h * 0.55f)
    )

    // Havan Kund stepped base
    drawRoundRect(
        color = Color(0xFFB26A00),
        topLeft = Offset(w * 0.26f, h * 0.68f),
        size = Size(w * 0.48f, h * 0.12f),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(6f, 6f)
    )
    drawRoundRect(
        color = Color(0xFFD84315),
        topLeft = Offset(w * 0.32f, h * 0.60f),
        size = Size(w * 0.36f, h * 0.09f),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(4f, 4f)
    )

    // Agni Flame
    val flame = Path().apply {
        moveTo(w * 0.50f, h * 0.60f)
        cubicTo(w * 0.42f, h * 0.52f, w * 0.44f, h * 0.38f, w * 0.50f, h * 0.28f)
        cubicTo(w * 0.56f, h * 0.38f, w * 0.58f, h * 0.52f, w * 0.50f, h * 0.60f)
        close()
    }
    drawPath(flame, brush = Brush.verticalGradient(listOf(Color(0xFFFFF59D), Color(0xFFFF7043))))
}

/**
 * Authentic Vedic Acharya & Pandit Visual Portrait
 * Replaces random stock photos with respectful, traditional Indian Vedic Purohit portraits.
 */
@Composable
fun PanditVisualArt(
    panditName: String,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 16.dp
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFF3E0), Color(0xFFFFE0B2), Color(0xFFFFCC80))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // Auspicious aura ring behind Pandit
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xAAFFF9C4), Color(0x66FFE082), Color(0x00FFB74D)),
                    center = Offset(w * 0.5f, h * 0.40f),
                    radius = w * 0.45f
                ),
                radius = w * 0.45f,
                center = Offset(w * 0.5f, h * 0.40f)
            )

            // Saffron Angavastram / Dhoti drape
            val clothColor = when {
                panditName.contains("Vishnu", ignoreCase = true) -> Color(0xFFE65100) // Deep saffron
                panditName.contains("Rajesh", ignoreCase = true) -> Color(0xFFF57C00) // Saffron orange
                panditName.contains("Suresh", ignoreCase = true) -> Color(0xFFFF8F00) // Golden pitambar
                else -> Color(0xFFEF6C00) // Traditional saffron
            }

            val drape = Path().apply {
                moveTo(0f, h)
                lineTo(w * 0.20f, h * 0.62f)
                lineTo(w * 0.80f, h * 0.62f)
                lineTo(w, h)
                close()
            }
            drawPath(drape, color = clothColor)

            // Angavastram golden border
            drawLine(
                color = Color(0xFFFFD54F),
                start = Offset(w * 0.22f, h * 0.64f),
                end = Offset(0f, h * 0.95f),
                strokeWidth = 3.5f
            )
            drawLine(
                color = Color(0xFFFFD54F),
                start = Offset(w * 0.78f, h * 0.64f),
                end = Offset(w, h * 0.95f),
                strokeWidth = 3.5f
            )

            // Pandit Neck & Head
            val headRadius = w * 0.24f
            val headCenter = Offset(w * 0.5f, h * 0.40f)
            drawCircle(
                color = Color(0xFFECC29C),
                radius = headRadius,
                center = headCenter
            )

            // Traditional Shikha (choti) at the back of head
            val shikha = Path().apply {
                moveTo(headCenter.x - 4f, headCenter.y - headRadius)
                cubicTo(headCenter.x - 12f, headCenter.y - headRadius - 8f, headCenter.x - 14f, headCenter.y - headRadius + 4f, headCenter.x - 4f, headCenter.y - headRadius + 8f)
                close()
            }
            drawPath(shikha, color = Color(0xFF212121))

            // Chandan Tilak / Tripundra
            when {
                panditName.contains("Vishnu", ignoreCase = true) || panditName.contains("Ankit", ignoreCase = true) -> {
                    // Urdhva Pundra (Lord Vishnu Tilak with Kumkum center)
                    val tilakPath = Path().apply {
                        moveTo(headCenter.x - 6f, headCenter.y - headRadius * 0.5f)
                        lineTo(headCenter.x - 6f, headCenter.y - headRadius * 0.1f)
                        cubicTo(headCenter.x - 6f, headCenter.y, headCenter.x + 6f, headCenter.y, headCenter.x + 6f, headCenter.y - headRadius * 0.1f)
                        lineTo(headCenter.x + 6f, headCenter.y - headRadius * 0.5f)
                    }
                    drawPath(tilakPath, color = Color(0xFFFFF9C4), style = Stroke(width = 2.5f))
                    // Central red bindu/line
                    drawLine(
                        color = Color(0xFFD32F2F),
                        start = Offset(headCenter.x, headCenter.y - headRadius * 0.45f),
                        end = Offset(headCenter.x, headCenter.y - headRadius * 0.05f),
                        strokeWidth = 2.5f
                    )
                }
                else -> {
                    // Tripundra (Three horizontal chandan lines)
                    for (i in 0..2) {
                        val ly = headCenter.y - headRadius * (0.45f - i * 0.12f)
                        drawLine(
                            color = Color(0xFFFFF9C4),
                            start = Offset(headCenter.x - 10f, ly),
                            end = Offset(headCenter.x + 10f, ly),
                            strokeWidth = 2f
                        )
                    }
                    // Central Kumkum dot
                    drawCircle(Color(0xFFD32F2F), 3f, Offset(headCenter.x, headCenter.y - headRadius * 0.33f))
                }
            }

            // Sacred Rudraksha bead mala around neck
            for (i in 0..7) {
                val beadAngle = i * (Math.PI / 8)
                val bx = headCenter.x - w * 0.20f + i * (w * 0.40f / 7)
                val by = headCenter.y + headRadius * (0.85f + kotlin.math.sin(beadAngle).toFloat() * 0.20f)
                drawCircle(color = Color(0xFF4E342E), radius = 3.5f, center = Offset(bx, by))
            }

            // Sacred Yajnopavita (Janeu thread across chest)
            drawLine(
                color = Color(0xFFFFFFFF),
                start = Offset(w * 0.32f, h * 0.65f),
                end = Offset(w * 0.70f, h * 0.95f),
                strokeWidth = 2f
            )
        }
    }
}

/**
 * Authentic Temple Visual Artwork
 */
@Composable
fun TempleVisualArt(
    templeName: String,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 16.dp
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFE65100), Color(0xFFBF360C), Color(0xFF3E2723))
                )
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // Radiant morning sunrise behind temple
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFFFFF9C4), Color(0xFFFFD54F), Color(0x00FF8F00)),
                    center = Offset(w * 0.70f, h * 0.30f),
                    radius = w * 0.35f
                ),
                radius = w * 0.35f,
                center = Offset(w * 0.70f, h * 0.30f)
            )

            // Grand Temple Shikhara Architecture
            val temple = Path().apply {
                moveTo(w * 0.05f, h)
                lineTo(w * 0.15f, h * 0.65f)
                lineTo(w * 0.22f, h * 0.45f)
                lineTo(w * 0.26f, h * 0.30f) // Left tower
                lineTo(w * 0.30f, h * 0.45f)
                lineTo(w * 0.38f, h * 0.58f)
                lineTo(w * 0.44f, h * 0.30f)
                lineTo(w * 0.50f, h * 0.12f) // Main central towering Shikhara
                lineTo(w * 0.56f, h * 0.30f)
                lineTo(w * 0.62f, h * 0.58f)
                lineTo(w * 0.70f, h * 0.45f)
                lineTo(w * 0.74f, h * 0.30f) // Right tower
                lineTo(w * 0.78f, h * 0.45f)
                lineTo(w * 0.85f, h * 0.65f)
                lineTo(w * 0.95f, h)
                close()
            }
            drawPath(
                path = temple,
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF5D1E0B), Color(0xFF270902))
                )
            )

            // Golden Kalash at top of central Shikhara
            drawCircle(Color(0xFFFFD54F), 5f, Offset(w * 0.50f, h * 0.11f))

            // Saffron/Red Dhwaja (Holy temple flag) fluttering
            val flag = Path().apply {
                moveTo(w * 0.50f, h * 0.11f)
                lineTo(w * 0.58f, h * 0.07f)
                lineTo(w * 0.50f, h * 0.04f)
                close()
            }
            drawPath(flag, color = Color(0xFFFF5722))

            // Specific details for Baidyanath Dham: Panchshul
            if (templeName.contains("Baidyanath", ignoreCase = true)) {
                // Panchshul trident at summit
                for (k in -2..2) {
                    val px = w * 0.50f + k * 4f
                    drawLine(Color(0xFFFFD54F), Offset(px, h * 0.11f), Offset(px, h * 0.06f), strokeWidth = 1.5f)
                }
            }
        }
    }
}

/**
 * Authentic Katha & Bhajan Sandhya Visual Artwork
 */
@Composable
fun KathaBhajanVisualArt(
    eventTitle: String,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 16.dp
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFBF360C), Color(0xFFE65100), Color(0xFF4E342E))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // Devotional golden sunburst aura
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFFFFD54F), Color(0xFFFF9800), Color(0x00BF360C)),
                    center = Offset(w * 0.5f, h * 0.5f),
                    radius = w * 0.42f
                ),
                radius = w * 0.42f,
                center = Offset(w * 0.5f, h * 0.5f)
            )

            // Indian Musical Instrument: Harmonium & Dholak / Vyas Peeth
            if (eventTitle.contains("Bhajan", ignoreCase = true)) {
                // Harmonium body
                drawRoundRect(
                    color = Color(0xFF5D4037),
                    topLeft = Offset(w * 0.18f, h * 0.55f),
                    size = Size(w * 0.40f, h * 0.25f),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(8f, 8f)
                )
                // Harmonium ivory & black keys
                for (k in 0..7) {
                    val kx = w * (0.20f + k * 0.045f)
                    drawRect(Color(0xFFFFF9C4), Offset(kx, h * 0.62f), Size(w * 0.038f, h * 0.15f))
                    if (k in listOf(1, 2, 4, 5, 6)) {
                        drawRect(Color(0xFF212121), Offset(kx - w * 0.015f, h * 0.62f), Size(w * 0.03f, h * 0.09f))
                    }
                }

                // Dholak on the right
                val dholakCenter = Offset(w * 0.74f, h * 0.65f)
                drawOval(
                    color = Color(0xFF795548),
                    topLeft = Offset(w * 0.62f, h * 0.54f),
                    size = Size(w * 0.24f, h * 0.26f)
                )
                drawCircle(Color(0xFF3E2723), w * 0.06f, dholakCenter)
            } else {
                // Sacred Vyas Peeth with Holy Granth & Peacock Feather
                val vyasPeeth = Path().apply {
                    moveTo(w * 0.25f, h * 0.65f)
                    lineTo(w * 0.75f, h * 0.65f)
                    lineTo(w * 0.85f, h * 0.82f)
                    lineTo(w * 0.15f, h * 0.82f)
                    close()
                }
                drawPath(vyasPeeth, color = Color(0xFFFF8F00))

                // Granth book
                drawRect(Color(0xFFFFF9C4), Offset(w * 0.32f, h * 0.52f), Size(w * 0.36f, h * 0.14f))
                drawLine(Color(0xFFD32F2F), Offset(w * 0.50f, h * 0.52f), Offset(w * 0.50f, h * 0.66f), strokeWidth = 4f)

                // Diya lamp
                drawRoundRect(
                    color = Color(0xFFE65100),
                    topLeft = Offset(w * 0.15f, h * 0.68f),
                    size = Size(w * 0.12f, h * 0.06f),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(6f, 6f)
                )
                val flame = Path().apply {
                    moveTo(w * 0.21f, h * 0.68f)
                    cubicTo(w * 0.18f, h * 0.60f, w * 0.20f, h * 0.50f, w * 0.21f, h * 0.46f)
                    cubicTo(w * 0.22f, h * 0.50f, w * 0.24f, h * 0.60f, w * 0.21f, h * 0.68f)
                    close()
                }
                drawPath(flame, brush = Brush.verticalGradient(listOf(Color(0xFFFFF9C4), Color(0xFFFF9800))))
            }
        }
    }
}

/**
 * Authentic Puja Samagri Visual Artwork
 */
@Composable
fun SamagriVisualArt(
    productName: String,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 16.dp
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFF8E1), Color(0xFFFFECB3), Color(0xFFFFE082))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // Warm golden glow
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFFFFD54F), Color(0x66FFB300), Color(0x00FFA000)),
                    center = Offset(w * 0.5f, h * 0.5f),
                    radius = w * 0.42f
                ),
                radius = w * 0.42f,
                center = Offset(w * 0.5f, h * 0.5f)
            )

            // Ornate Brass Puja Thali
            val thaliRadius = w * 0.38f
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFFFFE082), Color(0xFFFFB300), Color(0xFFFFA000), Color(0xFFB26A00)),
                    center = Offset(w * 0.5f - 10f, h * 0.5f - 10f),
                    radius = thaliRadius
                ),
                radius = thaliRadius,
                center = Offset(w * 0.5f, h * 0.5f)
            )
            // Thali decorative border
            drawCircle(
                color = Color(0xFFD84315),
                radius = thaliRadius * 0.94f,
                center = Offset(w * 0.5f, h * 0.5f),
                style = Stroke(width = 2.5f)
            )

            // Items inside Thali: Diya, Kumkum bowl, Chandan bowl, Akshat
            // Central Brass Diya
            drawCircle(Color(0xFFFFD54F), w * 0.12f, Offset(w * 0.50f, h * 0.50f))
            drawCircle(Color(0xFFFF8F00), w * 0.08f, Offset(w * 0.50f, h * 0.50f))
            // Diya Flame
            val flame = Path().apply {
                moveTo(w * 0.50f, h * 0.50f)
                cubicTo(w * 0.46f, h * 0.42f, w * 0.48f, h * 0.32f, w * 0.50f, h * 0.28f)
                cubicTo(w * 0.52f, h * 0.32f, w * 0.54f, h * 0.42f, w * 0.50f, h * 0.50f)
                close()
            }
            drawPath(flame, brush = Brush.verticalGradient(listOf(Color(0xFFFFF9C4), Color(0xFFFF5722))))

            // Red Kumkum / Roli Katori
            drawCircle(Color(0xFFD32F2F), w * 0.08f, Offset(w * 0.30f, h * 0.42f))
            // Chandan Yellow Katori
            drawCircle(Color(0xFFFFEB3B), w * 0.08f, Offset(w * 0.70f, h * 0.42f))
            // Pure White Akshat (sacred rice grains)
            drawCircle(Color(0xFFFFFFFF), w * 0.07f, Offset(w * 0.35f, h * 0.65f))
            // Sacred Mauli / Kalava coil
            drawCircle(Color(0xFFFF7043), w * 0.07f, Offset(w * 0.65f, h * 0.65f))
        }
    }
}

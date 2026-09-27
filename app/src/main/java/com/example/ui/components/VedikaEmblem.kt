package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@Composable
fun VedikaLogoBadge(
    modifier: Modifier = Modifier,
    size: Dp = 100.dp,
    showShadow: Boolean = true
) {
    Box(
        modifier = modifier
            .size(size)
            .then(
                if (showShadow) Modifier.shadow(12.dp, RoundedCornerShape(26.dp), spotColor = VedikaOrangeDark)
                else Modifier
            )
            .clip(RoundedCornerShape(26.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFA726),
                        Color(0xFFFF6F00),
                        Color(0xFFE65100),
                        Color(0xFFBF360C)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize().padding(size * 0.12f)) {
            val w = this.size.width
            val h = this.size.height

            // Divine radiant sun halo in the center
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFFFFE082), Color(0xFFFFB300), Color(0x00FF8F00)),
                    center = Offset(w * 0.5f, h * 0.42f),
                    radius = w * 0.42f
                ),
                radius = w * 0.38f,
                center = Offset(w * 0.5f, h * 0.42f)
            )

            // Sacred Temple Arch / Sanctum (Pristine ivory)
            val archPath = Path().apply {
                moveTo(w * 0.28f, h * 0.65f)
                lineTo(w * 0.28f, h * 0.48f)
                quadraticBezierTo(w * 0.28f, h * 0.38f, w * 0.38f, h * 0.34f)
                quadraticBezierTo(w * 0.45f, h * 0.30f, w * 0.50f, h * 0.20f)
                quadraticBezierTo(w * 0.55f, h * 0.30f, w * 0.62f, h * 0.34f)
                quadraticBezierTo(w * 0.72f, h * 0.38f, w * 0.72f, h * 0.48f)
                lineTo(w * 0.72f, h * 0.65f)
                lineTo(w * 0.64f, h * 0.65f)
                lineTo(w * 0.64f, h * 0.50f)
                quadraticBezierTo(w * 0.64f, h * 0.42f, w * 0.58f, h * 0.38f)
                quadraticBezierTo(w * 0.53f, h * 0.34f, w * 0.50f, h * 0.28f)
                quadraticBezierTo(w * 0.47f, h * 0.34f, w * 0.42f, h * 0.38f)
                quadraticBezierTo(w * 0.36f, h * 0.42f, w * 0.36f, h * 0.50f)
                lineTo(w * 0.36f, h * 0.65f)
                close()
            }
            drawPath(path = archPath, color = Color(0xFFFFFDF8), style = Fill)

            // Waving Holy Saffron Flag (Dhwaja) at Apex
            // Flag pole
            drawLine(
                color = Color(0xFFFFF8E1),
                start = Offset(w * 0.50f, h * 0.12f),
                end = Offset(w * 0.50f, h * 0.22f),
                strokeWidth = 3f,
                cap = StrokeCap.Round
            )
            // Flag pennant
            val flagPath = Path().apply {
                moveTo(w * 0.50f, h * 0.12f)
                quadraticBezierTo(w * 0.62f, h * 0.11f, w * 0.74f, h * 0.15f)
                quadraticBezierTo(w * 0.66f, h * 0.18f, w * 0.76f, h * 0.22f)
                lineTo(w * 0.50f, h * 0.20f)
                close()
            }
            drawPath(path = flagPath, color = Color(0xFFFF5722), style = Fill)

            // Golden Hanging Temple Bell (Ghanti)
            drawLine(
                color = Color(0xFFC78200),
                start = Offset(w * 0.50f, h * 0.30f),
                end = Offset(w * 0.50f, h * 0.40f),
                strokeWidth = 2.5f
            )
            val bellPath = Path().apply {
                moveTo(w * 0.50f, h * 0.40f)
                quadraticBezierTo(w * 0.45f, h * 0.41f, w * 0.43f, h * 0.48f)
                lineTo(w * 0.57f, h * 0.48f)
                quadraticBezierTo(w * 0.55f, h * 0.41f, w * 0.50f, h * 0.40f)
                close()
            }
            drawPath(path = bellPath, color = Color(0xFFFFD54F), style = Fill)
            drawCircle(
                color = Color(0xFFFFB300),
                radius = 3.5f,
                center = Offset(w * 0.50f, h * 0.51f)
            )

            // Blooming Sacred Lotus Petals at Base
            // Central upright petal
            val centerPetal = Path().apply {
                moveTo(w * 0.50f, h * 0.52f)
                cubicTo(w * 0.44f, h * 0.60f, w * 0.44f, h * 0.74f, w * 0.50f, h * 0.85f)
                cubicTo(w * 0.56f, h * 0.74f, w * 0.56f, h * 0.60f, w * 0.50f, h * 0.52f)
                close()
            }
            drawPath(
                path = centerPetal,
                brush = Brush.verticalGradient(listOf(Color(0xFFFFFFFF), Color(0xFFFFE082))),
                style = Fill
            )

            // Left inner petal
            val leftInnerPetal = Path().apply {
                moveTo(w * 0.50f, h * 0.85f)
                cubicTo(w * 0.38f, h * 0.80f, w * 0.35f, h * 0.67f, w * 0.40f, h * 0.58f)
                cubicTo(w * 0.44f, h * 0.66f, w * 0.47f, h * 0.74f, w * 0.50f, h * 0.85f)
                close()
            }
            drawPath(
                path = leftInnerPetal,
                brush = Brush.verticalGradient(listOf(Color(0xFFFFF9C4), Color(0xFFFFCA28))),
                style = Fill
            )

            // Right inner petal
            val rightInnerPetal = Path().apply {
                moveTo(w * 0.50f, h * 0.85f)
                cubicTo(w * 0.62f, h * 0.80f, w * 0.65f, h * 0.67f, w * 0.60f, h * 0.58f)
                cubicTo(w * 0.56f, h * 0.66f, w * 0.53f, h * 0.74f, w * 0.50f, h * 0.85f)
                close()
            }
            drawPath(
                path = rightInnerPetal,
                brush = Brush.verticalGradient(listOf(Color(0xFFFFF9C4), Color(0xFFFFCA28))),
                style = Fill
            )

            // Outer flared left petal
            val leftOuterPetal = Path().apply {
                moveTo(w * 0.50f, h * 0.88f)
                cubicTo(w * 0.28f, h * 0.88f, w * 0.15f, h * 0.77f, w * 0.22f, h * 0.68f)
                cubicTo(w * 0.28f, h * 0.78f, w * 0.40f, h * 0.84f, w * 0.50f, h * 0.88f)
                close()
            }
            drawPath(path = leftOuterPetal, color = Color(0xFFFFFDE7), style = Fill)

            // Outer flared right petal
            val rightOuterPetal = Path().apply {
                moveTo(w * 0.50f, h * 0.88f)
                cubicTo(w * 0.72f, h * 0.88f, w * 0.85f, h * 0.77f, w * 0.78f, h * 0.68f)
                cubicTo(w * 0.72f, h * 0.78f, w * 0.60f, h * 0.84f, w * 0.50f, h * 0.88f)
                close()
            }
            drawPath(path = rightOuterPetal, color = Color(0xFFFFFDE7), style = Fill)
        }
    }
}

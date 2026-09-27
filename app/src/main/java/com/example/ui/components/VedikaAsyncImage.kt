package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.model.*

/**
 * Premium Vedic Image Loading Architecture with Coil AsyncImage
 * Renders real high-resolution authentic photography with seamless
 * fallback to culturally authentic Vedic artworks.
 */

@Composable
fun VedikaServiceImage(
    service: ServiceItem,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 16.dp
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(Color(0xFFF7F2EA)),
        contentAlignment = Alignment.Center
    ) {
        // High quality photo with fallback
        if (service.imageUrl.isNotBlank()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(service.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = service.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            ServiceVisualArt(
                serviceTitle = service.title,
                modifier = Modifier.fillMaxSize(),
                cornerRadius = cornerRadius
            )
        }
    }
}

@Composable
fun VedikaPanditImage(
    pandit: PanditProfile,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 16.dp
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(Color(0xFFF7F2EA)),
        contentAlignment = Alignment.Center
    ) {
        if (pandit.imageUrl.isNotBlank()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pandit.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = pandit.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            PanditVisualArt(
                panditName = pandit.name,
                modifier = Modifier.fillMaxSize(),
                cornerRadius = cornerRadius
            )
        }
    }
}

@Composable
fun VedikaTempleImage(
    temple: TempleItem,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 16.dp
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(Color(0xFFF7F2EA)),
        contentAlignment = Alignment.Center
    ) {
        if (temple.imageUrl.isNotBlank()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(temple.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = temple.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            TempleVisualArt(
                templeName = temple.name,
                modifier = Modifier.fillMaxSize(),
                cornerRadius = cornerRadius
            )
        }
    }
}

@Composable
fun VedikaKathaImage(
    event: KathaEvent,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 16.dp
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(Color(0xFFF7F2EA)),
        contentAlignment = Alignment.Center
    ) {
        if (event.imageUrl.isNotBlank()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(event.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = event.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            KathaBhajanVisualArt(
                eventTitle = event.title,
                modifier = Modifier.fillMaxSize(),
                cornerRadius = cornerRadius
            )
        }
    }
}

@Composable
fun VedikaSamagriImage(
    product: SamagriProduct,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 16.dp
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(Color(0xFFF7F2EA)),
        contentAlignment = Alignment.Center
    ) {
        if (product.imageUrl.isNotBlank()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            SamagriVisualArt(
                productName = product.name,
                modifier = Modifier.fillMaxSize(),
                cornerRadius = cornerRadius
            )
        }
    }
}

@Composable
fun VedikaNetworkImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    cornerRadius: Dp = 16.dp,
    fallback: @Composable () -> Unit = {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF3E1F11), Color(0xFF6B2D11), Color(0xFF1E0C06))
                    )
                )
        )
    }
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(Color(0xFFF0EAE1)),
        contentAlignment = Alignment.Center
    ) {
        fallback()
        if (imageUrl.isNotBlank()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = contentDescription,
                contentScale = contentScale,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

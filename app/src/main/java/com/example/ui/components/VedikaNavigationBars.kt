package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.VedikaData
import com.example.ui.theme.*

@Composable
fun VedikaHomeTopBar(
    currentCity: String,
    onCitySelected: (String) -> Unit,
    onNotificationClick: () -> Unit,
    hasUnreadNotifications: Boolean = true,
    isHindi: Boolean = false,
    onToggleLanguage: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var showCityMenu by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Location Selector pill
        Box {
            Surface(
                onClick = { showCityMenu = true },
                shape = RoundedCornerShape(20.dp),
                color = VedikaCreamDark,
                modifier = Modifier.height(36.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location",
                        tint = VedikaOrange,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = currentCity,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = VedikaTextDark
                        )
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Change City",
                        tint = VedikaTextMedium,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            DropdownMenu(
                expanded = showCityMenu,
                onDismissRequest = { showCityMenu = false },
                modifier = Modifier.background(VedikaSurface)
            ) {
                VedikaData.cities.forEach { city ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = city,
                                color = if (city == currentCity) VedikaOrange else VedikaTextDark,
                                fontWeight = if (city == currentCity) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        onClick = {
                            onCitySelected(city)
                            showCityMenu = false
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Place,
                                contentDescription = null,
                                tint = if (city == currentCity) VedikaOrange else VedikaTextMuted,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    )
                }
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Language Switcher Badge (EN / हि)
            Surface(
                onClick = onToggleLanguage,
                shape = RoundedCornerShape(18.dp),
                color = if (isHindi) VedikaOrange else VedikaCreamDark,
                border = androidx.compose.foundation.BorderStroke(1.dp, if (isHindi) VedikaOrangeDark else VedikaCardBorder),
                modifier = Modifier.height(36.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Translate,
                        contentDescription = "Language",
                        tint = if (isHindi) Color.White else VedikaTextDark,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (isHindi) "हिन्दी" else "EN",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = if (isHindi) Color.White else VedikaTextDark
                    )
                }
            }

            // Notification Bell Icon with Red/Orange Badge Dot
            IconButton(
                onClick = onNotificationClick,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(VedikaSurface)
            ) {
                Box(contentAlignment = Alignment.TopEnd) {
                    Icon(
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = "Notifications",
                        tint = VedikaTextDark,
                        modifier = Modifier.size(24.dp)
                    )
                    if (hasUnreadNotifications) {
                        Box(
                            modifier = Modifier
                                .size(9.dp)
                                .clip(CircleShape)
                                .background(VedikaOrange)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun VedikaChildTopBar(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {}
) {
    Surface(
        color = VedikaCream,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = VedikaTextDark
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = VedikaTextDark
                ),
                modifier = Modifier.weight(1f)
            )
            actions()
        }
    }
}

@Composable
fun VedikaBottomBar(
    currentTab: String,
    onTabSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = VedikaSurface,
        modifier = modifier
            .fillMaxWidth()
            .shadow(16.dp, spotColor = Color(0x1F000000))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.navigationBars)
        ) {
            HorizontalDivider(color = VedikaDivider, thickness = 0.8.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BottomNavItem(
                    label = "Home",
                    iconFilled = Icons.Filled.Home,
                    iconOutlined = Icons.Outlined.Home,
                    isSelected = currentTab == "Home",
                    onClick = { onTabSelected("Home") }
                )
                BottomNavItem(
                    label = "Bookings",
                    iconFilled = Icons.Filled.CalendarMonth,
                    iconOutlined = Icons.Outlined.CalendarMonth,
                    isSelected = currentTab == "Bookings",
                    onClick = { onTabSelected("Bookings") }
                )
                BottomNavItem(
                    label = "Panchang",
                    iconFilled = Icons.Filled.DateRange,
                    iconOutlined = Icons.Outlined.DateRange,
                    isSelected = currentTab == "Panchang",
                    onClick = { onTabSelected("Panchang") }
                )
                BottomNavItem(
                    label = "Explore",
                    iconFilled = Icons.Filled.Explore,
                    iconOutlined = Icons.Outlined.Explore,
                    isSelected = currentTab == "Explore",
                    onClick = { onTabSelected("Explore") }
                )
                BottomNavItem(
                    label = "Profile",
                    iconFilled = Icons.Filled.Person,
                    iconOutlined = Icons.Outlined.PersonOutline,
                    isSelected = currentTab == "Profile",
                    onClick = { onTabSelected("Profile") }
                )
            }
        }
    }
}

@Composable
private fun BottomNavItem(
    label: String,
    iconFilled: ImageVector,
    iconOutlined: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(14.dp))
                    .background(if (isSelected) VedikaSaffronLight else Color.Transparent)
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (isSelected) iconFilled else iconOutlined,
                    contentDescription = label,
                    tint = if (isSelected) VedikaOrangeDark else VedikaTextMuted,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                    color = if (isSelected) VedikaOrangeDark else VedikaTextMuted,
                    fontSize = 12.sp
                )
            )
        }
    }
}

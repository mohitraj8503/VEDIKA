package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.VedikaLogoBadge
import com.example.ui.theme.*

@Composable
fun AuthScreen(
    onLoginSuccess: (phone: String, isGuest: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var isLoginTab by remember { mutableStateOf(true) }
    var mobileNumber by remember { mutableStateOf("98765 43210") }
    var password by remember { mutableStateOf("vedika@2026") }
    var fullName by remember { mutableStateOf("Mohit Raj") }
    var showPassword by remember { mutableStateOf(false) }
    var showForgotPasswordDialog by remember { mutableStateOf(false) }
    var resetSentMessage by remember { mutableStateOf(false) }

    Scaffold(containerColor = VedikaCream) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Brand Logo & Emblem
            VedikaLogoBadge(size = 96.dp)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "VEDIKA",
                fontSize = 34.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Serif,
                letterSpacing = 2.sp,
                color = VedikaOrangeDark
            )

            Text(
                text = "परंपरा, आपके द्वार।",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = VedikaGold
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Segmented Tab [Login] | [Sign Up]
            Surface(
                shape = RoundedCornerShape(24.dp),
                color = VedikaCreamDark,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Row(
                    modifier = Modifier.padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(20.dp))
                            .background(if (isLoginTab) VedikaSurface else Color.Transparent)
                            .clickable { isLoginTab = true },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Login",
                            fontWeight = if (isLoginTab) FontWeight.Bold else FontWeight.Medium,
                            color = if (isLoginTab) VedikaOrange else VedikaTextMedium,
                            fontSize = 15.sp
                        )
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(20.dp))
                            .background(if (!isLoginTab) VedikaSurface else Color.Transparent)
                            .clickable { isLoginTab = false },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Sign Up",
                            fontWeight = if (!isLoginTab) FontWeight.Bold else FontWeight.Medium,
                            color = if (!isLoginTab) VedikaOrange else VedikaTextMedium,
                            fontSize = 15.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            if (!isLoginTab) {
                OutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    placeholder = { Text("Full Name", fontSize = 15.sp) },
                    leadingIcon = {
                        Icon(Icons.Default.Person, contentDescription = null, tint = VedikaOrange)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = VedikaOrange,
                        unfocusedBorderColor = VedikaCardBorder,
                        focusedContainerColor = VedikaSurface,
                        unfocusedContainerColor = VedikaSurface
                    ),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(14.dp))
            }

            // Mobile Number Input
            OutlinedTextField(
                value = mobileNumber,
                onValueChange = { mobileNumber = it },
                placeholder = { Text("Mobile Number", fontSize = 15.sp) },
                leadingIcon = {
                    Row(
                        modifier = Modifier.padding(start = 12.dp, end = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Phone, contentDescription = null, tint = VedikaOrange, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("+91", fontWeight = FontWeight.Bold, color = VedikaTextDark, fontSize = 15.sp)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = VedikaOrange,
                    unfocusedBorderColor = VedikaCardBorder,
                    focusedContainerColor = VedikaSurface,
                    unfocusedContainerColor = VedikaSurface
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Password Input
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Password", fontSize = 15.sp) },
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = null, tint = VedikaOrange, modifier = Modifier.size(20.dp))
                },
                trailingIcon = {
                    IconButton(onClick = { showPassword = !showPassword }) {
                        Icon(
                            imageVector = if (showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = "Toggle Password",
                            tint = VedikaTextMuted
                        )
                    }
                },
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = VedikaOrange,
                    unfocusedBorderColor = VedikaCardBorder,
                    focusedContainerColor = VedikaSurface,
                    unfocusedContainerColor = VedikaSurface
                ),
                singleLine = true
            )

            // Forgot Password Link
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Forgot Password?",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = VedikaOrange,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    ),
                    modifier = Modifier.clickable { showForgotPasswordDialog = true }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Primary Action Button (54dp)
            Button(
                onClick = { onLoginSuccess(mobileNumber, false) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .shadow(4.dp, RoundedCornerShape(16.dp), spotColor = Color(0x20E65100)),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = VedikaOrange)
            ) {
                Text(
                    text = if (isLoginTab) "Login" else "Create Account",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 16.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(22.dp))

            // OR Divider
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(modifier = Modifier.weight(1f), color = VedikaDivider)
                Text(
                    text = "OR",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = VedikaTextMuted,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(horizontal = 14.dp)
                )
                HorizontalDivider(modifier = Modifier.weight(1f), color = VedikaDivider)
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Social Sign In
            Row(
                horizontalArrangement = Arrangement.spacedBy(18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SocialLoginCircle(label = "Google", color = Color(0xFFEA4335)) {
                    onLoginSuccess("98765 43210", false)
                }
                SocialLoginCircle(label = "WhatsApp", color = Color(0xFF25D366)) {
                    onLoginSuccess("98765 43210", false)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Continue as Guest Link (Accessible touch target)
            Surface(
                onClick = { onLoginSuccess("", true) },
                shape = RoundedCornerShape(12.dp),
                color = Color.Transparent,
                border = BorderStroke(1.dp, VedikaOrange.copy(alpha = 0.4f))
            ) {
                Text(
                    text = "Explore as Guest Devotee",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = VedikaOrangeDark,
                        fontSize = 14.sp
                    ),
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                )
            }
        }

        // Forgot Password Dialog
        if (showForgotPasswordDialog) {
            AlertDialog(
                onDismissRequest = {
                    showForgotPasswordDialog = false
                    resetSentMessage = false
                },
                title = {
                    Text(
                        text = "Reset Password",
                        fontWeight = FontWeight.Bold,
                        color = VedikaTextDark,
                        fontSize = 18.sp
                    )
                },
                text = {
                    if (resetSentMessage) {
                        Text(
                            text = "OTP for resetting password has been sent to +91 $mobileNumber. Please verify to set new credentials.",
                            color = VedikaGreen,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        )
                    } else {
                        Text(
                            text = "Enter your registered mobile number (+91 $mobileNumber) to receive an authentication OTP.",
                            color = VedikaTextDark,
                            fontSize = 14.sp
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            if (!resetSentMessage) {
                                resetSentMessage = true
                            } else {
                                showForgotPasswordDialog = false
                                resetSentMessage = false
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = VedikaOrange)
                    ) {
                        Text(if (resetSentMessage) "Done" else "Send OTP", fontWeight = FontWeight.Bold)
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showForgotPasswordDialog = false
                        resetSentMessage = false
                    }) {
                        Text("Cancel", color = VedikaTextMedium)
                    }
                },
                shape = RoundedCornerShape(20.dp),
                containerColor = VedikaSurface
            )
        }
    }
}

@Composable
private fun SocialLoginCircle(
    label: String,
    color: Color,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(14.dp),
        color = VedikaSurface,
        border = BorderStroke(1.2.dp, VedikaCardBorder),
        modifier = Modifier.height(44.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = color
            )
        }
    }
}

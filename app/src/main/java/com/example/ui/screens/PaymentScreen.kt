package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.VedikaServiceImage
import com.example.ui.theme.*
import com.example.viewmodel.Screen
import com.example.viewmodel.VedikaViewModel

@Composable
fun PaymentScreen(
    viewModel: VedikaViewModel,
    uiState: com.example.viewmodel.VedikaUiState,
    modifier: Modifier = Modifier
) {
    val draft = uiState.bookingDraft
    val service = draft.service ?: com.example.data.VedikaData.services.first()

    val serviceCharge = service.price
    val samagriFee = 0
    val travelFee = if (draft.isInterCity) draft.travelFee else 0
    val platformFee = 99
    val totalAmount = serviceCharge + samagriFee + travelFee + platformFee

    val paymentMethods = listOf(
        "UPI (GPay, PhonePe, Paytm)",
        "Credit / Debit Card",
        "Net Banking",
        "Wallet"
    )

    var selectedPayment by remember { mutableStateOf(draft.selectedPaymentMethod) }

    Scaffold(
        containerColor = VedikaCream,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { viewModel.navigateTo(Screen.BookingFlow) }, modifier = Modifier.size(48.dp)) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = VedikaTextDark,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Payment",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = VedikaTextDark,
                        fontSize = 20.sp
                    )
                )
            }
        },
        bottomBar = {
            Surface(
                color = VedikaSurface,
                shadowElevation = 14.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 14.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            viewModel.selectPaymentMethod(selectedPayment)
                            viewModel.confirmPaymentAndBook()
                        },
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = VedikaOrange),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Text(
                            text = "Pay ₹ $totalAmount",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 17.sp
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            tint = VedikaGreen,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "100% Secure Payment",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = VedikaTextMedium,
                                fontSize = 13.sp
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 14.dp)
        ) {
            // Selected Service Summary Card with Real Photo
            item {
                Surface(
                    shape = RoundedCornerShape(18.dp),
                    color = VedikaSurface,
                    border = BorderStroke(1.dp, VedikaCardBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(72.dp)
                                .clip(RoundedCornerShape(14.dp))
                        ) {
                            VedikaServiceImage(
                                service = service,
                                modifier = Modifier.fillMaxSize(),
                                cornerRadius = 14.dp
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = service.title,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = VedikaTextDark,
                                    fontSize = 17.sp
                                )
                            )
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                text = "${draft.selectedDate} Oct 2026 • ${draft.selectedTime}",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = VedikaOrangeDark,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp
                                )
                            )
                            Text(
                                text = draft.selectedPandit?.name ?: "Pandit Rajesh Mishra",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = VedikaTextMedium,
                                    fontSize = 13.sp
                                )
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

            // Price Details Breakdown (Large and readable)
            item {
                Surface(
                    shape = RoundedCornerShape(18.dp),
                    color = VedikaSurface,
                    border = BorderStroke(1.dp, VedikaCardBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = "Price Details",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = VedikaTextDark,
                                fontSize = 19.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        PriceRow(title = "Service Charges", amount = "₹ $serviceCharge")
                        PriceRow(title = "Samagri Included", amount = "₹ 0")
                        if (draft.isInterCity) {
                            PriceRow(title = "Inter-City Travel (${draft.originCity} to ${draft.destinationCity})", amount = "₹ $travelFee")
                        }
                        PriceRow(title = "Platform Fee", amount = "₹ $platformFee")

                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 14.dp),
                            color = VedikaDivider
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Total Amount",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = VedikaTextDark,
                                    fontSize = 18.sp
                                )
                            )
                            Text(
                                text = "₹ $totalAmount",
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                    color = VedikaOrangeDark,
                                    fontSize = 24.sp
                                )
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            // Select Payment Method
            item {
                Text(
                    text = "Select Payment Method",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = VedikaTextDark,
                        fontSize = 19.sp
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))

                Surface(
                    shape = RoundedCornerShape(18.dp),
                    color = VedikaSurface,
                    border = BorderStroke(1.dp, VedikaCardBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        paymentMethods.forEachIndexed { index, method ->
                            val isSelected = selectedPayment == method
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .clickable { selectedPayment = method }
                                    .padding(horizontal = 14.dp, vertical = 14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = isSelected,
                                    onClick = { selectedPayment = method },
                                    colors = RadioButtonDefaults.colors(selectedColor = VedikaOrange)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = method,
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                        color = VedikaTextDark,
                                        fontSize = 15.sp
                                    )
                                )
                            }
                            if (index < paymentMethods.lastIndex) {
                                HorizontalDivider(color = VedikaDivider, thickness = 0.8.dp)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PriceRow(title: String, amount: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = VedikaTextMedium,
                fontSize = 15.sp
            )
        )
        Text(
            text = amount,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.SemiBold,
                color = VedikaTextDark,
                fontSize = 15.sp
            )
        )
    }
}

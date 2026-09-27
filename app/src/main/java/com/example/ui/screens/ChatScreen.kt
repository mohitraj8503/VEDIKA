package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
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
import com.example.model.ChatMessage
import com.example.ui.components.VedikaBottomBar
import com.example.ui.components.VedikaNetworkImage
import com.example.ui.theme.*
import com.example.viewmodel.Screen
import com.example.viewmodel.VedikaViewModel

@Composable
fun ChatScreen(
    viewModel: VedikaViewModel,
    uiState: com.example.viewmodel.VedikaUiState,
    modifier: Modifier = Modifier
) {
    var inputText by remember { mutableStateOf("") }

    Scaffold(
        containerColor = VedikaCream,
        topBar = {
            Surface(
                color = VedikaSurface,
                shadowElevation = 4.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { viewModel.setBottomNavTab("Home") }, modifier = Modifier.size(48.dp)) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = VedikaTextDark,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    // Pandit authentic real photo avatar
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                    ) {
                        VedikaNetworkImage(
                            imageUrl = "https://images.unsplash.com/photo-1544717305-2782549b5136?auto=format&fit=crop&w=400&q=80",
                            contentDescription = "Pandit Rajesh Mishra",
                            modifier = Modifier.fillMaxSize(),
                            cornerRadius = 23.dp
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Pandit Rajesh Mishra",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = VedikaTextDark,
                                fontSize = 16.sp
                            )
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(VedikaGreen)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "Online",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = VedikaGreen,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                        }
                    }

                    IconButton(onClick = { }, modifier = Modifier.size(48.dp)) {
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = "Call Pandit",
                            tint = VedikaOrange,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
            }
        },
        bottomBar = {
            Column {
                // Input Bar (clean vector icons)
                Surface(
                    color = VedikaSurface,
                    shadowElevation = 8.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { }, modifier = Modifier.size(44.dp)) {
                            Icon(
                                imageVector = Icons.Default.AttachFile,
                                contentDescription = "Attach File",
                                tint = VedikaTextMedium,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        TextField(
                            value = inputText,
                            onValueChange = { inputText = it },
                            placeholder = { Text("Type a message...", color = VedikaTextMuted, fontSize = 15.sp) },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = VedikaCreamDark,
                                unfocusedContainerColor = VedikaCreamDark,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            shape = RoundedCornerShape(24.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp),
                            singleLine = true
                        )

                        IconButton(onClick = { }, modifier = Modifier.size(44.dp)) {
                            Icon(
                                imageVector = Icons.Default.Mic,
                                contentDescription = "Voice Message",
                                tint = VedikaTextMedium,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        IconButton(
                            onClick = {
                                if (inputText.isNotBlank()) {
                                    viewModel.sendChatMessage(inputText)
                                    inputText = ""
                                }
                            },
                            modifier = Modifier
                                .size(46.dp)
                                .clip(CircleShape)
                                .background(VedikaOrange)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Send,
                                contentDescription = "Send",
                                tint = Color.White,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    }
                }

                VedikaBottomBar(
                    currentTab = uiState.activeTab,
                    onTabSelected = { viewModel.setBottomNavTab(it) }
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(uiState.chatMessages) { message ->
                ChatBubble(message = message)
            }
        }
    }
}

@Composable
fun ChatBubble(message: ChatMessage) {
    val isPandit = message.isFromPandit

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (isPandit) Alignment.Start else Alignment.End
    ) {
        Surface(
            shape = RoundedCornerShape(
                topStart = 18.dp,
                topEnd = 18.dp,
                bottomStart = if (isPandit) 4.dp else 18.dp,
                bottomEnd = if (isPandit) 18.dp else 4.dp
            ),
            color = if (isPandit) VedikaSurface else VedikaSaffronLight,
            border = BorderStroke(1.dp, if (isPandit) VedikaCardBorder else VedikaOrange.copy(alpha = 0.3f)),
            modifier = Modifier.widthIn(max = 300.dp)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = message.text,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = VedikaTextDark,
                        fontSize = 15.sp,
                        lineHeight = 22.sp
                    )
                )

                // Optional PDF Attachment Card
                if (message.hasAttachment && message.attachmentName != null) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = VedikaCreamDark,
                        border = BorderStroke(1.dp, VedikaCardBorder),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFFFFEBEE),
                                modifier = Modifier.size(40.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = Icons.Default.PictureAsPdf,
                                        contentDescription = null,
                                        tint = VedikaRed,
                                        modifier = Modifier.size(22.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = message.attachmentName,
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = VedikaTextDark,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    text = message.attachmentSize ?: "PDF Document",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = VedikaTextMuted,
                                        fontSize = 12.sp
                                    )
                                )
                            }

                            IconButton(onClick = { }, modifier = Modifier.size(36.dp)) {
                                Icon(
                                    imageVector = Icons.Default.Download,
                                    contentDescription = "Download",
                                    tint = VedikaOrange,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = message.timestamp,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = VedikaTextMuted,
                        fontSize = 11.sp
                    ),
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}

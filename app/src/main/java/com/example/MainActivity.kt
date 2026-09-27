package com.example

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.screens.*
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.VedikaCream
import com.example.viewmodel.Screen
import com.example.viewmodel.VedikaViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val viewModel: VedikaViewModel = viewModel()
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                val context = LocalContext.current

                // Show toast when message is set
                LaunchedEffect(uiState.toastMessage) {
                    uiState.toastMessage?.let { msg ->
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        viewModel.clearToast()
                    }
                }

                // Handle Back button navigation
                BackHandler(enabled = uiState.currentScreen != Screen.Home && uiState.currentScreen != Screen.Splash) {
                    when (uiState.currentScreen) {
                        Screen.BookingFlow -> viewModel.navigateTo(Screen.ServiceDetail)
                        Screen.Payment -> viewModel.navigateTo(Screen.BookingFlow)
                        Screen.BookingConfirmed -> viewModel.setBottomNavTab("Bookings")
                        Screen.ServiceDetail -> viewModel.setBottomNavTab("Home")
                        Screen.PanditBooking, Screen.TempleDarshan, Screen.KathaBhajan, Screen.PujaSamagri, Screen.NearbyMap, Screen.Search, Screen.Notifications, Screen.PanchangMuhurat, Screen.MantraJapa -> {
                            viewModel.setBottomNavTab("Home")
                        }
                        Screen.MoreMenu, Screen.ProviderDashboard, Screen.AdminDashboard -> {
                            viewModel.setBottomNavTab("Profile")
                        }
                        else -> viewModel.setBottomNavTab("Home")
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = VedikaCream
                ) {
                    AnimatedContent(
                        targetState = uiState.currentScreen,
                        transitionSpec = {
                            fadeIn(animationSpec = tween(220)) togetherWith fadeOut(animationSpec = tween(220))
                        },
                        label = "screen_transition"
                    ) { targetScreen ->
                        when (targetScreen) {
                            Screen.Splash -> SplashScreen(
                                onSplashFinished = { viewModel.navigateTo(Screen.Onboarding) }
                            )
                            Screen.Onboarding -> OnboardingScreen(
                                onFinish = { viewModel.navigateTo(Screen.Auth) }
                            )
                            Screen.Auth -> AuthScreen(
                                onLoginSuccess = { phone, isGuest ->
                                    viewModel.login(phone, isGuest)
                                }
                            )
                            Screen.Home -> HomeScreen(
                                viewModel = viewModel,
                                uiState = uiState
                            )
                            Screen.Explore -> ExploreScreen(
                                viewModel = viewModel,
                                uiState = uiState
                            )
                            Screen.PanditBooking -> PanditBookingScreen(
                                viewModel = viewModel,
                                uiState = uiState
                            )
                            Screen.ServiceDetail -> ServiceDetailScreen(
                                viewModel = viewModel,
                                uiState = uiState
                            )
                            Screen.BookingFlow -> BookingFlowScreen(
                                viewModel = viewModel,
                                uiState = uiState
                            )
                            Screen.Payment -> PaymentScreen(
                                viewModel = viewModel,
                                uiState = uiState
                            )
                            Screen.BookingConfirmed -> BookingConfirmedScreen(
                                viewModel = viewModel,
                                uiState = uiState
                            )
                            Screen.MyBookings -> MyBookingsScreen(
                                viewModel = viewModel,
                                uiState = uiState
                            )
                            Screen.TempleDarshan -> TempleDarshanScreen(
                                viewModel = viewModel,
                                uiState = uiState
                            )
                            Screen.KathaBhajan -> KathaBhajanScreen(
                                viewModel = viewModel,
                                uiState = uiState
                            )
                            Screen.PujaSamagri -> PujaSamagriScreen(
                                viewModel = viewModel,
                                uiState = uiState
                            )
                            Screen.Chat -> ChatScreen(
                                viewModel = viewModel,
                                uiState = uiState
                            )
                            Screen.NearbyMap -> NearbyMapScreen(
                                viewModel = viewModel,
                                uiState = uiState
                            )
                            Screen.Notifications -> NotificationsScreen(
                                viewModel = viewModel,
                                uiState = uiState
                            )
                            Screen.Search -> SearchScreen(
                                viewModel = viewModel,
                                uiState = uiState
                            )
                            Screen.Profile -> ProfileScreen(
                                viewModel = viewModel,
                                uiState = uiState
                            )
                            Screen.MoreMenu -> MoreMenuScreen(
                                viewModel = viewModel,
                                uiState = uiState
                            )
                            Screen.ProviderDashboard -> ProviderDashboardScreen(
                                viewModel = viewModel,
                                uiState = uiState
                            )
                            Screen.AdminDashboard -> AdminDashboardScreen(
                                viewModel = viewModel,
                                uiState = uiState
                            )
                            Screen.PanchangMuhurat -> PanchangMuhuratScreen(
                                viewModel = viewModel,
                                uiState = uiState
                            )
                            Screen.MantraJapa -> MantraJapaScreen(
                                viewModel = viewModel,
                                uiState = uiState
                            )
                        }
                    }
                }
            }
        }
    }
}

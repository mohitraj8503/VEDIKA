package com.example.viewmodel

import androidx.lifecycle.ViewModel
import com.example.data.VedikaData
import com.example.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Onboarding : Screen("onboarding")
    data object Auth : Screen("auth")
    data object Home : Screen("home")
    data object Explore : Screen("explore")
    data object PanditBooking : Screen("pandit_booking")
    data object ServiceDetail : Screen("service_detail")
    data object BookingFlow : Screen("booking_flow")
    data object Payment : Screen("payment")
    data object BookingConfirmed : Screen("booking_confirmed")
    data object MyBookings : Screen("my_bookings")
    data object TempleDarshan : Screen("temple_darshan")
    data object KathaBhajan : Screen("katha_bhajan")
    data object PujaSamagri : Screen("puja_samagri")
    data object Chat : Screen("chat")
    data object NearbyMap : Screen("nearby_map")
    data object Notifications : Screen("notifications")
    data object Search : Screen("search")
    data object Profile : Screen("profile")
    data object MoreMenu : Screen("more_menu")
    data object ProviderDashboard : Screen("provider_dashboard")
    data object AdminDashboard : Screen("admin_dashboard")
    data object PanchangMuhurat : Screen("panchang_muhurat")
    data object MantraJapa : Screen("mantra_japa")
}

data class CurrentBookingDraft(
    val service: ServiceItem? = null,
    val selectedPandit: PanditProfile? = null,
    val selectedDate: String = "25",
    val selectedDayName: String = "Sat",
    val selectedMonthYear: String = "October 2026",
    val selectedTime: String = "8:00 AM",
    val isHomeLocation: Boolean = true,
    val address: String = "Jamshedpur, Jharkhand",
    val isInterCity: Boolean = false,
    val originCity: String = "Deoghar",
    val destinationCity: String = "Bhagalpur",
    val travelFee: Int = 1200,
    val selectedPaymentMethod: String = "UPI (GPay, PhonePe, Paytm)",
    val bookingId: String = "VED-${(10000..99999).random()}"
)

data class VedikaUiState(
    val currentScreen: Screen = Screen.Splash,
    val selectedCity: String = "Jamshedpur",
    val userName: String = "Mohit Raj",
    val userPhone: String = "+91 98765 43210",
    val userEmail: String = "mohitraj@example.com",
    val isLoggedIn: Boolean = true,
    val isGuest: Boolean = false,
    val selectedCategory: String = "All",
    val searchQuery: String = "",
    val activeTab: String = "Home", // Bottom nav: Home, Bookings, Panchang, Explore, Profile
    val selectedServiceId: String = "s1",
    val selectedPanditId: String = "p2",
    val bookingDraft: CurrentBookingDraft = CurrentBookingDraft(),
    val bookings: List<BookingRecord> = VedikaData.initialBookings,
    val chatMessages: List<ChatMessage> = VedikaData.initialChatMessages,
    val notifications: List<NotificationItem> = VedikaData.notifications,
    val favoriteServiceIds: Set<String> = setOf("s1", "s2"),
    val favoritePanditIds: Set<String> = setOf("p1"),
    val cartItems: Map<String, Int> = mapOf("sm1" to 1),
    val toastMessage: String? = null,
    val isHindi: Boolean = false,
    val activePanchangDay: String = "today",
    val selectedMantraId: String = "mt1",
    val japaCounter: Int = 0,
    val totalJapaRounds: Int = 0,
    val preparationChecklist: List<PujaPreparationItem> = VedikaData.getPreparationChecklist("Griha Pravesh")
)

class VedikaViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(VedikaUiState())
    val uiState: StateFlow<VedikaUiState> = _uiState.asStateFlow()

    fun navigateTo(screen: Screen) {
        val updatedTab = when (screen) {
            Screen.Home -> "Home"
            Screen.MyBookings -> "Bookings"
            Screen.PanchangMuhurat -> "Panchang"
            Screen.Explore -> "Explore"
            Screen.Profile -> "Profile"
            else -> _uiState.value.activeTab
        }
        _uiState.update { it.copy(currentScreen = screen, activeTab = updatedTab) }
    }

    fun selectCity(city: String) {
        _uiState.update { it.copy(selectedCity = city) }
    }

    fun setBottomNavTab(tab: String) {
        _uiState.update { currentState ->
            val targetScreen = when (tab) {
                "Home" -> Screen.Home
                "Bookings" -> Screen.MyBookings
                "Panchang" -> Screen.PanchangMuhurat
                "Explore" -> Screen.Explore
                "Chat" -> Screen.Chat
                "Profile" -> Screen.Profile
                else -> currentState.currentScreen
            }
            currentState.copy(activeTab = tab, currentScreen = targetScreen)
        }
    }

    fun openServiceDetail(serviceId: String) {
        val service = VedikaData.services.find { it.id == serviceId } ?: VedikaData.services.first()
        _uiState.update {
            it.copy(
                selectedServiceId = serviceId,
                currentScreen = Screen.ServiceDetail,
                bookingDraft = it.bookingDraft.copy(service = service)
            )
        }
    }

    fun startBooking(serviceId: String? = null) {
        val service = if (serviceId != null) {
            VedikaData.services.find { it.id == serviceId }
        } else {
            VedikaData.services.find { it.id == _uiState.value.selectedServiceId }
        } ?: VedikaData.services.first()

        val defaultPandit = VedikaData.pandits.first()
        _uiState.update {
            it.copy(
                bookingDraft = CurrentBookingDraft(
                    service = service,
                    selectedPandit = defaultPandit,
                    bookingId = "VED-${(10000..99999).random()}"
                ),
                currentScreen = Screen.BookingFlow
            )
        }
    }

    fun selectBookingDate(date: String, dayName: String) {
        _uiState.update {
            it.copy(bookingDraft = it.bookingDraft.copy(selectedDate = date, selectedDayName = dayName))
        }
    }

    fun selectBookingTime(time: String) {
        _uiState.update {
            it.copy(bookingDraft = it.bookingDraft.copy(selectedTime = time))
        }
    }

    fun setPujaLocationType(isHome: Boolean) {
        _uiState.update {
            it.copy(bookingDraft = it.bookingDraft.copy(isHomeLocation = isHome))
        }
    }

    fun setInterCityOptions(isInterCity: Boolean, origin: String, destination: String) {
        _uiState.update {
            it.copy(
                bookingDraft = it.bookingDraft.copy(
                    isInterCity = isInterCity,
                    originCity = origin,
                    destinationCity = destination,
                    travelFee = if (isInterCity) 1400 else 0
                )
            )
        }
    }

    fun selectPaymentMethod(method: String) {
        _uiState.update {
            it.copy(bookingDraft = it.bookingDraft.copy(selectedPaymentMethod = method))
        }
    }

    fun proceedToPayment() {
        _uiState.update { it.copy(currentScreen = Screen.Payment) }
    }

    fun confirmPaymentAndBook() {
        val draft = _uiState.value.bookingDraft
        val newBooking = BookingRecord(
            id = draft.bookingId,
            serviceTitle = draft.service?.title ?: "Vedic Puja",
            date = "${draft.selectedDate} Oct 2026",
            time = draft.selectedTime,
            panditName = draft.selectedPandit?.name ?: "Pandit Rajesh Mishra",
            location = draft.address,
            status = BookingStatus.CONFIRMED,
            totalAmount = (draft.service?.price ?: 5100) + (if (draft.isInterCity) draft.travelFee else 0) + 99,
            isInterCity = draft.isInterCity,
            originCity = draft.originCity,
            destinationCity = draft.destinationCity,
            serviceCharges = draft.service?.price ?: 5100,
            samagriCharges = 0,
            platformFee = 99
        )

        val newNotification = NotificationItem(
            id = "n_${System.currentTimeMillis()}",
            category = "Bookings",
            title = "Booking Confirmed",
            description = "Your ${newBooking.serviceTitle} is confirmed for ${newBooking.date} • ${newBooking.time}",
            timeAgo = "Just now",
            isUnread = true
        )

        _uiState.update {
            it.copy(
                bookings = listOf(newBooking) + it.bookings,
                notifications = listOf(newNotification) + it.notifications,
                currentScreen = Screen.BookingConfirmed
            )
        }
    }

    fun sendChatMessage(text: String) {
        if (text.isBlank()) return
        val userMsg = ChatMessage(
            id = "m_${System.currentTimeMillis()}",
            senderName = _uiState.value.userName,
            isFromPandit = false,
            text = text.trim(),
            timestamp = "Just now"
        )
        _uiState.update { it.copy(chatMessages = it.chatMessages + userMsg) }
    }

    fun toggleFavoriteService(serviceId: String) {
        _uiState.update {
            val favs = it.favoriteServiceIds.toMutableSet()
            if (favs.contains(serviceId)) favs.remove(serviceId) else favs.add(serviceId)
            it.copy(favoriteServiceIds = favs)
        }
    }

    fun toggleFavoritePandit(panditId: String) {
        _uiState.update {
            val favs = it.favoritePanditIds.toMutableSet()
            if (favs.contains(panditId)) favs.remove(panditId) else favs.add(panditId)
            it.copy(favoritePanditIds = favs)
        }
    }

    fun addToCart(productId: String) {
        _uiState.update {
            val updated = it.cartItems.toMutableMap()
            updated[productId] = (updated[productId] ?: 0) + 1
            it.copy(cartItems = updated, toastMessage = "Item added to Puja Kit")
        }
    }

    fun clearToast() {
        _uiState.update { it.copy(toastMessage = null) }
    }

    fun setSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun selectCategory(category: String) {
        _uiState.update { it.copy(selectedCategory = category) }
    }

    fun login(phone: String, isGuestMode: Boolean = false) {
        _uiState.update {
            it.copy(
                isLoggedIn = true,
                isGuest = isGuestMode,
                userPhone = if (phone.isNotBlank()) phone else "+91 98765 43210",
                currentScreen = Screen.Home,
                activeTab = "Home"
            )
        }
    }

    fun cancelBooking(bookingId: String) {
        _uiState.update { state ->
            val updated = state.bookings.map {
                if (it.id == bookingId) it.copy(status = BookingStatus.CANCELLED) else it
            }
            state.copy(bookings = updated)
        }
    }

    fun toggleLanguage() {
        _uiState.update { it.copy(isHindi = !it.isHindi) }
    }

    fun setActivePanchangDay(day: String) {
        _uiState.update { it.copy(activePanchangDay = day) }
    }

    fun selectMantra(mantraId: String) {
        _uiState.update { it.copy(selectedMantraId = mantraId, japaCounter = 0) }
    }

    fun incrementJapa() {
        _uiState.update { state ->
            val newCount = state.japaCounter + 1
            if (newCount >= 108) {
                state.copy(
                    japaCounter = 0,
                    totalJapaRounds = state.totalJapaRounds + 1,
                    toastMessage = "1 Mala (108 Japa) Completed! Har Har Mahadev"
                )
            } else {
                state.copy(japaCounter = newCount)
            }
        }
    }

    fun resetJapa() {
        _uiState.update { it.copy(japaCounter = 0) }
    }

    fun toggleChecklistItem(itemId: String) {
        _uiState.update { state ->
            val updated = state.preparationChecklist.map {
                if (it.id == itemId) it.copy(isChecked = !it.isChecked) else it
            }
            state.copy(preparationChecklist = updated)
        }
    }

    fun reloadChecklistForService(serviceTitle: String) {
        _uiState.update {
            it.copy(preparationChecklist = VedikaData.getPreparationChecklist(serviceTitle))
        }
    }
}

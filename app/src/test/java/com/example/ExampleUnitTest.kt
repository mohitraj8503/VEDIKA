package com.example

import com.example.model.BookingStatus
import com.example.viewmodel.Screen
import com.example.viewmodel.VedikaViewModel
import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {
  @Test
  fun addition_isCorrect() {
    assertEquals(4, 2 + 2)
  }

  @Test
  fun testVedikaInitialStateAndNavigation() {
    val viewModel = VedikaViewModel()
    assertEquals(Screen.Splash, viewModel.uiState.value.currentScreen)
    assertEquals("Jamshedpur", viewModel.uiState.value.selectedCity)

    // Test Tab Navigation
    viewModel.setBottomNavTab("Bookings")
    assertEquals(Screen.MyBookings, viewModel.uiState.value.currentScreen)
    assertEquals("Bookings", viewModel.uiState.value.activeTab)

    // Test Booking Flow
    viewModel.startBooking("s1")
    assertEquals(Screen.BookingFlow, viewModel.uiState.value.currentScreen)
    assertEquals("Griha Pravesh Puja", viewModel.uiState.value.bookingDraft.service?.title)

    viewModel.proceedToPayment()
    assertEquals(Screen.Payment, viewModel.uiState.value.currentScreen)

    viewModel.confirmPaymentAndBook()
    assertEquals(Screen.BookingConfirmed, viewModel.uiState.value.currentScreen)
    assertEquals(BookingStatus.CONFIRMED, viewModel.uiState.value.bookings.first().status)
  }
}


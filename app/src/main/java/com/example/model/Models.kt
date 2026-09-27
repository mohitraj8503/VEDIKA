package com.example.model

enum class CategoryIconType {
    PANDIT_BOOKING,
    PUJA_HAVAN,
    KATHA_BHAJAN,
    TEMPLE_DARSHAN,
    RELIGIOUS_EVENTS,
    SANSKAR,
    PUJA_SAMAGRI,
    SPIRITUAL_EXPERTS
}

data class ServiceCategory(
    val id: String,
    val name: String,
    val hindiName: String,
    val iconType: CategoryIconType,
    val description: String
)

data class ServiceItem(
    val id: String,
    val title: String,
    val category: String,
    val rating: Float,
    val reviewCount: Int,
    val price: Int,
    val originalPrice: Int,
    val discountPercent: Int,
    val durationHours: String,
    val panditCount: String,
    val samagriIncluded: Boolean,
    val isVerified: Boolean = true,
    val shortDescription: String,
    val overview: String,
    val whatsIncluded: List<String>,
    val ritualProcess: List<String>,
    val imageUrl: String
)

data class PanditProfile(
    val id: String,
    val name: String,
    val title: String = "Acharya / Pandit",
    val isVerified: Boolean = true,
    val rating: Float,
    val reviewCount: Int,
    val specializations: List<String>,
    val experienceYears: Int,
    val basePrice: Int,
    val city: String,
    val availableForInterCity: Boolean = true,
    val travelFeeEstimate: Int = 1200,
    val languages: List<String> = listOf("Sanskrit", "Hindi", "Maithili"),
    val bio: String,
    val distanceKm: Float = 1.2f,
    val imageUrl: String
)

data class TempleItem(
    val id: String,
    val name: String,
    val location: String,
    val state: String,
    val rating: Float,
    val reviewCountString: String,
    val tags: List<String>,
    val timing: String,
    val deity: String,
    val darshanPrice: Int = 0,
    val vipDarshanPrice: Int = 500,
    val hasLiveAarti: Boolean = true,
    val imageUrl: String
)

data class KathaEvent(
    val id: String,
    val title: String,
    val dateDay: String,
    val dateMonth: String,
    val artistOrSpeaker: String,
    val venue: String,
    val city: String,
    val time: String,
    val priceText: String = "Free Pass / Registration",
    val isLiveEvent: Boolean = false,
    val imageUrl: String
)

data class SamagriProduct(
    val id: String,
    val name: String,
    val hindiName: String,
    val category: String,
    val price: Int,
    val weightOrPieces: String,
    val description: String,
    val imageUrl: String
)

enum class BookingStatus(val displayName: String) {
    CONFIRMED("Confirmed"),
    PENDING("Pending"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled")
}

data class BookingRecord(
    val id: String,
    val serviceTitle: String,
    val date: String,
    val time: String,
    val panditName: String,
    val location: String,
    val status: BookingStatus,
    val totalAmount: Int,
    val isInterCity: Boolean = false,
    val originCity: String = "",
    val destinationCity: String = "",
    val serviceCharges: Int = 5100,
    val samagriCharges: Int = 0,
    val platformFee: Int = 99,
    val imageUrl: String = "https://images.unsplash.com/photo-1609137144813-7d9921338f24?auto=format&fit=crop&w=800&q=80"
)

data class ChatMessage(
    val id: String,
    val senderName: String,
    val isFromPandit: Boolean,
    val text: String,
    val timestamp: String,
    val hasAttachment: Boolean = false,
    val attachmentName: String? = null,
    val attachmentSize: String? = null
)

data class NotificationItem(
    val id: String,
    val category: String,
    val title: String,
    val description: String,
    val timeAgo: String,
    val isUnread: Boolean = false
)

data class PanchangDay(
    val dateString: String,
    val dayName: String,
    val tithi: String,
    val paksha: String,
    val nakshatra: String,
    val yoga: String,
    val karana: String,
    val sunrise: String,
    val sunset: String,
    val rahuKaal: String,
    val abhijitMuhurat: String,
    val brahmaMuhurat: String,
    val amritKaal: String,
    val moonSign: String,
    val sunSign: String,
    val samvatYear: String = "Vikram Samvat 2083"
)

data class ShubhMuhuratItem(
    val id: String,
    val category: String,
    val title: String,
    val dates: List<String>,
    val bestTime: String,
    val nakshatra: String,
    val significance: String,
    val linkedServiceId: String
)

data class FestivalEvent(
    val id: String,
    val name: String,
    val hindiName: String,
    val date: String,
    val daysRemaining: Int,
    val tithi: String,
    val description: String,
    val recommendedPujaId: String
)

data class MantraItem(
    val id: String,
    val name: String,
    val deity: String,
    val mantraDevanagari: String,
    val transliteration: String,
    val meaning: String,
    val targetCount: Int = 108,
    val benefits: String
)

data class PujaPreparationItem(
    val id: String,
    val title: String,
    val quantity: String,
    val isProvidedByPandit: Boolean,
    val isChecked: Boolean = false
)

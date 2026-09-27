# 🕉️ VEDIKA (वेदिका)

<div align="center">

### *India’s Digital Vedic, Spiritual & Religious Services Ecosystem & Marketplace*

> **परंपरा, आपके द्वार।**  
> *Transforming India's fragmented devotional and spiritual landscape into a trusted, unified, modern technology platform.*

[![Status: Active Development](https://img.shields.io/badge/Status-Active%20Development-FF6B00?style=for-the-badge&logo=android&logoColor=white)]()
[![Domain: Devotional Tech / FaithTech](https://img.shields.io/badge/Domain-FaithTech%20%2F%20Vedic%20Marketplace-7B2CBF?style=for-the-badge)]()
[![Ecosystem: Android App](https://img.shields.io/badge/Platform-Android%20%7C%20Gemini%20AI-008080?style=for-the-badge&logo=android)]()
[![Target: Pan--India & NRI Diaspora](https://img.shields.io/badge/Reach-Pan--India%20%26%20Global%20Devotees-1E3A8A?style=for-the-badge)]()

<br/>

[Overview](#-1-executive-overview) • [Mobile App Setup](#-2-android-app-setup--local-run) • [The Core Problem](#-3-the-core-problem--the-big-idea) • [Product Universe](#-4-the-vedika-service-universe) • [Inter-City Mobility](#-5-inter-city-pandit--ritual-dispatch) • [System Architecture](#-6-technology-architecture--system-design) • [Complete Blueprint](#-7-complete-business-blueprint)

---

</div>

## 📖 1. Executive Overview

**VEDIKA (वेदिका)** is a full-stack digital marketplace and operating system designed to organize, standardize, and digitize India’s massive, multi-billion-dollar religious, spiritual, devotional, pilgrimage, and ritual services ecosystem.

From discovery and verified credentials to cross-city travel scheduling, standardized samagri delivery, escrow payments, and live streaming — VEDIKA replaces chaotic phone calls and local word-of-mouth with a seamless, high-trust digital infrastructure.

```
       [ Devotee / Customer ] 
                  │
       ┌──────────▼──────────┐
       │   VEDIKA PLATFORM   │ ◄─── AI Muhurat Engine & Smart Matcher
       └──────────┬──────────┘
                  │
 ┌────────────────┼────────────────┬────────────────┐
 │                │                │                │
▼                ▼                ▼                ▼
[ Verified       [ Samagri &      [ Temple SaaS    [ Bhajan/Katha   
  Acharyas ]       Kits Supply ]    & Darshan ]      Artists ]
```

---

## 📱 2. Android App Setup & Local Run

VEDIKA's mobile client is powered by Android and Google Gemini AI.

### Prerequisites
- [Android Studio](https://developer.android.com/studio) (Giraffe or newer recommended)
- Android SDK 34+
- Gemini API Key

### Running Locally
1. Clone the repository:
   ```bash
   git clone https://github.com/mohitraj8503/VEDIKA.git
   cd VEDIKA
   ```
2. Open **Android Studio** and select **Open**, selecting this project directory.
3. Allow Android Studio to sync Gradle and download dependencies.
4. Create a `.env` file in the root directory (based on `.env.example`):
   ```env
   GEMINI_API_KEY=your_gemini_api_key_here
   ```
5. Build and launch on an emulator or physical device.

---

## 💡 3. The Core Problem & The Big Idea

### ❌ The Status Quo ("Pandit Ji ka number hai?")
* **High Fragmentation:** 95%+ of religious and ritual bookings in India occur offline via unverified references.
* **Pricing Opacity:** Arbitrary pricing, lack of clarity on Dakshina vs. travel vs. Samagri costs.
* **Inter-City Roadblocks:** Extremely difficult for devotees living in Tier-1 metros or abroad to book authentic Vedic scholars from holy cities (Varanasi, Haridwar, Deoghar, Ujjain, Ayodhya, Puri, Tirupati).
* **Samagri Hassles:** Incomplete or low-quality ritual ingredients causing friction during auspicious ceremonies.

### ✅ The VEDIKA Solution
> **Select Ritual → Select Location/Tradition → Pick Verified Scholar → Select Samagri Kit → Escrow Pay → Track & Perform**

---

## 🌌 4. The VEDIKA Service Universe

| Vertical | Services Included | Value Proposition |
| :--- | :--- | :--- |
| **Pujas & Havans** | Rudrabhishek, Satyanarayan Katha, Griha Pravesh, Navchandi, Mahamrityunjaya | Verified Vedic purohits matched by language, tradition, and gotra. |
| **Sanskar & Milestones** | Vivah Sanskar, Namkaran, Mundan, Janeu (Upanayan), Antyeshti & Shraddha | End-to-end milestone management with ritual guidelines. |
| **Spiritual Performances** | Bhajan Mandalis, Kirtan Groups, Jagran Troupes, Classical Devotional Artists | Structured portfolio previews, transparent pricing & audio/video reels. |
| **Katha & Discourses** | Shrimad Bhagwat, Ram Katha, Shiv Puran, Spiritual Keynote Speakers | Booking management, venue planning, live-stream infrastructure. |
| **Temple & Teerth SaaS** | E-Darshan, Seva & Puja Booking, Prasad Delivery, Queue Optimization | Digitizing temple administration with transparent devotee ledger. |
| **Pure Samagri Marketplace** | Certified pure havan samagri, customized ritual kits, pre-packaged boxes | Standardized, unadulterated items delivered to doorstep prior to ritual. |

---

## 🚆 5. Inter-City Pandit & Ritual Dispatch

One of VEDIKA's core differentiators is its structured **Cross-City Priest Logistics Engine**:

```mermaid
sequenceDiagram
    autonumber
    actor User as Devotee (e.g. Delhi / NRI)
    participant Platform as VEDIKA Engine
    actor Priest as Tirth Priest (e.g. Varanasi / Deoghar)
    participant Logistics as Samagri / Travel Partner
    
    User->>Platform: Requests Authentic Vedic Rudrabhishek
    Platform->>Platform: Calculates Distance, Travel Logistics & Muhurat
    Platform->>Priest: Instant Notification with Travel Stipend & Details
    Priest-->>Platform: Confirms Acceptance
    Platform->>Logistics: Auto-dispatches Certified Samagri Kit
    Platform->>User: Confirms Booking + Live Travel Tracking + Checklist
    Priest->>User: Arrives & Conducts Sacred Ritual
    User->>Platform: Post-Ritual Feedback & Dakshina Release
```

---

## 🏗️ 6. Technology Architecture & System Design

VEDIKA is architected as an enterprise-grade, scalable multi-sided platform:

### High-Level Tech Stack
* **Client App:** Android (Jetpack Compose / Kotlin), Google Gemini AI Studio integration.
* **Core API & Microservices:** Node.js / TypeScript, FastAPI (AI/Muhurat Engine).
* **Databases:** PostgreSQL (Relational transactions & bookings), Redis (High-speed caching & availability locks), Pinecone (Semantic search for rituals and scriptures).
* **Payments & Escrow:** UPI AutoPay, Razorpay / Stripe (Global NRI remittances), Smart Escrow Payouts.
* **Communication & Streaming:** WebRTC / LiveKit for E-Pujas and virtual consultations, WhatsApp Business Cloud API for real-time ritual alerts.

### Core Database Entities
* `User` (Devotee / Yajman)
* `Provider` (Pandit, Shastri, Acharya, Artist, Vendor)
* `ServiceCatalog` (Rituals, Havans, Kathas with tradition & Samagri manifests)
* `Booking` (State machine: `REQUESTED` ➔ `CONFIRMED` ➔ `DISPATCHED` ➔ `IN_PROGRESS` ➔ `COMPLETED` ➔ `SETTLED`)
* `Temple` (SaaS administration, daily sevas, darshan slots)
* `SamagriOrder` (E-Commerce inventory & kit fulfillment)

---

## 💰 7. Business & Monetization Model

1. **Marketplace Commission:** 10% – 20% platform take-rate on ritual service bookings.
2. **Samagri E-Commerce Margin:** 25% – 40% margin on certified pure puja kits and spiritual merchandise.
3. **Temple Management SaaS:** Tiered subscription (SaaS) for temples managing queues, donations, and accommodations.
4. **Event Management & Mega-Kathas:** Full-service event orchestration fee for multi-day religious festivals.
5. **VEDIKA Gold / Family Membership:** Annual subscription offering monthly sankalp pujas, personalized astrological muhurats, and priority booking.

---

## 📑 8. Complete Business Blueprint

The complete, unabridged **84-section architectural and business blueprint** is documented in [`VEDIKA_Business_Blueprint.md`](./VEDIKA_Business_Blueprint.md).

---

<div align="center">

### 🕉️ *हर घर परंपरा। हर दिल आस्था।*

**Repository Maintained by:** [@mohitraj8503](https://github.com/mohitraj8503)  
*Crafted for the future of India's spiritual economy.*

</div>

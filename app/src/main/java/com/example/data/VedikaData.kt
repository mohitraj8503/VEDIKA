package com.example.data

import com.example.model.*

object VedikaData {

    val cities = listOf(
        "Jamshedpur",
        "Deoghar",
        "Bhagalpur",
        "Varanasi",
        "Patna",
        "Ranchi",
        "Gaya",
        "Delhi NCR",
        "Ujjain",
        "Ayodhya",
        "Puri",
        "Kolkata"
    )

    val categories = listOf(
        ServiceCategory("1", "Pandit Booking", "पंडित बुकिंग", CategoryIconType.PANDIT_BOOKING, "Verified priests for rituals"),
        ServiceCategory("2", "Puja & Havan", "पूजा एवं हवन", CategoryIconType.PUJA_HAVAN, "Authentic Vedic fire ceremonies"),
        ServiceCategory("3", "Katha & Bhajan", "कथा एवं भजन", CategoryIconType.KATHA_BHAJAN, "Spiritual discourses and singers"),
        ServiceCategory("4", "Temple Darshan", "मंदिर दर्शन", CategoryIconType.TEMPLE_DARSHAN, "VIP passes and seva booking"),
        ServiceCategory("5", "Religious Events", "धार्मिक आयोजन", CategoryIconType.RELIGIOUS_EVENTS, "Jagran, satsang and festivals"),
        ServiceCategory("6", "Sanskar Services", "संस्कार", CategoryIconType.SANSKAR, "Mundan, Naamkaran, Upanayan"),
        ServiceCategory("7", "Puja Samagri", "पूजा सामग्री", CategoryIconType.PUJA_SAMAGRI, "Complete authentic ritual kits"),
        ServiceCategory("8", "Spiritual Experts", "आध्यात्मिक गुरु", CategoryIconType.SPIRITUAL_EXPERTS, "Astrology and Vastu consultation")
    )

    val services = listOf(
        ServiceItem(
            id = "s1",
            title = "Griha Pravesh Puja",
            category = "Puja & Havan",
            rating = 4.9f,
            reviewCount = 320,
            price = 5100,
            originalPrice = 7100,
            discountPercent = 28,
            durationHours = "2-3 Hours",
            panditCount = "1-2",
            samagriIncluded = true,
            isVerified = true,
            shortDescription = "Invite prosperity and positivity to your new home with authentic Vedic blessings.",
            overview = "Griha Pravesh Puja is performed before entering a newly constructed or bought house to purify the environment, remove negative energies, and invite divine blessings for health, wealth, and lasting harmony. Our experienced Vedic pandits follow scriptural Vidhi with complete authentic samagri.",
            whatsIncluded = listOf(
                "1 Experienced Vedic Acharya + 1 Assistant Pandit",
                "Complete Pure Samagri Kit (Havan samagri, Kalash, Ghee, Dhoop, Kumkum)",
                "Navagraha Puja and Shanti Path",
                "Vastu Purusha Puja and Havan",
                "Gau Seva Sankalp and Maha Aarti",
                "Purification of entire home with Ganga Jal"
            ),
            ritualProcess = listOf(
                "Dwar Puja and Auspicious Entrance with Kalash",
                "Gauri Ganesh Puja and Navagraha Sthapana",
                "Vastu Devata Avahana and Kuldevi/Devta Puja",
                "Pavitra Havan and Ahuti with Vedic Mantras",
                "Purna Ahuti, Aarti and Ashirwad"
            ),
            imageUrl = "https://images.unsplash.com/photo-1609137144813-7d9921338f24?auto=format&fit=crop&w=800&q=80"
        ),
        ServiceItem(
            id = "s2",
            title = "Satyanarayan Katha",
            category = "Katha & Bhajan",
            rating = 4.8f,
            reviewCount = 240,
            price = 2100,
            originalPrice = 2800,
            discountPercent = 25,
            durationHours = "2-3 Hours",
            panditCount = "1",
            samagriIncluded = true,
            isVerified = true,
            shortDescription = "Perform auspicious Lord Vishnu Katha on Purnima or family occasions.",
            overview = "Sri Satyanarayan Puja and Katha brings peace, harmony, and prosperity into the home. Dedicated to Lord Vishnu, this Katha explains the divine fruits of devotion, truth, and righteous living.",
            whatsIncluded = listOf(
                "Verified Vedic Pandit",
                "Panchamrit and Prasad Guidance",
                "Complete 5 Chapter Katha Recitation",
                "Havan and Sankalp",
                "Vishnu Aarti and Sacred Thread (Kalava)"
            ),
            ritualProcess = listOf(
                "Ganesh Vandana and Kalash Sthapana",
                "Shodashopachara Vishnu Puja",
                "Detailed 5-Adhyay Katha Recital",
                "Havan and Aarti",
                "Prasad Vitaran"
            ),
            imageUrl = "https://images.unsplash.com/photo-1621644827993-3d0bf8818f97?auto=format&fit=crop&w=800&q=80"
        ),
        ServiceItem(
            id = "s3",
            title = "Rudrabhishek Puja",
            category = "Puja & Havan",
            rating = 4.9f,
            reviewCount = 410,
            price = 3500,
            originalPrice = 4500,
            discountPercent = 22,
            durationHours = "2-3 Hours",
            panditCount = "1-2",
            samagriIncluded = true,
            isVerified = true,
            shortDescription = "Sacred Shiva Abhishek with milk, honey, sugarcane juice, and holy water.",
            overview = "Rudrabhishek is one of the most powerful Vedic rituals dedicated to Lord Shiva to eradicate past karmic obstacles, fulfill desires, and bestow protection and inner peace.",
            whatsIncluded = listOf(
                "Shiva Bhakt Vedic Pandit",
                "Full Rudri Path / Shukla Yajurveda chanting",
                "Panchamrit Abhishek Items and Belpatra",
                "Shiva Stotra and Bhasma Aarti"
            ),
            ritualProcess = listOf(
                "Sankalp and Shiva Linga Sthapana",
                "Continuous Abhishek with 108 Belpatra",
                "Namakam and Chamakam Vedic Chanting",
                "Shiva Aarti and Prasad"
            ),
            imageUrl = "https://images.unsplash.com/photo-1544717305-2782549b5136?auto=format&fit=crop&w=800&q=80"
        ),
        ServiceItem(
            id = "s4",
            title = "Mundan Sanskar",
            category = "Sanskar Services",
            rating = 4.9f,
            reviewCount = 180,
            price = 1800,
            originalPrice = 2400,
            discountPercent = 25,
            durationHours = "1-2 Hours",
            panditCount = "1",
            samagriIncluded = true,
            isVerified = true,
            shortDescription = "First haircut ceremony for child purification and longevity.",
            overview = "Mundan Sanskar is one of the 16 primary Hindu Samskaras, purifying the infant of past-life karma, enhancing intellect, and ensuring vibrant physical development.",
            whatsIncluded = listOf(
                "Experienced Purohit for Child Rituals",
                "Havan and Protection Mantras",
                "Turmeric and Sandalwood Lepan",
                "Aarti and Ashirwad"
            ),
            ritualProcess = listOf(
                "Ganesh and Navagraha Invocation",
                "Ceremonial First Strand Cutting",
                "Shanti Havan",
                "Blessing by Elders"
            ),
            imageUrl = "https://images.unsplash.com/photo-1596704017254-9b121068fb31?auto=format&fit=crop&w=800&q=80"
        ),
        ServiceItem(
            id = "s5",
            title = "Namkaran Sanskar",
            category = "Sanskar Services",
            rating = 4.6f,
            reviewCount = 210,
            price = 1500,
            originalPrice = 2000,
            discountPercent = 25,
            durationHours = "1-2 Hours",
            panditCount = "1",
            samagriIncluded = true,
            isVerified = true,
            shortDescription = "Sacred naming ceremony in alignment with Vedic astrology and Nakshatra.",
            overview = "Namkaran Sanskar calculates the child birth constellation (Nakshatra) and Pada to determine the auspicious syllable for lifelong fortune, intellect, and grace.",
            whatsIncluded = listOf(
                "Astrological Name Letter Determination",
                "Katha and Ayushya Havan",
                "Ear Whispering Ritual",
                "Kalyan Sankalp"
            ),
            ritualProcess = listOf(
                "Panchang Shuddhi",
                "Naming in child ear",
                "Ayushya Homa",
                "Ashirwad"
            ),
            imageUrl = "https://images.unsplash.com/photo-1582510003544-4d00b7f74220?auto=format&fit=crop&w=800&q=80"
        ),
        ServiceItem(
            id = "s6",
            title = "Vastu Shanti Puja",
            category = "Puja & Havan",
            rating = 4.9f,
            reviewCount = 195,
            price = 4500,
            originalPrice = 6000,
            discountPercent = 25,
            durationHours = "3-4 Hours",
            panditCount = "2",
            samagriIncluded = true,
            isVerified = true,
            shortDescription = "Harmonize directional energies and remove architectural doshas.",
            overview = "Vastu Shanti addresses spatial imbalances and invokes the 45 presiding deities of the Vastu Mandala to bless residences and commercial workplaces.",
            whatsIncluded = listOf(
                "2 Vastu Shastra Scholars",
                "Full Vastu Mandala Sthapana",
                "Navagraha and Dikpala Ahuti",
                "Copper Vastu Yantra Energization"
            ),
            ritualProcess = listOf(
                "Dikpala Balidana",
                "Vastu Purusha Havan",
                "Yantra Sthapana at Ishan Kona",
                "Maha Mangal Aarti"
            ),
            imageUrl = "https://images.unsplash.com/photo-1605371924599-2d0365da1ae0?auto=format&fit=crop&w=800&q=80"
        )
    )

    val pandits = listOf(
        PanditProfile(
            id = "p1",
            name = "Acharya Vishnu Sharma",
            title = "Vedic Acharya and Rigveda Scholar",
            isVerified = true,
            rating = 4.9f,
            reviewCount = 512,
            specializations = listOf("Vedic Rituals", "Katha", "Havan"),
            experienceYears = 15,
            basePrice = 2100,
            city = "Jamshedpur",
            availableForInterCity = true,
            travelFeeEstimate = 1200,
            languages = listOf("Sanskrit", "Hindi", "Bhojpuri"),
            bio = "Gold medalist in Vedic Studies from Sampurnanand Sanskrit University, Varanasi. Performed over 1,200+ Pujas with authentic scriptural precision across Bihar, Jharkhand, and UP.",
            distanceKm = 1.2f,
            imageUrl = "https://images.unsplash.com/photo-1532375810709-75b1da00537c?auto=format&fit=crop&w=800&q=80"
        ),
        PanditProfile(
            id = "p2",
            name = "Pandit Rajesh Mishra",
            title = "Senior Karmakandi Purohit",
            isVerified = true,
            rating = 4.8f,
            reviewCount = 430,
            specializations = listOf("Marriage", "Sanskar", "Puja"),
            experienceYears = 8,
            basePrice = 1800,
            city = "Jamshedpur",
            availableForInterCity = true,
            travelFeeEstimate = 1000,
            languages = listOf("Hindi", "Maithili", "Sanskrit"),
            bio = "Specialist in Vivah Sanskar, Griha Pravesh, and Shanti Homa. Known for clear explanation of every mantra to family members in simple words.",
            distanceKm = 2.5f,
            imageUrl = "https://images.unsplash.com/photo-1544717305-2782549b5136?auto=format&fit=crop&w=800&q=80"
        ),
        PanditProfile(
            id = "p3",
            name = "Acharya Suresh Tiwari",
            title = "Jyotish and Vastu Specialist",
            isVerified = true,
            rating = 4.9f,
            reviewCount = 620,
            specializations = listOf("Astrology", "Puja", "Vastu"),
            experienceYears = 15,
            basePrice = 2500,
            city = "Deoghar",
            availableForInterCity = true,
            travelFeeEstimate = 1500,
            languages = listOf("Sanskrit", "Hindi", "Bengali"),
            bio = "Renowned Acharya from Deoghar Dham. Expert in inter-city Vedic travels, Rudra Mahayagya, and commercial enterprise sanctifications.",
            distanceKm = 3.8f,
            imageUrl = "https://images.unsplash.com/photo-1582510003544-4d00b7f74220?auto=format&fit=crop&w=800&q=80"
        ),
        PanditProfile(
            id = "p4",
            name = "Pandit Ankit Jha",
            title = "Yajurveda and Havan Specialist",
            isVerified = true,
            rating = 4.7f,
            reviewCount = 310,
            specializations = listOf("Havan", "Satyanarayan Katha"),
            experienceYears = 6,
            basePrice = 1500,
            city = "Bhagalpur",
            availableForInterCity = true,
            travelFeeEstimate = 800,
            languages = listOf("Hindi", "Angika", "Sanskrit"),
            bio = "Dedicated young scholar trained under traditional Gurukula lineage. Highly punctual with deep devotion and melodious mantra recitation.",
            distanceKm = 4.2f,
            imageUrl = "https://images.unsplash.com/photo-1609137144813-7d9921338f24?auto=format&fit=crop&w=800&q=80"
        )
    )

    val temples = listOf(
        TempleItem(
            id = "t1",
            name = "Baba Baidyanath Dham",
            location = "Deoghar",
            state = "Jharkhand",
            rating = 4.9f,
            reviewCountString = "1.2K",
            tags = listOf("Darshan", "Online Booking", "Live Aarti"),
            timing = "4:00 AM - 9:00 PM",
            deity = "Lord Shiva (Jyotirlinga)",
            darshanPrice = 0,
            vipDarshanPrice = 500,
            hasLiveAarti = true,
            imageUrl = "https://images.unsplash.com/photo-1567157577867-05ccb1388e66?auto=format&fit=crop&w=800&q=80"
        ),
        TempleItem(
            id = "t2",
            name = "Kashi Vishwanath Temple",
            location = "Varanasi",
            state = "Uttar Pradesh",
            rating = 4.8f,
            reviewCountString = "2.1K",
            tags = listOf("Darshan", "VIP Darshan", "Aarti Pass"),
            timing = "3:00 AM - 11:00 PM",
            deity = "Lord Shiva",
            darshanPrice = 0,
            vipDarshanPrice = 750,
            hasLiveAarti = true,
            imageUrl = "https://images.unsplash.com/photo-1605647540924-852290f6b0d5?auto=format&fit=crop&w=800&q=80"
        ),
        TempleItem(
            id = "t3",
            name = "Jagannath Puri",
            location = "Puri",
            state = "Odisha",
            rating = 4.9f,
            reviewCountString = "3.4K",
            tags = listOf("Darshan", "Temple Stay", "Special Puja"),
            timing = "5:00 AM - 10:00 PM",
            deity = "Lord Jagannath, Balabhadra, Subhadra",
            darshanPrice = 0,
            vipDarshanPrice = 600,
            hasLiveAarti = true,
            imageUrl = "https://images.unsplash.com/photo-1600100397608-f010f443831b?auto=format&fit=crop&w=800&q=80"
        )
    )

    val kathaEvents = listOf(
        KathaEvent(
            id = "e1",
            title = "Shrimad Bhagwat Katha",
            dateDay = "25",
            dateMonth = "Oct",
            artistOrSpeaker = "By Rajan Ji Maharaj",
            venue = "Tata Auditorium",
            city = "Jamshedpur",
            time = "6:00 PM - 9:00 PM",
            priceText = "Free Pass / Registration",
            isLiveEvent = true,
            imageUrl = "https://images.unsplash.com/photo-1596405344148-2d0ae5f1217a?auto=format&fit=crop&w=800&q=80"
        ),
        KathaEvent(
            id = "e2",
            title = "Bhajan Sandhya",
            dateDay = "12",
            dateMonth = "Nov",
            artistOrSpeaker = "With Popular Bhajan Singers",
            venue = "Jubilee Park",
            city = "Jamshedpur",
            time = "5:00 PM - 8:00 PM",
            priceText = "Free Pass / Registration",
            isLiveEvent = true,
            imageUrl = "https://images.unsplash.com/photo-1514565131-fce0801e5785?auto=format&fit=crop&w=800&q=80"
        ),
        KathaEvent(
            id = "e3",
            title = "Hanuman Katha",
            dateDay = "18",
            dateMonth = "Nov",
            artistOrSpeaker = "By Pujya Morari Bapu",
            venue = "Online Live Streaming",
            city = "Worldwide",
            time = "9:30 AM - 1:00 PM",
            priceText = "Online Live Event",
            isLiveEvent = true,
            imageUrl = "https://images.unsplash.com/photo-1544717305-2782549b5136?auto=format&fit=crop&w=800&q=80"
        )
    )

    val samagriProducts = listOf(
        SamagriProduct(
            id = "sm1",
            name = "Complete Puja Samagri Kit",
            hindiName = "सम्पूर्ण पूजा सामग्री किट",
            category = "Puja Kits",
            price = 499,
            weightOrPieces = "32 Sacred Items Included",
            description = "Contains pure Roli, Kumkum, Chandan, Janeu, Mauli, Kapoor, Supari, Laung, Elaichi, and Havan Dravya.",
            imageUrl = "https://images.unsplash.com/photo-1599818464377-2e55e6270ff1?auto=format&fit=crop&w=800&q=80"
        ),
        SamagriProduct(
            id = "sm2",
            name = "Havan Samagri",
            hindiName = "शुद्ध हवन सामग्री",
            category = "Havan Samagri",
            price = 289,
            weightOrPieces = "1 Kg Pack",
            description = "100% natural herbs, guggul, loban, dry fruits, and sacred wood shavings for pure fragrant ahutis.",
            imageUrl = "https://images.unsplash.com/photo-1609137144813-7d9921338f24?auto=format&fit=crop&w=800&q=80"
        ),
        SamagriProduct(
            id = "sm3",
            name = "Kalash Set (Brass)",
            hindiName = "पीतल कलश एवं नारियल सेट",
            category = "Idols",
            price = 399,
            weightOrPieces = "Brass Kalash + Coconut + Mango Leaves",
            description = "Auspicious engraved pure brass Kalash essential for sthapana and divine blessing invocations.",
            imageUrl = "https://images.unsplash.com/photo-1617478755490-e21232a5eeaf?auto=format&fit=crop&w=800&q=80"
        ),
        SamagriProduct(
            id = "sm4",
            name = "Puja Thali Set",
            hindiName = "सुसज्जित पूजा थाली",
            category = "Puja Kits",
            price = 599,
            weightOrPieces = "Decorated Brass Platter + Diya + Agarbatti Stand",
            description = "Handcrafted traditional ornate brass puja thali with separate katories for chandan, roli, and akshat.",
            imageUrl = "https://images.unsplash.com/photo-1599818464377-2e55e6270ff1?auto=format&fit=crop&w=800&q=80"
        ),
        SamagriProduct(
            id = "sm5",
            name = "Pure Cow Ghee Diya (Box of 50)",
            hindiName = "शुद्ध गाय के घी की बत्तियां",
            category = "Puja Kits",
            price = 199,
            weightOrPieces = "50 Pre-formed Ghee Diyas",
            description = "Ready to light pure cow ghee wicks burning clean for up to 30 minutes with gentle fragrance.",
            imageUrl = "https://images.unsplash.com/photo-1617478755490-e21232a5eeaf?auto=format&fit=crop&w=800&q=80"
        ),
        SamagriProduct(
            id = "sm6",
            name = "Gangajal & Sacred Teerth Kit",
            hindiName = "पवित्र गंगाजल एवं तीर्थ जल",
            category = "Puja Kits",
            price = 149,
            weightOrPieces = "500ml Gangotri Jal",
            description = "Hermetically sealed authentic Gangotri glacier jal for house purification and sankalp.",
            imageUrl = "https://images.unsplash.com/photo-1605647540924-852290f6b0d5?auto=format&fit=crop&w=800&q=80"
        )
    )

    val initialBookings = listOf(
        BookingRecord(
            id = "VED-84920",
            serviceTitle = "Griha Pravesh Puja",
            date = "25 Oct 2026",
            time = "8:00 AM",
            panditName = "Pandit Rajesh Mishra",
            location = "Jamshedpur, Jharkhand",
            status = BookingStatus.CONFIRMED,
            totalAmount = 5199,
            serviceCharges = 5100,
            samagriCharges = 0,
            platformFee = 99,
            imageUrl = "https://images.unsplash.com/photo-1609137144813-7d9921338f24?auto=format&fit=crop&w=800&q=80"
        ),
        BookingRecord(
            id = "VED-62184",
            serviceTitle = "Satyanarayan Katha",
            date = "12 Nov 2026",
            time = "6:00 PM",
            panditName = "Acharya Vishnu Sharma",
            location = "Home Service",
            status = BookingStatus.PENDING,
            totalAmount = 2199,
            serviceCharges = 2100,
            samagriCharges = 0,
            platformFee = 99,
            imageUrl = "https://images.unsplash.com/photo-1621644827993-3d0bf8818f97?auto=format&fit=crop&w=800&q=80"
        ),
        BookingRecord(
            id = "VED-51103",
            serviceTitle = "Temple Darshan (Group)",
            date = "5 Dec 2026",
            time = "7:00 AM",
            panditName = "Kashi Vishwanath Temple",
            location = "Varanasi, UP",
            status = BookingStatus.CONFIRMED,
            totalAmount = 1099,
            serviceCharges = 1000,
            samagriCharges = 0,
            platformFee = 99,
            imageUrl = "https://images.unsplash.com/photo-1561361513-2d000a50f0dc?auto=format&fit=crop&w=800&q=80"
        ),
        BookingRecord(
            id = "VED-31902",
            serviceTitle = "Mundan Sanskar",
            date = "5 Jan 2026",
            time = "10:00 AM",
            panditName = "Pandit Rajesh Mishra",
            location = "Jamshedpur, Jharkhand",
            status = BookingStatus.COMPLETED,
            totalAmount = 1899,
            serviceCharges = 1800,
            samagriCharges = 0,
            platformFee = 99,
            imageUrl = "https://images.unsplash.com/photo-1596704017254-9b121068fb31?auto=format&fit=crop&w=800&q=80"
        )
    )

    val initialChatMessages = listOf(
        ChatMessage(
            id = "m1",
            senderName = "Pandit Rajesh Mishra",
            isFromPandit = true,
            text = "Namaste! How can I help you with the puja details?",
            timestamp = "10:30 AM"
        ),
        ChatMessage(
            id = "m2",
            senderName = "Mohit Raj",
            isFromPandit = false,
            text = "I want to book Griha Pravesh Puja on 25 Oct at 8 AM. Is it available?",
            timestamp = "10:31 AM"
        ),
        ChatMessage(
            id = "m3",
            senderName = "Pandit Rajesh Mishra",
            isFromPandit = true,
            text = "Yes, it is available. I will confirm the details and share the samagri list.",
            timestamp = "10:32 AM"
        ),
        ChatMessage(
            id = "m4",
            senderName = "Pandit Rajesh Mishra",
            isFromPandit = true,
            text = "Here is the list of things needed for your Griha Pravesh:",
            timestamp = "10:32 AM",
            hasAttachment = true,
            attachmentName = "Samagri List (PDF)",
            attachmentSize = "2.4 MB"
        ),
        ChatMessage(
            id = "m5",
            senderName = "Mohit Raj",
            isFromPandit = false,
            text = "Perfect! Please proceed with the booking.",
            timestamp = "10:33 AM"
        )
    )

    val notifications = listOf(
        NotificationItem(
            id = "n1",
            category = "Bookings",
            title = "Booking Confirmed",
            description = "Your Griha Pravesh Puja is confirmed for 25 Oct 2026 • 8:00 AM",
            timeAgo = "2m ago",
            isUnread = true
        ),
        NotificationItem(
            id = "n2",
            category = "Updates",
            title = "New Message",
            description = "Pandit Rajesh Mishra sent a message",
            timeAgo = "10m ago",
            isUnread = true
        ),
        NotificationItem(
            id = "n3",
            category = "Offers",
            title = "Special Offer",
            description = "Get 20% off on Temple Darshan bookings",
            timeAgo = "3h ago",
            isUnread = false
        ),
        NotificationItem(
            id = "n4",
            category = "Bookings",
            title = "Payment Successful",
            description = "5,199 paid successfully for Griha Pravesh Puja",
            timeAgo = "1d ago",
            isUnread = false
        ),
        NotificationItem(
            id = "n5",
            category = "Updates",
            title = "Event Reminder",
            description = "Bhajan Sandhya starts in 2 hours at Jubilee Park",
            timeAgo = "1d ago",
            isUnread = false
        )
    )

    val todayPanchang = PanchangDay(
        dateString = "27 September 2026",
        dayName = "Sunday (रविवार)",
        tithi = "Shukla Paksha Pratipada (प्रतिपदा)",
        paksha = "Shukla Paksha (शुक्ल पक्ष)",
        nakshatra = "Hasta (हस्त - 06:14 PM तक, उपरांत चित्रा)",
        yoga = "Brahma Yoga (ब्रह्म योग)",
        karana = "Kimstughna (किंस्तुघ्न)",
        sunrise = "05:48 AM",
        sunset = "05:54 PM",
        rahuKaal = "04:22 PM - 05:54 PM (वर्जित)",
        abhijitMuhurat = "11:28 AM - 12:16 PM (अति शुभ)",
        brahmaMuhurat = "04:12 AM - 05:00 AM",
        amritKaal = "02:15 PM - 03:45 PM",
        moonSign = "Kanya (कन्या)",
        sunSign = "Kanya (कन्या)",
        samvatYear = "Vikram Samvat 2083"
    )

    val tomorrowPanchang = PanchangDay(
        dateString = "28 September 2026",
        dayName = "Monday (सोमवार)",
        tithi = "Shukla Paksha Dwitiya (द्वितीया)",
        paksha = "Shukla Paksha (शुक्ल पक्ष)",
        nakshatra = "Chitra (चित्रा - 08:30 PM तक, उपरांत स्वाती)",
        yoga = "Indra Yoga (इन्द्र योग)",
        karana = "Bava (बव)",
        sunrise = "05:48 AM",
        sunset = "05:53 PM",
        rahuKaal = "07:20 AM - 08:50 AM (वर्जित)",
        abhijitMuhurat = "11:27 AM - 12:15 PM (अति शुभ)",
        brahmaMuhurat = "04:13 AM - 05:01 AM",
        amritKaal = "04:10 PM - 05:40 PM",
        moonSign = "Kanya (कन्या) उपरांत तुला",
        sunSign = "Kanya (कन्या)",
        samvatYear = "Vikram Samvat 2083"
    )

    val upcomingFestivals = listOf(
        FestivalEvent(
            id = "f1",
            name = "Sharad Navratri Sthapana",
            hindiName = "शारदीय नवरात्रि कलश स्थापना",
            date = "11 October 2026",
            daysRemaining = 14,
            tithi = "Ashwin Shukla Pratipada",
            description = "Auspicious Ghatasthapana and 9 divine days of Goddess Durga worship for spiritual protection and abundance.",
            recommendedPujaId = "s1"
        ),
        FestivalEvent(
            id = "f2",
            name = "Vijayadashami / Dussehra",
            hindiName = "विजयादशमी एवं अपराजिता पूजा",
            date = "20 October 2026",
            daysRemaining = 23,
            tithi = "Ashwin Shukla Dashami",
            description = "Celebration of victory of Dharma over Adharma, Shastra Puja, and auspicious beginning of new ventures.",
            recommendedPujaId = "s2"
        ),
        FestivalEvent(
            id = "f3",
            name = "Karwa Chauth Vrat",
            hindiName = "करवा चौथ व्रत एवं पूजा",
            date = "29 October 2026",
            daysRemaining = 32,
            tithi = "Kartik Krishna Chaturthi",
            description = "Sacred marital vow and moonlight Arghya to Lord Shiva and Parvati for longevity and lifelong harmony.",
            recommendedPujaId = "s2"
        ),
        FestivalEvent(
            id = "f4",
            name = "Dhanteras & Deepawali Lakshmi Puja",
            hindiName = "धनतेरस एवं महालक्ष्मी पूजन",
            date = "08 November 2026",
            daysRemaining = 42,
            tithi = "Kartik Amavasya",
            description = "Auspicious Kubera & Maha Lakshmi invocation for permanent wealth, fortune, and illumination in home and business.",
            recommendedPujaId = "s1"
        )
    )

    val shubhMuhurats = listOf(
        ShubhMuhuratItem(
            id = "m1",
            category = "Griha Pravesh",
            title = "Auspicious House Warming Muhurat",
            dates = listOf("14 Oct 2026", "23 Oct 2026", "02 Nov 2026", "11 Nov 2026"),
            bestTime = "06:15 AM - 08:30 AM (Pratah Kaal)",
            nakshatra = "Rohini & Uttara Phalguni",
            significance = "Guarantees peace, vaastu purification, and steady prosperity in newly entered dwelling.",
            linkedServiceId = "s1"
        ),
        ShubhMuhuratItem(
            id = "m2",
            category = "Satyanarayan Katha",
            title = "Purnima & Ekadashi Katha Muhurat",
            dates = listOf("09 Oct 2026 (Ekadashi)", "14 Oct 2026 (Purnima)", "24 Oct 2026"),
            bestTime = "10:15 AM - 01:00 PM (Madhyahna)",
            nakshatra = "Hasta & Shravana",
            significance = "Universal blessing of Lord Vishnu, fulfillment of sincere wishes, and removal of family anxieties.",
            linkedServiceId = "s2"
        ),
        ShubhMuhuratItem(
            id = "m3",
            category = "Rudrabhishek",
            title = "Pradosh & Somwar Shiv Abhishek",
            dates = listOf("28 Sep 2026 (Somwar)", "05 Oct 2026", "12 Oct 2026 (Pradosh)"),
            bestTime = "06:00 AM - 09:30 AM (Brahma Muhurat)",
            nakshatra = "Ardra & Pushya",
            significance = "Removes grave planetary afflictions, restores sound physical health and longevity.",
            linkedServiceId = "s3"
        ),
        ShubhMuhuratItem(
            id = "m4",
            category = "Mundan Sanskar",
            title = "Child Chudakarana Sanskar Muhurat",
            dates = listOf("18 Oct 2026", "25 Oct 2026", "07 Nov 2026"),
            bestTime = "08:00 AM - 11:15 AM",
            nakshatra = "Mrigashirsha & Swati",
            significance = "Cleanses past-life karmic impressions, promotes intelligence and vitality in infant.",
            linkedServiceId = "s4"
        )
    )

    val vedicMantras = listOf(
        MantraItem(
            id = "mt1",
            name = "Gayatri Mantra",
            deity = "Maa Savitri & Surya Dev",
            mantraDevanagari = "ॐ भूर्भुवः स्वः तत्सवितुर्वरेण्यं भर्गो देवस्य धीमहि धियो यो नः प्रचोदयात्॥",
            transliteration = "Om Bhur Bhuvah Swah Tat-Savitur Varenyam Bhargo Devasya Dheemahi Dhiyo Yo Nah Prachodayat",
            meaning = "We meditate on the supreme transcendental radiance of the divine Sun; may that illumination inspire our intellect and guide our consciousness.",
            targetCount = 108,
            benefits = "Sharper intellect, mental calmness, removal of fears, and spiritual awakening."
        ),
        MantraItem(
            id = "mt2",
            name = "Maha Mrityunjaya Mantra",
            deity = "Lord Shiva (Rudra)",
            mantraDevanagari = "ॐ त्र्यम्बकं यजामहे सुगन्धिं पुष्टिवर्धनम्। उर्वारुकमिव बन्धनान् मृत्योर्मुक्षीय मामृतात्॥",
            transliteration = "Om Tryambakam Yajamahe Sugandhim Pushti-Vardhanam Urvarukamiva Bandhanan Mrityor Mukshiya Mamritat",
            meaning = "We worship the three-eyed Lord Shiva who is fragrant and nourishes all beings. As a ripe cucumber is liberated from its stem, may He release us from bondage and death into immortality.",
            targetCount = 108,
            benefits = "Shield against accidents, relief from chronic illness, peace of mind, and divine protection."
        ),
        MantraItem(
            id = "mt3",
            name = "Shree Ganesh Mool Mantra",
            deity = "Lord Ganesha (Vighnaharta)",
            mantraDevanagari = "ॐ गं गणपतये नमः। वक्रतुण्ड महाकाय सूर्यकोटि समप्रभ। निर्विघ्नं कुरु मे देव सर्वकार्येषु सर्वदा॥",
            transliteration = "Om Gam Ganapataye Namah. Vakratunda Mahakaya Suryakoti Samaprabha. Nirvighnam Kuru Me Deva Sarvakaryeshu Sarvada",
            meaning = "Salutations to Lord Ganesha, with a curved trunk and vast body whose radiance rivals million suns. O Lord, please remove all hurdles in all our undertakings forever.",
            targetCount = 108,
            benefits = "Removes career obstacles, brings auspicious beginnings, clarity, and success."
        ),
        MantraItem(
            id = "mt4",
            name = "Panchakshari Shiva Mantra",
            deity = "Lord Sadashiva",
            mantraDevanagari = "ॐ नमः शिवाय॥",
            transliteration = "Om Namah Shivaya",
            meaning = "I bow to Lord Shiva, the auspicious inner divine consciousness who transcends time and duality.",
            targetCount = 108,
            benefits = "Deep inner stillness, ego dissolution, cosmic harmony, and emotional equilibrium."
        ),
        MantraItem(
            id = "mt5",
            name = "Maha Mantra (Hare Krishna)",
            deity = "Lord Krishna & Radha Rani",
            mantraDevanagari = "हरे कृष्ण हरे कृष्ण कृष्ण कृष्ण हरे हरे। हरे राम हरे राम राम राम हरे हरे॥",
            transliteration = "Hare Krishna Hare Krishna Krishna Krishna Hare Hare, Hare Rama Hare Rama Rama Rama Hare Hare",
            meaning = "O supreme energy of God (Hara) and O all-attractive Supreme Lord (Krishna & Rama), please engage me in Your pure devotional loving service.",
            targetCount = 108,
            benefits = "Fills the heart with divine joy, cleanses emotional stress, promotes unconditional love."
        )
    )

    fun getPreparationChecklist(serviceTitle: String): List<PujaPreparationItem> {
        return listOf(
            // Household items
            PujaPreparationItem("p1", "Pure Cow Milk (कच्चा गाय का दूध)", "1 - 2 Litres", false),
            PujaPreparationItem("p2", "Fresh Flowers & Marigold Garland (माला)", "2 Garlands + 250g loose", false),
            PujaPreparationItem("p3", "Fresh Fruits for Prasad (पंचामृत / मौसमी फल)", "5 Seasonal varieties", false),
            PujaPreparationItem("p4", "Clean Copper / Brass Vessels (लोटा एवं थाली)", "2 Sets cleaned", false),
            PujaPreparationItem("p5", "Seating Mats for Family & Acharya (आसन)", "3 - 5 Asanas", false),
            PujaPreparationItem("p6", "Pure Ghee for Prasad / Kheer (देसी घी)", "250 Grams", false),
            // Pandit provided items
            PujaPreparationItem("p7", "Vedic Havan Kund & Wood (हवन कुंड एवं समिधा)", "1 Set pure mango wood", true),
            PujaPreparationItem("p8", "Sacred Havan Herbs (शुद्ध हवन सामग्री)", "1.5 Kg premium blend", true),
            PujaPreparationItem("p9", "Pure Cow Desi Ghee for Agni Ahuti", "1 Kg Vedic Bilona", true),
            PujaPreparationItem("p10", "Sacred Kalash with Nariyal & Mauli", "Complete consecrated set", true),
            PujaPreparationItem("p11", "Navagraha Vastram, Chandan, Roli & Dhoop", "Complete ritual basket", true),
            PujaPreparationItem("p12", "Ganga Jal & Sacred Teerth Water", "500 ml sealed bottle", true)
        )
    }
}

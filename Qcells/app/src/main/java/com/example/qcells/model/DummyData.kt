package com.example.qcells.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import kotlin.random.Random
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
object DummyData {
    val applications = listOf(
        Application(
            name = "TikTok",
            category = ApplicationCategory.SOCIAL,
            ratingInTenths = 44,
            developerName = "TikTok Pte. Ltd.",
            installStatus = InstallStatus.NOT_INSTALLED,
            iconColorAsInt = Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat()).toArgb(),
            uuid = Uuid.random(),
            description = "TikTok is THE destination for mobile videos. On TikTok, short-form videos are exciting, spontaneous, and genuine. Whether you’re a sports fanatic, a pet enthusiast, or just looking for a laugh, there’s something for everyone on TikTok. All you have to do is watch, engage with what you like, skip what you don’t, and you’ll find an endless stream of short videos that feel personalized just for you. From your morning coffee to your afternoon errands, TikTok has the videos that are guaranteed to make your day.",
        ),
        Application(
            name = "Instagram",
            category = ApplicationCategory.SOCIAL,
            ratingInTenths = 43,
            developerName = "Meta Platforms, Inc.",
            installStatus = InstallStatus.INSTALLED,
            iconColorAsInt = Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat()).toArgb(),
            uuid = Uuid.random(),
            description = "Instagram (from Facebook) allows you to create and share your photos, stories, and videos with the friends and followers you care about. Connect with friends, share what you're up to, or see what's new from others all over the world. Explore our community where you can feel free to be yourself and share everything from your daily moments to life's highlights.",
        ),
        Application(
            name = "Facebook",
            category = ApplicationCategory.SOCIAL,
            ratingInTenths = 38,
            developerName = "Meta Platforms, Inc.",
            installStatus = InstallStatus.NOT_INSTALLED,
            iconColorAsInt = Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat()).toArgb(),
            uuid = Uuid.random(),
            description = "Keeping up with friends is faster and easier than ever. Share updates and photos, engage with friends and Pages, and stay connected to communities important to you. The Facebook app does more than help you stay connected with your friends and interests. It's also your personal organizer for storing, saving and sharing photos. It's easy to share photos straight from your Android camera, and you have full control over your photos and privacy settings. You can choose when to keep individual photos private or even set up a secret photo album to control who sees it.",
        ),
        Application(
            name = "WhatsApp Messenger",
            category = ApplicationCategory.COMMUNICATION,
            ratingInTenths = 42,
            developerName = "WhatsApp LLC",
            installStatus = InstallStatus.INSTALLED,
            iconColorAsInt = Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat()).toArgb(),
            uuid = Uuid.random(),
            description = "WhatsApp from Meta is a FREE messaging and video calling app. It’s used by over 2B people in more than 180 countries. It’s simple, reliable, and private, so you can easily keep in touch with your friends and family. WhatsApp works across mobile and desktop even on slow connections, with no subscription fees*. Private messaging across the world. Your personal messages and calls to friends and family are end-to-end encrypted. No one outside of your chats, not even WhatsApp, can read or listen to them.",
        ),
        Application(
            name = "Telegram",
            category = ApplicationCategory.COMMUNICATION,
            ratingInTenths = 43,
            developerName = "Telegram FZ-LLC",
            installStatus = InstallStatus.NOT_INSTALLED,
            iconColorAsInt = Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat()).toArgb(),
            uuid = Uuid.random(),
            description = "Pure instant messaging — simple, fast, secure, and synced across all your devices. One of the world's top 10 most downloaded apps with over 500 million active users. FAST: Telegram is the fastest messaging app on the market, connecting people via a unique, distributed network of data centers around the globe. SYNCED: You can access your messages from all your phones, tablets and computers at once. Telegram apps are standalone, so you don’t need to keep your phone connected. Start typing on one device and finish the message from another. Never lose your data again.",
        ),
        Application(
            name = "Spotify",
            category = ApplicationCategory.MUSIC_AND_AUDIO,
            ratingInTenths = 44,
            developerName = "Spotify AB",
            installStatus = InstallStatus.INSTALLED,
            iconColorAsInt = Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat()).toArgb(),
            uuid = Uuid.random(),
            description = "With Spotify, you can listen to music and play millions of songs and podcasts for free. Stream music and podcasts you love and find music - or your next favorite song - from all over the world. Discover new music, albums, and podcasts. Search for your favorite song, artist, or podcast. Enjoy music playlists and an unparalleled daily mix made just for you. Make and share your own playlists. Find music for any mood and activity. Listen on your mobile and tablet.",
        ),
        Application(
            name = "Netflix",
            category = ApplicationCategory.ENTERTAINMENT,
            ratingInTenths = 42,
            developerName = "Netflix, Inc.",
            installStatus = InstallStatus.NOT_INSTALLED,
            iconColorAsInt = Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat()).toArgb(),
            uuid = Uuid.random(),
            description = "Looking for the most talked about TV shows and movies from the around the world? They’re all on Netflix. We’ve got award-winning series, movies, documentaries, and stand-up specials. And with the mobile app, you get Netflix while you travel, commute, or just take a break. What you’ll love about Netflix: We add TV shows and movies all the time. Browse new titles or search for your favorites, and stream videos right on your device. The more you watch, the better Netflix gets at recommending TV shows and movies you’ll love.",
        ),
        Application(
            name = "Amazon Shopping",
            category = ApplicationCategory.SHOPPING,
            ratingInTenths = 43,
            developerName = "Amazon Mobile LLC",
            installStatus = InstallStatus.NOT_INSTALLED,
            iconColorAsInt = Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat()).toArgb(),
            uuid = Uuid.random(),
            description = "Product Features Amazon Shopping offers app-only benefits to help make shopping on Amazon faster and easier than shopping on your desktop. Never miss a delivery. Get real-time tracking and delivery notifications so you know where your package is and when it arrives. Know exactly what you’re purchasing. Full 360° product view lets you see items from every angle. “View in your room” makes sure it fits by using your phone’s camera and AR so you can see it in your space. We’ll notify you when items go on sale. Just tap the heart icon to save items to Your Lists and we’ll alert you of price drops so you don’t miss a deal.",
        )
    )
}

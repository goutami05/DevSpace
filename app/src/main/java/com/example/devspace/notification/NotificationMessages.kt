package com.example.devspace.notification

data class NotificationItem(
    val title: String,
    val message: String
)

object NotificationMessages {

    val messages = listOf(

        NotificationItem(
            "🚀 Trending Repositories",
            "Discover today's most popular GitHub repositories."
        ),

        NotificationItem(
            "📰 Fresh Tech News",
            "Stay updated with the latest technology headlines."
        ),

        NotificationItem(
            "⭐ Explore Your Bookmarks",
            "Your saved repositories and articles are waiting."
        ),

        NotificationItem(
            "💡 Developer Tip",
            "Explore a new open-source project today."
        ),

        NotificationItem(
            "🔥 Time to Explore",
            "Open DevSpace and discover something new!"
        ),

        NotificationItem(
            "📱 Android Updates",
            "Check out the latest Android development news."
        ),

        NotificationItem(
            "🌍 Global Open Source",
            "See what developers around the world are building."
        ),

        NotificationItem(
            "⚡ Trending in Tech",
            "Catch up with today's hottest technology stories."
        ),

        NotificationItem(
            "📚 Learn Something New",
            "Explore a repository that could improve your skills."
        ),

        NotificationItem(
            "🎯 Coding Inspiration",
            "Your next favorite GitHub project could be one tap away."
        ),

        NotificationItem(
            "🔍 Repository Spotlight",
            "A popular repository is waiting to be explored."
        ),

        NotificationItem(
            "🛠 Build Better Apps",
            "Find open-source projects that inspire your next app."
        ),

        NotificationItem(
            "📈 Stay Ahead",
            "Keep up with the latest developer trends."
        ),

        NotificationItem(
            "🌟 Top Picks",
            "Browse today's recommended repositories."
        ),

        NotificationItem(
            "💻 Ready to Code?",
            "Open DevSpace and discover exciting developer resources."
        ),

        NotificationItem(
            "🔔 DevSpace Reminder",
            "You haven't explored DevSpace in a while."
        ),

        NotificationItem(
            "📢 Developer Feed",
            "Fresh content has been curated just for you."
        ),

        NotificationItem(
            "🚀 Discover More",
            "Explore repositories from different programming languages."
        ),

        NotificationItem(
            "✨ Daily Developer Boost",
            "Learn something useful in just a few minutes."
        ),

        NotificationItem(
            "🎉 Welcome Back",
            "Your next discovery is waiting inside DevSpace!"
        )

    )
}
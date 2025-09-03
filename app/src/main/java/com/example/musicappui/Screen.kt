package com.example.musicappui

import androidx.annotation.DrawableRes

sealed class Screen(
    val title: String,
    val route:String
) {

    sealed class BottomScreen(val bTitle: String, val bRoute: String, @DrawableRes val icon: Int)
        : Screen(bTitle, bRoute){
        object Home: BottomScreen(
            "Home",
            "home",
            R.drawable.ic_baseline_home_24
        )
        object Library: BottomScreen(
            "Library",
            "library",
            R.drawable.ic_outline_library_music_24
        )
        object Browse: BottomScreen(
            "Browse",
            "browse",
            R.drawable.ic_outline_search_24
        )
    }

    sealed class DrawerScreen(val dTitle: String, val dRoute: String, @DrawableRes val icon: Int)
        : Screen(dTitle, dRoute){
            object Account: DrawerScreen(
                "Account",
                "account",
                R.drawable.ic_account
            )
        object Subscription: DrawerScreen(
            "Subscription",
            "subscribe",
            R.drawable.ic_subscription
        )
        object AddAccount: DrawerScreen(
            "AddAccount",
            "add_account",
            R.drawable.baseline_person_add_alt_1_24
        )
    }
}

val screensInBottom = listOf(
    Screen.BottomScreen.Home,
    Screen.BottomScreen.Library,
    Screen.BottomScreen.Browse

)

val screensInDrawer = listOf(
    Screen.DrawerScreen.Account,
    Screen.DrawerScreen.Subscription,
    Screen.DrawerScreen.AddAccount

)
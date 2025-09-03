package com.example.musicappui.ui.theme

import com.example.musicappui.R
import androidx.annotation.DrawableRes

data class Lib(@DrawableRes val icon: Int, val name: String)

val libraries= listOf<Lib>(
    Lib( R.drawable.baseline_playlist_add_24, "Playlist"),
    Lib( R.drawable.baseline_person_outline_24, "Artists"),
    Lib( R.drawable.baseline_album_24, "Albums"),
    Lib( R.drawable.outline_music_note_24, "Songs"),
    Lib( R.drawable.outline_radio_24, "Genre")
)
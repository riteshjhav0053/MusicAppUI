package com.example.musicappui.ui.theme

import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.runtime.Composable
import com.example.musicappui.R

@Composable
fun BrowseView(){
    val categories = listOf("Hits", "Monday Mood", "Workout", "Chill", "Party", "English")
    LazyVerticalGrid(GridCells.Fixed(2)) {
            items(categories){
                    cat->
                BrowserItem(cat = cat,  R.drawable.ic_outline_search_24)
            }

    }
}

//@Preview(showBackground = true)
//@Composable
//fun BrowsePreview(){
//    BrowseView()
//}



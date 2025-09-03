package com.example.musicappui.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import com.example.musicappui.R


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun HomeView(){
    val categories = listOf("Hits", "Monday Mood", "Workout", "Chill", "Party")
    val grouped = listOf<String>("New Releases", "Favorites", "Top 10 Songs", "Top Indian Artists")
    LazyColumn {
        grouped.forEach{key ->
            stickyHeader {
                Text(text = key.toString(),
                    modifier = Modifier.padding(16.dp))
                LazyRow {
                    items(categories){
                        cat->
                        BrowserItem(cat = cat,  R.drawable.ic_baseline_home_24)
                    }
                }
            }
        }
    }
}
@Composable
fun BrowserItem(cat:String, drawable:Int){
    Card(modifier = Modifier.padding(16.dp).size(200.dp), border = BorderStroke(3.dp, color = Color.Black))  {
        Column(modifier = Modifier.
        padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
            ) {
            Text(cat)
            Image(painter = painterResource(drawable),
                contentDescription = cat,)
        }
    }
}
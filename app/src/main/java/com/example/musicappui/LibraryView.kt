package com.example.musicappui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.musicappui.ui.theme.Lib
import com.example.musicappui.ui.theme.libraries


@Composable
fun LibraryView(){
    LazyColumn {
        items(libraries){
            lib ->
            LibItem(lib)
        }

    }
}

@Composable
fun LibItem(lib: Lib){
    Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Row {
                Image(painter = painterResource(lib.icon),
                    modifier = Modifier.padding(horizontal = 8.dp),
                    contentDescription = lib.name)
                Text(lib.name)
            }
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "More")
        }
        Divider(color = Color.LightGray, modifier = Modifier.padding(vertical = 8.dp))
    }
}
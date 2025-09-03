package com.example.musicappui.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable

fun Subscription(){
    Column(
        modifier = Modifier
            .height(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Manage Subscription")
        Card(modifier = Modifier
            .padding(8.dp),
            elevation = 4.dp) {
            Column(modifier = Modifier.padding(8.dp)) {
                    Text("Musical")
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Free Trial")
                        TextButton(onClick = {}) {
                            Text("See all plans")
                            Icon(
                                Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = "See all plans"
                            )
                        }
                    }
                Divider(modifier = Modifier.padding(horizontal = 8.dp), thickness = 1.dp)

                Row(modifier = Modifier.padding(vertical = 16.dp)) {
                    Icon(
                        Icons.Default.AccountBox,
                        contentDescription = "Account"
                    )
                    Text("Get plans")
                }
            }
        }
    }
}
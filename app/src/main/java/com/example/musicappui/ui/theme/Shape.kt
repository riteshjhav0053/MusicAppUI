package com.example.musicappui.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val AppShapes = Shapes(
    small = RoundedCornerShape(8.dp),   // Buttons, Chips
    medium = RoundedCornerShape(16.dp), // Cards, TextFields
    large = RoundedCornerShape(24.dp)   // Dialogs, Sheets
)

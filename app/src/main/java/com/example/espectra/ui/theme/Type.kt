package com.example.espectra.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.espectra.R

// ==========================
// FONTES
// ==========================

val Imprima = FontFamily(
    Font(R.font.imprima_regular)
)

val InclusiveSans = FontFamily(
    Font(R.font.inclusivesans_variablefont_wght)
)

val InstrumentSans = FontFamily(
    Font(R.font.instrumentsans_variablefont_wdth_wght)
)

// ==========================
// TYPOGRAPHY
// ==========================

val Typography = Typography(

    bodyLarge = TextStyle(
        fontFamily = Imprima,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    titleLarge = TextStyle(
        fontFamily = InclusiveSans,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),

    headlineMedium = TextStyle(
        fontFamily = InstrumentSans,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    )
)
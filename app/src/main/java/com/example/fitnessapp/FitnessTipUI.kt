package com.example.fitnessapp

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class FitnessTipUI(
    @DrawableRes val imageRes : Int,
    val day : Int,
    @StringRes val title : Int,
    @StringRes val description : Int,
    val expand : Boolean = true
)

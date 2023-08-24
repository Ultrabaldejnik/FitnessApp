package com.example.fitnessapp.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.fitnessapp.R

data class FitTips(
    val day : Int,
    @DrawableRes val imageRes : Int,
    @StringRes val title : Int,
    @StringRes val description : Int,
)


object FitsTipsRepository{
    val tips = mutableListOf(
        FitTips(1, R.drawable.one,R.string.title1,R.string.description1),
        FitTips(2, R.drawable.two,R.string.title2,R.string.description2),
        FitTips(3, R.drawable.three,R.string.title3,R.string.description3),
        FitTips(4, R.drawable.four,R.string.title4,R.string.description4),
        FitTips(5, R.drawable.five,R.string.title5,R.string.description5),
        FitTips(6, R.drawable.six,R.string.title6,R.string.description6),
        FitTips(7, R.drawable.seven,R.string.title7,R.string.description7),
        FitTips(8, R.drawable.eight,R.string.title8,R.string.description8),
        FitTips(9, R.drawable.nine,R.string.title9,R.string.description9),
        FitTips(10, R.drawable.ten,R.string.title10,R.string.description10),
    )
}
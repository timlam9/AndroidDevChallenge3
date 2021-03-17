package com.example.androiddevchallenge

import androidx.annotation.DrawableRes

data class SootheItem(@DrawableRes val image: Int, val title: String)

val favorites = listOf(
    SootheItem(R.drawable.short_mantras, "Short mantras"),
    SootheItem(R.drawable.stress_and_anxiety, "Stress and anxiety"),
    SootheItem(R.drawable.overwhelmed, "Overwhelmed"),
    SootheItem(R.drawable.nature_meditations, "Nature meditations"),
    SootheItem(R.drawable.self_massage, "Self-massage"),
    SootheItem(R.drawable.nightly_wind_down, "Wind down")
)

val bodyList = listOf(
    SootheItem(R.drawable.inversions, "Inversions"),
    SootheItem(R.drawable.quick_yoga, "Quick yoga"),
    SootheItem(R.drawable.stretching, "Stretching"),
    SootheItem(R.drawable.tabata, "Tabata"),
    SootheItem(R.drawable.hiit, "Hiit"),
    SootheItem(R.drawable.pre_natal_yoga, "Pre natal yoga")
)

val mindList = listOf(
    SootheItem(R.drawable.meditate, "Meditate"),
    SootheItem(R.drawable.with_kids, "With kids"),
    SootheItem(R.drawable.aromatherapy, "Aromatherapy"),
    SootheItem(R.drawable.on_the_go, "On the go"),
    SootheItem(R.drawable.with_pets, "With pets"),
    SootheItem(R.drawable.high_stress, "High stress")
)
package com.pessimisticit.scarcitybackend.constants

enum class Color(val hex: String) {
    BRONZE("#6A3805"),
    SILVER("#D7D7D7"),
    GOLD("#AF9500"),

    PERIWINKLE("#C1CBE8"),
    PASTEL_BLUE("#7686CF"),
    PLUMP_PURPLE("#6047B5"),
    DEEP_LILAC("#A458C4"),
    HOT_PINK("#FC60B9"),
    ;
    companion object{
        fun fromItemLevel(itemLevel:Double):Color =
            when(itemLevel){
                in 0.0..20.0 -> PERIWINKLE
                in 20.0..40.0 -> PASTEL_BLUE
                in 40.0..60.0-> PLUMP_PURPLE
                in 60.0..80.0 -> DEEP_LILAC
                else -> HOT_PINK
            }
    }
}


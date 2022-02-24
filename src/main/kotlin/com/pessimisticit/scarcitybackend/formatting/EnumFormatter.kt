package com.pessimisticit.scarcitybackend.formatting

object EnumFormatter {
    fun <T:Enum<*>>toDisplayString(enum: T):String{
        return enum.name.lowercase().replace('_', ' ')
    }
}
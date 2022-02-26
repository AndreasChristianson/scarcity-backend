package com.pessimisticit.scarcitybackend.constants

enum class BindType {
    NEVER_BINDS,
    BIND_ON_USE,
    BIND_ON_PICKUP,
    ;

    fun max(minBindType: BindType?): BindType {
        if (minBindType == null)
            return this
        if (minBindType > this) {
            return minBindType
        }
        return this
    }

}
package com.pessimisticit.scarcitybackend.interfaces

import com.pessimisticit.scarcitybackend.images.GameIcon
import java.net.URI

interface Displayable {
    val name: String
    val description: String
    val icon: GameIcon
    val flavor: String?
        get() = null
}


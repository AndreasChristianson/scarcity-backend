package com.pessimisticit.scarcitybackend.images

import java.net.URI

abstract class GameIcon(
    val uri: URI,
)

class SvgIcon(
    uri: URI,
    val color: String = "lightgray",
) : GameIcon(uri) {
    fun withColor(newColor: String): SvgIcon = SvgIcon(this.uri, newColor)
}

class PngIcon(
    uri: URI,
    val offset: Pair<Int, Int>,
    val size: Pair<Int, Int> = 32 to 32,
) : GameIcon(uri)

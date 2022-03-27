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
    val offset: Position<Int>,
    val size: Position<Int> = Position(32, 32),
) : GameIcon(uri)

data class Position<T>(
    val x: T,
    val y: T,
)
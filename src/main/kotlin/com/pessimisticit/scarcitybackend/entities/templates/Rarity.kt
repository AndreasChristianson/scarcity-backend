package com.pessimisticit.scarcitybackend.entities.templates

enum class Rarity(val relativeWeight: Int) {
    COMMON(1000),
    UNCOMMON(400),
    SCARCE(100),
    RARE(10),
    EXTRAORDINARY(1),
}

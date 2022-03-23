package com.pessimisticit.scarcitybackend.entities.equipment

import com.pessimisticit.scarcitybackend.constants.Color
import com.pessimisticit.scarcitybackend.entities.GameObject
import com.pessimisticit.scarcitybackend.entities.Modifier
import com.pessimisticit.scarcitybackend.images.GameIcon
import com.pessimisticit.scarcitybackend.images.SvgIcon
import java.net.URI
import javax.persistence.Entity

@Entity
abstract class Equipment : GameObject() {
    val weight: Double // in grams
        get() = applyModifiers(baseWeight, Modifier::modifyWeight)
    protected abstract val baseWeight: Double
    val maxDurability: Double
        get() = applyModifiers(baseMaxDurability, Modifier::modifyDurability)
    protected abstract val baseMaxDurability: Double
    abstract val itemLevel:Double
    protected abstract val iconUri:URI

    override val icon:GameIcon
        get() = SvgIcon(iconUri, Color.fromItemLevel(itemLevel).hex)
}

package com.pessimisticit.scarcitybackend.entities.templates.modifiers

import com.pessimisticit.scarcitybackend.objects.Armor
import javax.persistence.Entity

@Entity
abstract class ArmorModifierTemplate : ModifierTemplate<Armor>()

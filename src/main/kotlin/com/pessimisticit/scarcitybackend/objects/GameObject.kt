package com.pessimisticit.scarcitybackend.objects

import com.fasterxml.jackson.annotation.JsonIgnore
import com.pessimisticit.scarcitybackend.constants.Classification
import com.pessimisticit.scarcitybackend.constants.getForModificationLevel
import com.pessimisticit.scarcitybackend.interfaces.Displayable
import java.net.URI


abstract class GameObject : Displayable {
    @JsonIgnore
    var prefix: String? = null

    @JsonIgnore
    var suffix: String? = null

    @JsonIgnore
    var modificationSum: Double = 0.0

    @JsonIgnore
    var modificationMax: Double = 0.0
    val classification: Classification
        get() = getForModificationLevel(modificationSum, modificationMax)
    override var flavor: String? = null

    @JsonIgnore
    lateinit var label: String
    override val name: String
        get() = "${prefix ?: ""} $label ${suffix ?: ""}".trim()
    override lateinit var description: String
    override lateinit var icon: URI

    override fun toString(): String {
        return name
    }
}

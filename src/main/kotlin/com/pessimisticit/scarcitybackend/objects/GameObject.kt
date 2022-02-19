package com.pessimisticit.scarcitybackend.objects

import com.pessimisticit.scarcitybackend.interfaces.Displayable
import java.net.URI


abstract class GameObject : Displayable {
    var prefix: String? = null
    var suffix: String? = null
    var flavor: String? = null
    lateinit var label: String
    override val name: String
        get() = "${prefix ?: ""} $label ${suffix ?: ""}".trim()
    final override lateinit var description: String
    final override lateinit var icon: URI

    override fun toString(): String {
        return name
    }

}

package com.pessimisticit.scarcitybackend.model.composers

import com.pessimisticit.scarcitybackend.model.GameObject
import com.pessimisticit.scarcitybackend.model.Template
import java.net.URL

interface HasTemplate<T:GameObject<T>> {
    val template: Template<T>

    val label: String
        get() = template.label

    val description: String
        get() = template.description

    val icon: URL
        get() = template.icon
}
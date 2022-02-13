package com.pessimisticit.scarcitybackend.configuration.loaders

import com.pessimisticit.scarcitybackend.entities.templates.Template

interface ValueProvider<T> {
    val getValues:Sequence<T>
}

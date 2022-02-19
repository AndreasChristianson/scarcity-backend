package com.pessimisticit.scarcitybackend.configuration.loaders

interface ValueProvider<T> {
    val getValues: Sequence<T>
}

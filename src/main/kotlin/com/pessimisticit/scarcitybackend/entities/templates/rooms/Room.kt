package com.pessimisticit.scarcitybackend.entities.templates.rooms

import com.pessimisticit.scarcitybackend.entities.templates.Template

open class Room<T: Room<T>> : Template<T>() {
}
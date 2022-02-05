package com.pessimisticit.scarcitybackend.entities.changes


import javax.persistence.Entity

@Entity
class Destroyed : Change() {
    override fun getChangeMessage(): String {
        return "$parent destroyed by $source"
    }
}
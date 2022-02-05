package com.pessimisticit.scarcitybackend.entities.changes


import javax.persistence.Entity

@Entity
class Exchanged : Change() {
    override fun getChangeMessage(): String {
        return "$parent parent changed to $source"
    }
}
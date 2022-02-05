package com.pessimisticit.scarcitybackend.entities.changes


import javax.persistence.Entity

@Entity
class ModifierAdded : Change() {
    override fun getChangeMessage(): String {
        return "$parent gained modifier $modifier via $source"
    }
}
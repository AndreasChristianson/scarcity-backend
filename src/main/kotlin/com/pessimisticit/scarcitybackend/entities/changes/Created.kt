package com.pessimisticit.scarcitybackend.entities.changes


import javax.persistence.Entity

@Entity
class Created : Change() {
    override fun getChangeMessage(): String {
        return "$parent created"
    }
}

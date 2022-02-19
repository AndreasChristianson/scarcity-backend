package com.pessimisticit.scarcitybackend.entities

import org.springframework.data.util.ProxyUtils
import java.util.*
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class AbstractJpaPersistable {
    @Id
    open var id: UUID = UUID.randomUUID()

    override fun hashCode(): Int {
        return 31
    }

    override fun toString() = "${this.javaClass.name}: ($id)"
    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (this === other) return true
        if (javaClass != ProxyUtils.getUserClass(other)) return false
        other as AbstractJpaPersistable
        return id == other.id
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
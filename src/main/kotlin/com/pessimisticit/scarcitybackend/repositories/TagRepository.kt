package com.pessimisticit.scarcitybackend.repositories

import com.pessimisticit.scarcitybackend.entities.Tag
import org.springframework.data.repository.CrudRepository
import java.util.*

interface TagRepository : CrudRepository<Tag, UUID>

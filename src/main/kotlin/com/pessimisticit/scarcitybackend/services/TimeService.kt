package com.pessimisticit.scarcitybackend.services

import com.pessimisticit.scarcitybackend.repositories.TimeRepo
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
class TimeService(
    val timeRepo: TimeRepo
) {

    val getGameTime: Long by lazy {
        timeRepo.getGameTime()
    }
}


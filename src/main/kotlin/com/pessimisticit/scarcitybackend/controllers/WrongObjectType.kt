package com.pessimisticit.scarcitybackend.controllers

import java.io.Serial

class WrongObjectType(message: String) : java.lang.RuntimeException(message) {
    companion object {
        @Serial
        private const val serialVersionUID: Long = 8840717288126222419L
    }
}
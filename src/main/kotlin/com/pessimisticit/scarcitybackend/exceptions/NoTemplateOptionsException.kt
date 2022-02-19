package com.pessimisticit.scarcitybackend.exceptions

import java.io.Serial

class NoTemplateOptionsException(
    message: String?
) : RuntimeException(
    message
) {
    companion object {
        @Serial
        private const val serialVersionUID: Long = -4610920613230297768L
    }
}
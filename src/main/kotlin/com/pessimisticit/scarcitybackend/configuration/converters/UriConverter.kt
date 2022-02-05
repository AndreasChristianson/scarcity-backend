package com.pessimisticit.scarcitybackend.configuration.converters

import java.net.URI
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter
class UriConverter : AttributeConverter<URI, String> {
    override fun convertToDatabaseColumn(attribute: URI?): String {
        return attribute?.toString() ?: throw IllegalArgumentException("Unable to convert null URI to string.")
    }

    override fun convertToEntityAttribute(dbData: String?): URI {
        return URI.create(
            dbData ?: throw IllegalArgumentException("Unable to convert null string to URI.")
        )
    }
}



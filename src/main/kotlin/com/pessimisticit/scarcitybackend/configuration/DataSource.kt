package com.pessimisticit.scarcitybackend.configuration;

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.net.URI
import javax.sql.DataSource


@Configuration
class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "app.datasource")
    fun dataSource(@Value("\${app.datasource.url}") connectionString: String): DataSource {
        val dsb = DataSourceBuilder.create() ?: throw Exception("Unable to init data source builder")
        val parsedConnectionString = URI(connectionString)
        val (user, password) = parsedConnectionString.userInfo.split(":")

        val urlString =
            "jdbc:postgresql://${parsedConnectionString.host}:${parsedConnectionString.port}${parsedConnectionString.path}"

        dsb.username(user).password(password).url(urlString)

        return dsb.build()
    }

}
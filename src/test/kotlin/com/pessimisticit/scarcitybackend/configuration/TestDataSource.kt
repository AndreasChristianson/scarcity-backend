package com.pessimisticit.scarcitybackend.configuration

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.datasource.DriverManagerDataSource


@Configuration
class TestDataSource {


		@Bean
		@Primary
		fun embeddedDataSource(): DriverManagerDataSource {
				val driverManagerDataSource = DriverManagerDataSource()
				driverManagerDataSource.setDriverClassName("org.h2.Driver")
				driverManagerDataSource.url = "jdbc:h2:mem:testdb;IGNORECASE=TRUE;DB_CLOSE_ON_EXIT=FALSE;DB_CLOSE_DELAY=-1;MODE=Postgresql"
				return driverManagerDataSource
			}

}

package com.pessimisticit.scarcitybackend.configuration.rest

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.web.PageableHandlerMethodArgumentResolver
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer

@Configuration
class PaginationConfig {
    @Bean
    fun pageableResolverCustomizer(): PageableHandlerMethodArgumentResolverCustomizer? {
        return PageableHandlerMethodArgumentResolverCustomizer { pageableResolver: PageableHandlerMethodArgumentResolver ->
            pageableResolver.setMaxPageSize(
                50
            )
        }
    }
}
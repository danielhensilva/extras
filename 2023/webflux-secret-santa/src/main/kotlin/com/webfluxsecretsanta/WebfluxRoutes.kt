package com.webfluxsecretsanta

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class WebfluxRoutes {

    private var repository: ParticipantsRepository = ParticipantsRepository()
    private var handler: ParticipantsHandler = ParticipantsHandler(repository)

    @Bean
    fun routes() : RouterFunction<ServerResponse> {
        return RouterFunctions.route(RequestPredicates.GET("/")) {
            ServerResponse.ok().body("Hello", String::class.java)
        }
            .andRoute(RequestPredicates.GET("/participants"), handler::getAll)
            .andRoute(RequestPredicates.POST("/participants"), handler::add)
            .andRoute(RequestPredicates.POST("/participants/draw"), handler::draw)
    }
}
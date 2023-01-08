package com.webfluxsecretsanta

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono

@Component
class ParticipantsHandler(
    private val repository: ParticipantsRepository
) {

    fun add(req: ServerRequest): Mono<ServerResponse> =
        req.bodyToMono(Participant::class.java)
            .doOnNext { repository.add(it) }
            .then(ok().build())

    fun getAll(req: ServerRequest): Mono<ServerResponse>  =
        ok().bodyValue(repository.getAll())

    fun draw(req: ServerRequest): Mono<ServerResponse> =
        ok().bodyValue(repository.shuffle().getAll())

}
package com.webfluxsecretsanta

import java.util.UUID

data class Participant(
    val guid: UUID = UUID.randomUUID(),
    val name: String,
)
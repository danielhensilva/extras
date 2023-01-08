package com.webfluxsecretsanta

import org.springframework.stereotype.Component
import java.util.*

@Component
class ParticipantsRepository {
    private val collection: MutableList<Participant> = mutableListOf()

    fun getAll() = collection

    fun add(participant: Participant): ParticipantsRepository {
        collection.add(participant)
        return this
    }

    fun shuffle(): ParticipantsRepository {
        // Implements Fisher-Yates algorithm
        // Could use just collection.shuffled

        val array = collection.toTypedArray()
        val random = Random()

        for (i in (array.size-1) downTo 1) {
            val j = random.nextInt(i)
            val temp = array[i]
            array[i] = array[j]
            array[j] = temp
        }

        collection.clear()
        collection.addAll(array)
        return this
    }
}
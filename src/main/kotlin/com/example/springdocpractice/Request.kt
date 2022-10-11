package com.example.springdocpractice

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes(
    JsonSubTypes.Type(value = InheritanceRequestA::class, name = "A"),
    JsonSubTypes.Type(value = InheritanceRequestB::class, name = "B")
)
interface Request {
    val type: Type
    fun toResponse(): Response
}

data class InheritanceRequestA(
    override val type: Type,
    val somethingA: String
) : Request {
    override fun toResponse(): Response {
        return InheritanceResponseA(1L, type, somethingA)
    }
}

data class InheritanceRequestB(
    override val type: Type,
    val somethingB1: Int,
    val somethingB2: String
) : Request {
    override fun toResponse(): Response {
        return InheritanceResponseB(1L, type, somethingB1, somethingB2)
    }
}

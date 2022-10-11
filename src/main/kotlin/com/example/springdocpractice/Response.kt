package com.example.springdocpractice

interface Response {
    val id: Long
    val type: Type
}

data class InheritanceResponseA(
    override val id: Long,
    override val type: Type,
    val somethingA: String
) : Response

data class InheritanceResponseB(
    override val id: Long,
    override val type: Type,
    val somethingB1: Int,
    val somethingB2: String
) : Response

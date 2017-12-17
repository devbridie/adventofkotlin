package com.devbridie.advent.day16


fun parseDanceMoves(input: String) = input.split(",").map { parseDanceMove(it) }

fun parseDanceMove(input: String): DanceMove {
    val danceId = input.take(1)
    val rest = input.drop(1)
    return when (danceId) {
        "s" -> SpinMove(rest.toInt())
        "x" -> {
            val (first, second) = rest.split("/")
            ExchangeMove(first.toInt(), second.toInt())
        }
        "p" -> {
            val (first, second) = rest.split("/").map { it[0] }
            PartnerMove(first, second)
        }
        else -> throw RuntimeException("Unknown dance ${input[0]}")
    }
}
package com.devbridie.advent.day9

import org.junit.Assert.assertEquals
import org.junit.Test


class Part1Test {
    @Test
    fun `given examples`() {
        listOf(
                "{}" to 1,
                "{{{}}}" to 6,
                "{{},{}}" to 5,
                "{{{},{},{{}}}}" to 16,
                "{<a>,<a>,<a>,<a>}" to 1,
                "{{<ab>},{<ab>},{<ab>},{<ab>}}" to 9,
                "{{<!!>},{<!!>},{<!!>},{<!!>}}" to 9,
                "{{<a!>},{<a!>},{<a!>},{<ab>}}" to 3).forEach {
            assertEquals(it.first, it.second, calculateGroupScore(it.first))
        }
    }
}
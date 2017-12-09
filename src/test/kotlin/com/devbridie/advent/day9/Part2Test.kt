package com.devbridie.advent.day9

import org.junit.Assert.assertEquals
import org.junit.Test


class Part2Test {
    @Test
    fun `given examples`() {
        listOf("<>" to 0,
                "<random characters>" to 17,
                "<<<<>" to 3,
                "<{!>}>" to 2,
                "<!!>" to 0,
                "<!!!>>" to 0,
                "<{o\"i!a,<{i<a>" to 10).forEach {
            assertEquals(it.first, it.second, countGarbage(it.first))
        }
    }
}
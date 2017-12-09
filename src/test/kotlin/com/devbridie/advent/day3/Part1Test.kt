package com.devbridie.advent.day3

import org.junit.Assert.assertEquals
import org.junit.Test


class Part1Test {
    @Test
    fun `test examples`() {
        assertEquals(0, distanceToNumberOnSquare(1))
        assertEquals(3, distanceToNumberOnSquare(12))
        assertEquals(2, distanceToNumberOnSquare(23))
        assertEquals(31, distanceToNumberOnSquare(1024))
    }
}
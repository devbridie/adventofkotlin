package com.devbridie.advent.day10

import org.junit.Assert.assertEquals
import org.junit.Test

class Part2Test {
    @Test
    fun `example hash 1`() {
        assertEquals("a2582a3a0e66e6e86e3812dcb672a272", createHash("".toAsciiList()))
    }

    @Test
    fun `example hash 2`() {
        assertEquals("33efeb34ea91902bb2f59c9920caa6cd", createHash("AoC 2017".toAsciiList()))
    }

    @Test
    fun `example hash 3`() {
        assertEquals("3efbe78a8d82f29979031a4aa0b16a9d", createHash("1,2,3".toAsciiList()))
    }

    @Test
    fun `example hash 4`() {
        assertEquals("63960835bcdc130f0b66d7ff4f6a5a8e", createHash("1,2,4".toAsciiList()))
    }

    @Test
    fun `calculate dense hash`() {
        assertEquals(64, calculateDenseHash(listOf(65, 27, 9, 1, 4, 3, 40, 50, 91, 7, 6, 0, 2, 5, 68, 22)))
    }
}
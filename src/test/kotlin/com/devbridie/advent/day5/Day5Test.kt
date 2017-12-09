package com.devbridie.advent.day5

import org.junit.Assert.assertEquals
import org.junit.Test

class Day5Test {
    val example = mutableListOf(0, 3, 0, 1, -3)

    @Test
    fun `test example part 1`() {
        assertEquals(5, followJumps(example))
    }

    @Test
    fun `test example part 2`() {
        assertEquals(10, followJumps2(example))
    }
}
package com.devbridie.advent.day6

import org.junit.Assert.assertEquals
import org.junit.Test


class Part2Test {
    @Test
    fun `part 1 example`() {
        val banks = listOf(0, 2, 7, 0)
        val states = redistributeBanks2(banks)
        assertEquals(4, findLoopLength(states))
    }
}
package com.devbridie.advent.day6

import org.junit.Assert.assertEquals
import org.junit.Test


class Part1Test {
    @Test
    fun `part 1 example`() {
        val banks = listOf(0, 2, 7, 0)
        assertEquals(5, redistributeBanks(banks).size)
    }

    @Test
    fun `part 1 example sequence`() {
        val banks = listOf(0, 2, 7, 0)
        val sequence = banksSequence(banks).iterator()
        assertEquals(listOf(0, 2, 7, 0), sequence.next())
        assertEquals(listOf(2, 4, 1, 2), sequence.next())
        assertEquals(listOf(3, 1, 2, 3), sequence.next())
        assertEquals(listOf(0, 2, 3, 4), sequence.next())
        assertEquals(listOf(1, 3, 4, 1), sequence.next())
        assertEquals(listOf(2, 4, 1, 2), sequence.next())
    }
}
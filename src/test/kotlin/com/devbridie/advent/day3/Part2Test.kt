package com.devbridie.advent.day3

import org.junit.Assert.assertEquals
import org.junit.Test

class Part2Test {
    @Test
    fun testSequence() {
        val sequence = createPositionSequence().iterator()
        assertEquals(Position(0, 0), sequence.next())
        assertEquals(Position(1, 0), sequence.next())
        assertEquals(Position(1, 1), sequence.next())
        assertEquals(Position(0, 1), sequence.next())
        assertEquals(Position(-1, 1), sequence.next())
        assertEquals(Position(-1, 0), sequence.next())
        assertEquals(Position(-1, -1), sequence.next())
        assertEquals(Position(0, -1), sequence.next())
        assertEquals(Position(1, -1), sequence.next())
        assertEquals(Position(2, -1), sequence.next())
        assertEquals(Position(2, 0), sequence.next())
    }
}
package com.devbridie.advent.day5

import org.junit.Assert.assertEquals
import org.junit.Test

class Part1Test {
    val example = listOf(0, 3, 0, 1, -3)

    @Test
    fun `example answer`() {
        assertEquals(5, jumpsToReachExit(example))
    }

    @Test
    fun `example states`() {
        val states = programStateSequence(example).iterator()
        assertEquals(states.next(), ProgramState(listOf(0, 3, 0, 1, -3), 0))
        assertEquals(states.next(), ProgramState(listOf(1, 3, 0, 1, -3), 0))
        assertEquals(states.next(), ProgramState(listOf(2, 3, 0, 1, -3), 1))
        assertEquals(states.next(), ProgramState(listOf(2, 4, 0, 1, -3), 4))
        assertEquals(states.next(), ProgramState(listOf(2, 4, 0, 1, -2), 1))
        assertEquals(states.next(), ProgramState(listOf(2, 5, 0, 1, -2), 5))
    }
}
package com.devbridie.advent.day5

import org.junit.Assert
import org.junit.Test


class Part2Test {
    val example = listOf(0, 3, 0, 1, -3)

    @Test
    fun `example answer`() {
        Assert.assertEquals(10, jumpsToReachExit2(example))
    }

    @Test
    fun `example final state`() {
        val states = programStateSequence2(example).iterator()
        Assert.assertEquals(states.next(), ProgramState(listOf(0, 3, 0, 1, -3), 0))
        (0 until 9).forEach { println(states.next()) }
        Assert.assertEquals(states.next(), ProgramState(listOf(2, 3, 2, 3, -1), 5))
    }
}
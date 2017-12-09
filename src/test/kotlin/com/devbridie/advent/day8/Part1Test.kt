package com.devbridie.advent.day8

import com.devbridie.advent.fileToLines
import org.junit.Assert.assertEquals
import org.junit.Test

class Part1Test {
    @Test
    fun `example input`() {
        val lines = fileToLines("day8/example")
        val instructions = lines.map { parseInstruction(it) }
        val state = execute(instructions)
        assertEquals(1, getHighestValueInAnyRegister(state))
    }
}
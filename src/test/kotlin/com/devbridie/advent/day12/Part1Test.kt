package com.devbridie.advent.day12

import com.devbridie.advent.fileToLines
import org.junit.Assert.assertEquals
import org.junit.Test

class Part1Test {
    @Test
    fun `example`() {
        val links = linesToLinks(fileToLines("day12/example"))
        assertEquals(6, getAmountOfProgramsInGroup(links))
    }

    @Test
    fun `programs in small cycle`() {
        val links = listOf(Link(0, 1), Link(1, 2), Link(2, 3), Link(3, 1))
        assertEquals(4, getAmountOfProgramsInGroup(links))
    }
}
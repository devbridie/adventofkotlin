package com.devbridie.advent.day12

import com.devbridie.advent.fileToLines
import org.junit.Assert.assertEquals
import org.junit.Test

class Part2Test {
    @Test
    fun `example`() {
        val links = linesToLinks(fileToLines("day12/example"))
        assertEquals(2, getAmountOfGroups(links))
    }
}
package com.devbridie.advent.day19

import com.devbridie.advent.fileToLines
import org.junit.Assert.assertEquals
import org.junit.Test

class Part2Test {
    @Test
    fun `example diagram`() {
        val diagram = parseLines(fileToLines("day19/example"))
        assertEquals(38, getStepsWalked(diagram))
    }
}
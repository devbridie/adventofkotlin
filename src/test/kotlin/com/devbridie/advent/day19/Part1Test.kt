package com.devbridie.advent.day19

import com.devbridie.advent.fileToLines
import org.junit.Assert.assertEquals
import org.junit.Test

class Part1Test {
    @Test
    fun `example diagram`() {
        val diagram = parseLines(fileToLines("day19/example"))
        assertEquals("ABCDEF", lettersEncounteredOnPath(diagram))
    }
}
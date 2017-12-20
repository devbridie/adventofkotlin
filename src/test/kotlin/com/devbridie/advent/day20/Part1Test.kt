package com.devbridie.advent.day20

import com.devbridie.advent.fileToLines
import org.junit.Assert.assertEquals
import org.junit.Test

class Part1Test {
    @Test
    fun `example closest`() {
        val particles = fileToLines("day20/example").map { parseLine(it) }
        assertEquals(0, getClosestParticleIndex(particles))
    }
}

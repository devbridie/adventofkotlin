package com.devbridie.advent.day20

import com.devbridie.advent.fileToLines
import org.junit.Assert.assertEquals
import org.junit.Test

class Part2Test {
    @Test
    fun `example collisions`() {
        val particles = fileToLines("day20/example2").map { parseLine(it) }
        assertEquals(1, survivingParticles(particles,3))
    }
}

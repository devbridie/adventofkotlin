package com.devbridie.advent.day11

import org.junit.Assert.assertEquals
import org.junit.Test

class Part1Test {
    @Test
    fun `example 1`() {
        val directions = "ne,ne,ne".split(",").map { parseDirection(it) }
        assertEquals(3, getDistance(directions))
    }

    @Test
    fun `example 2`() {
        val directions = "ne,ne,sw,sw".split(",").map { parseDirection(it) }
        assertEquals(0, getDistance(directions))
    }

    @Test
    fun `example 3`() {
        val directions = "ne,ne,s,s".split(",").map { parseDirection(it) }
        assertEquals(2, getDistance(directions))
    }

    @Test
    fun `example 4`() {
        val directions = "se,sw,se,sw,sw".split(",").map { parseDirection(it) }
        assertEquals(3, getDistance(directions))
    }

    @Test
    fun `move directions`() {
        val center = Position(1, 1)
        assertEquals(Position(1, 0), center + Direction.NORTH)
        assertEquals(Position(2, 0), center + Direction.NORTHEAST)
        assertEquals(Position(2, 1), center + Direction.SOUTHEAST)
        assertEquals(Position(1, 2), center + Direction.SOUTH)
        assertEquals(Position(0, 2), center + Direction.SOUTHWEST)
        assertEquals(Position(0, 1), center + Direction.NORTHWEST)
    }
}
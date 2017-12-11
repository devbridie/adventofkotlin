package com.devbridie.advent.day10

import org.junit.Assert.assertEquals
import org.junit.Test

class Part1Test {
    @Test
    fun `circular shift`() {
        assertEquals(listOf(2, 3, 4, 1), listOf(1, 2, 3, 4).shiftCircular(1))
        assertEquals(listOf(4, 1, 2, 3), listOf(1, 2, 3, 4).shiftCircular(-1))
    }

    @Test
    fun `modular sublist test`() {
        val list = listOf(0, 1, 2, 3, 4)
        assertEquals(list.subList(0, 5), list.getModularSublist(5, 5))
        assertEquals(listOf(3, 4, 0, 1, 2), list.getModularSublist(3, 8))
    }

    @Test
    fun `example knot step 1`() {
        val step = KnotHashResult(listOf(0, 1, 2, 3, 4), 0, 0)
        val result = knotHashStep(3, step)
        assertEquals(listOf(2, 1, 0, 3, 4), result.list)
        assertEquals(3, result.loc)
        assertEquals(1, result.skipSize)
    }

    @Test
    fun `example knot step 2`() {
        val step = KnotHashResult(listOf(2, 1, 0, 3, 4), 3, 1)
        val result = knotHashStep(4, step)
        assertEquals(listOf(4, 3, 0, 1, 2), result.list)
        assertEquals(8, result.loc)
        assertEquals(2, result.skipSize)
    }

    @Test
    fun `example knot step 3`() {
        val step = KnotHashResult(listOf(4, 3, 0, 1, 2), 8, 2)
        val result = knotHashStep(1, step)
        assertEquals(listOf(4, 3, 0, 1, 2), result.list)
        assertEquals(11, result.loc)
        assertEquals(3, result.skipSize)
    }

    @Test
    fun `example knot step 4`() {
        val step = KnotHashResult(listOf(4, 3, 0, 1, 2), 11, 3)
        val result = knotHashStep(5, step)
        assertEquals(listOf(3, 4, 2, 1, 0), result.list)
        assertEquals(19, result.loc)
        assertEquals(4, result.skipSize)
    }

    @Test
    fun `example end to end`() {
        val result = knotHash((0 until 5).toList(), listOf(3, 4, 1, 5))
        assertEquals(KnotHashResult(listOf(3, 4, 2, 1, 0), 19, 4), result)
    }
}


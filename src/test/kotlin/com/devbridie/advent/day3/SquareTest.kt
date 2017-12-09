package com.devbridie.advent.day3

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**  5th square   *
 * 17 16 15 14 13 *
 * 18          12 *
 * 19          11 *
 * 20        9 10 *
 * 21 22 23 24 25 *
 * See tests for proof
 */
class SquareTest {
    val square = Square(5)

    @Test
    fun lowestNumber() {
        assertEquals(10, square.lowestNumber())
    }

    @Test
    fun highestNumber() {
        assertEquals(25, square.highestNumber())
    }

    @Test
    fun bottomRight() {
        assertEquals(25, square.bottomRight())
    }

    @Test
    fun contains() {
        assertTrue(square.contains(10))
        assertTrue(square.contains(25))
        assertTrue(!square.contains(9))
        assertTrue(!square.contains(26))
    }

    @Test
    fun corners() {
        assertEquals(listOf(13, 17, 21, 25), square.corners().sorted())
    }

    @Test
    fun middles() {
        assertEquals(listOf(11, 15, 19, 23), square.middles().sorted())
    }
}
package com.devbridie.advent.day14

import org.junit.Assert.assertEquals
import org.junit.Test

class Part1Test {
    @Test
    fun `example disk rows and columns`() {
        val subsectioned = createDiskRows("flqrgnkx").take(8).map { it.take(8) }
        val exampleDiskResult = listOf(
                "##.#.#..",
                ".#.#.#.#",
                "....#.#.",
                "#.#.##.#",
                ".##.#...",
                "##..#..#",
                ".#...#..",
                "##.#.##.")
        assertEquals(exampleDiskResult, subsectioned)
    }

    @Test
    fun `example squares used`() {
        assertEquals(8108, squaresUsed("flqrgnkx"))
    }
}
package com.devbridie.advent.day16

import org.junit.Assert.assertEquals
import org.junit.Test

class Part2Test {
    @Test
    fun `example dance`() {
        val moves = parseDanceMoves("s1,x3/4,pe/b")
        val start = "baedc".toList()
        val danceSequence = positionSequence(start, moves.asSequence()).iterator()
        assertEquals("baedc".toCharArray(), danceSequence.next())
        assertEquals("cbaed".toCharArray(), danceSequence.next())
        assertEquals("cbade".toCharArray(), danceSequence.next())
        assertEquals("ceadb".toCharArray(), danceSequence.next())
    }
}
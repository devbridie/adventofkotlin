package com.devbridie.advent.day16

import org.junit.Assert.assertEquals
import org.junit.Test

class Part1Test {
    @Test
    fun `example dance`() {
        val moves = parseDanceMoves("s1,x3/4,pe/b")
        val start = "abcde".toList()
        val danceSequence = positionSequence(start, moves.asSequence()).iterator()
        assertEquals("abcde".toCharArray(), danceSequence.next())
        assertEquals("eabcd".toCharArray(), danceSequence.next())
        assertEquals("eabdc".toCharArray(), danceSequence.next())
        assertEquals("baedc".toCharArray(), danceSequence.next())
    }
}
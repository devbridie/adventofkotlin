package com.devbridie.advent.day15

import org.junit.Assert.assertEquals
import org.junit.Test


class Part1Test {
    @Test
    fun `generator A example`() {
        val generatorA = createGenerator(65, 16807).iterator()
        assertEquals(1092455L, generatorA.next())
        assertEquals(1181022009L, generatorA.next())
        assertEquals(245556042L, generatorA.next())
        assertEquals(1744312007L, generatorA.next())
        assertEquals(1352636452L, generatorA.next())
    }

    @Test
    fun `generator B example`() {
        val generatorB = createGenerator(8921, 48271).iterator()
        assertEquals(430625591L, generatorB.next())
        assertEquals(1233683848L, generatorB.next())
        assertEquals(1431495498L, generatorB.next())
        assertEquals(137874439L, generatorB.next())
        assertEquals(285222916L, generatorB.next())
    }

    @Test
    fun `last 16 bits match example`() {
        assertEquals(false, last16BitsEqual(1092455L to 430625591L))
        assertEquals(false, last16BitsEqual(118102200L to 1233683848L))
        assertEquals(true, last16BitsEqual(245556042L to 1431495498L))
        assertEquals(false, last16BitsEqual(1744312007L to 137874439L))
        assertEquals(false, last16BitsEqual(1352636452L to 285222916L))
    }

    @Test
    fun `example final count`() {
        val generatorA = createGenerator(65, 16807)
        val generatorB = createGenerator(8921, 48271)
        assertEquals(588, judge(generatorA.zip(generatorB), 40_000_000))
    }
}
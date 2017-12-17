package com.devbridie.advent.day15

import org.junit.Assert.assertEquals
import org.junit.Test


class Part2Test {
    @Test
    fun `generator A example`() {
        val generatorA = onlyMultiples(createGenerator(65, 16807), 4).iterator()
        assertEquals(1352636452L, generatorA.next())
        assertEquals(1992081072L, generatorA.next())
        assertEquals(530830436L, generatorA.next())
        assertEquals(1980017072L, generatorA.next())
        assertEquals(740335192L, generatorA.next())
    }

    @Test
    fun `generator B example`() {
        val generatorB = onlyMultiples(createGenerator(8921, 48271), 8).iterator()
        assertEquals(1233683848L, generatorB.next())
        assertEquals(862516352L, generatorB.next())
        assertEquals(1159784568L, generatorB.next())
        assertEquals(1616057672L, generatorB.next())
        assertEquals(412269392L, generatorB.next())
    }

    @Test
    fun `last 16 bits match example`() {
        assertEquals(false, last16BitsEqual(1352636452L to 1233683848L))
        assertEquals(false, last16BitsEqual(1992081072L to 862516352L))
        assertEquals(false, last16BitsEqual(530830436L to 1159784568L))
        assertEquals(false, last16BitsEqual(1980017072L to 1616057672L))
        assertEquals(false, last16BitsEqual(740335192L to 412269392L))
    }

    @Test
    fun `example final count`() {
        val generatorA = onlyMultiples(createGenerator(65, 16807), 4)
        val generatorB = onlyMultiples(createGenerator(8921, 48271), 8)
        assertEquals(309, judge(generatorA.zip(generatorB), 5_000_000))
    }
}
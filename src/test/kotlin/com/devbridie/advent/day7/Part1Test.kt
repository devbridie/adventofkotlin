package com.devbridie.advent.day7

import com.devbridie.advent.fileToLines
import org.junit.Assert.assertEquals
import org.junit.Test

class Part1Test {
    @Test
    fun `bottom example`() {
        val (_, links) = parseLines(fileToLines("day7/example"))
        assertEquals("tknk", findBottomName(links))
    }

    @Test
    fun `all links have the same bottom`() {
        val (_, links) = parseLines(fileToLines("day7/example"))
        val bottom = findBottomName(links)
        val bottoms = links.map { findBottomName(links, it) }
        bottoms.forEach { assertEquals(bottom, it) }
    }
}
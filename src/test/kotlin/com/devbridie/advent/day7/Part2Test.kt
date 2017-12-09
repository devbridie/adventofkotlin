package com.devbridie.advent.day7

import com.devbridie.advent.fileToLines
import org.junit.Assert.assertEquals
import org.junit.Test


class Part2Test {
    @Test
    fun `example input`() {
        val (discs, links) = parseLines(fileToLines("day7/example"))
        assertEquals(60, findSmallestWeightAdjustment(discs, links))
    }

    @Test
    fun `weight of single disc`() {
        val disc = Disc("test", 50)
        assertEquals(50, getWeightOfStack(disc, listOf(disc), listOf(Link("a", "b"))))
    }

    @Test
    fun `weight of stack`() {
        val bottom = Disc("bottom", 50)
        val top = listOf(Disc("top1", 70), Disc("top2", 1), Disc("top3", 99))
        val discs = top + bottom
        val links = top.map { Link(bottom.name, it.name) }

        assertEquals(50 + 70 + 1 + 99, getWeightOfStack(bottom, discs, links))
    }

    @Test
    fun `weight of line`() {
        val bottom = Disc("bottom", 50)
        val discs = listOf(bottom, Disc("disc2", 6), Disc("disc3", 9), Disc("disc4", 98))
        val links = discs.zipWithNext().map { (prev, next) -> Link(prev.name, next.name) }

        assertEquals(50 + 6 + 9 + 98, getWeightOfStack(bottom, discs, links))
    }
}
package com.devbridie.advent.day2

import com.devbridie.advent.fileToLines
import org.junit.Assert.assertEquals
import org.junit.Test


class Part1Test {
    @Test
    fun `test example`() {
        val spreadsheet = parseSpreadsheet(fileToLines("day2/example"))
        assertEquals(18, checksum(spreadsheet))
    }
}
package com.devbridie.advent.day1

import org.junit.Assert.assertEquals
import org.junit.Test

class Part1Test {
    @Test
    fun `test examples`() {
        assertEquals(3, inverseCaptcha("1122"))
        assertEquals(4, inverseCaptcha("1111"))
        assertEquals(0, inverseCaptcha("1234"))
        assertEquals(9, inverseCaptcha("91212129"))
    }
}

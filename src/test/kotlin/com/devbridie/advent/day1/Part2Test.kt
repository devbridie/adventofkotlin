package com.devbridie.advent.day1

import org.junit.Assert.assertEquals
import org.junit.Test

class Part2Test {
    @Test
    fun `test examples`() {
        assertEquals(6, inverseCaptchaCircular("1212"))
        assertEquals(0, inverseCaptchaCircular("1221"))
        assertEquals(4, inverseCaptchaCircular("123425"))
        assertEquals(12, inverseCaptchaCircular("123123"))
        assertEquals(4, inverseCaptchaCircular("12131415"))
    }
}

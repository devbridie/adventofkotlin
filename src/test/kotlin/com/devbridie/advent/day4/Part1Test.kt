package com.devbridie.advent.day4

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class Part1Test {
    @Test
    fun `test valid phrases`() {
        assertTrue(isValidPhrase("aa bb cc dd ee"))
        assertFalse(isValidPhrase("aa bb cc dd aa"))
        assertTrue(isValidPhrase("aa bb cc dd aaa"))
    }
}
package com.devbridie.advent.day4

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class Part2Test {
    @Test
    fun `test valid phrases`() {
        assertTrue(isValidPhraseAnagram("abcde fghij"))
        assertFalse(isValidPhraseAnagram("abcde xyz ecdab"))
        assertTrue(isValidPhraseAnagram("a ab abc abd abf abj"))
        assertTrue(isValidPhraseAnagram("iiii oiii ooii oooi oooo"))
        assertFalse(isValidPhraseAnagram("oiii ioii iioi iiio"))
    }
}
// --- Day 4: High-Entropy Passphrases ---

// A new system policy has been put in place that requires all accounts to use a passphrase instead
// of simply a password. A passphrase consists of a series of words (lowercase letters) separated by
// spaces.

// To ensure security, a valid passphrase must contain no duplicate words.

// The system's full passphrase list is available as your puzzle input.
// How many passphrases are valid?

package com.devbridie.advent.day4

import com.devbridie.advent.fileToLines

fun isValidPhrase(phrase: String): Boolean {
    val words = phrase.split(" ")
    return words.distinct().size == words.size
}

fun main(args: Array<String>) {
    val phrases = fileToLines("day4/input")
    val out = phrases.map { isValidPhrase(it) }.count { it }

    println(out)
}
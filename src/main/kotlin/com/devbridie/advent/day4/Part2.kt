// --- Part Two ---

// For added security, yet another system policy has been put in place. Now, a valid passphrase must
// contain no two words that are anagrams of each other - that is, a passphrase is invalid if any
// word's letters can be rearranged to form any other word in the passphrase.

// Under this new system policy, how many passphrases are valid?

package com.devbridie.advent.day4

import com.devbridie.advent.fileToLines

fun isValidPhraseAnagram(phrase: String): Boolean {
    val words = phrase.split(" ")
    return words.combineSelf().map { (word1, word2) ->
        word1.toCharArray().sorted() == word2.toCharArray().sorted()
    }.none { it }
}

fun main(args: Array<String>) {
    val phrases = fileToLines("day4/input")
    val out = phrases.map { isValidPhraseAnagram(it) }.count { it }

    println(out)
}

fun <T> List<T>.combineSelf(): List<Pair<T, T>> { // match every item with every other item
    return withIndex().flatMap { (index, firstItem) ->
        this.withIndex().filter { it.index != index }.map { it.value }.map { firstItem to it }
    }
}
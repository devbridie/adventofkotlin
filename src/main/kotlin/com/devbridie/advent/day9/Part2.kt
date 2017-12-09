// --- Part Two ---

// Now, you're ready to remove the garbage.

// To prove you've removed it, you need to count all of the characters within the garbage. The
// leading and trailing < and > don't count, nor do any canceled characters or the ! doing the
// canceling.

// How many non-canceled characters are within the garbage in your puzzle input?

package com.devbridie.advent.day9

import com.devbridie.advent.fileToLine


fun main(args: Array<String>) {
    val test = fileToLine("day9/input")
    val score = countGarbage(test)
    println(score)
}

fun countGarbage(string: String): Int {
    val removeIgnored = string.replace(Regex("!."), "")
    val matcher = Regex("<[^>]+>").findAll(removeIgnored)
    val values = matcher.map { it.value }.toList()
    return values.map { it.count() - 2 }.sum()
}
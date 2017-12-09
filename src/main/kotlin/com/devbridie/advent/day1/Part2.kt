// --- Part Two ---

// Now, instead of considering the next digit, it wants you to consider the digit halfway around the
// circular list. That is, if your list contains 10 items, only include a digit in your sum if the
// digit 10/2 = 5 steps forward matches it. Fortunately, your list has an even number of elements.

package com.devbridie.advent.day1

import com.devbridie.advent.fileToLine

fun main(args: Array<String>) {
    val input = fileToLine("day1/input")
    println(inverseCaptchaCircular(input))
}

fun inverseCaptchaCircular(input: String): Int {
    val halfLength = input.length / 2
    val pairs = input.take(halfLength).withIndex().map { (i, value) -> value to input[i + halfLength] }
    val matchingPairs = pairs.filter { (first, second) -> first == second }
    val unpaired = matchingPairs.map { it.first }
    return unpaired.map { it - '0' }.map { it * 2 }.sum()
}
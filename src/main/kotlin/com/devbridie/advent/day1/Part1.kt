// --- Day 1: Inverse Captcha --
// The captcha requires you to review a sequence of digits (your puzzle input) and find the sum of all
// digits that match the next digit in the list. The list is circular, so the digit after the last
// digit is the first digit in the list.

package com.devbridie.advent.day1

import com.devbridie.advent.fileToLine

fun main(args: Array<String>) {
    val input = fileToLine("day1/input")
    println(inverseCaptcha(input))
}

fun inverseCaptcha(input: String): Int {
    val doubled = input + input[0]
    val matchingPairs = doubled.zipWithNext().filter { pair -> pair.first == pair.second }
    val unpaired = matchingPairs.map { pair -> pair.first }
    return unpaired.map { it - '0' }.sum()
}
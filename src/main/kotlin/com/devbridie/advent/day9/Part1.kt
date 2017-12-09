// --- Day 9: Stream Processing ---

// You sit for a while and record part of the stream (your puzzle input). The characters represent
// groups - sequences that begin with { and end with }. Within a group, there are zero or more other
// things, separated by commas: either another group or garbage. Since groups can contain other
// groups, a } only closes the most-recently-opened unclosed group - that is, they are nestable.
// Your puzzle input represents a single, large group which itself contains many smaller ones.

// Sometimes, instead of a group, you will find garbage. Garbage begins with < and ends with >.
// Between those angle brackets, almost any character can appear, including { and }. Within garbage,
// < has no special meaning.

// In a futile attempt to clean up the garbage, some program has canceled some of the characters
// within it using !: inside garbage, any character that comes after ! should be ignored, including
// <, >, and even another !.

// You don't see any characters that deviate from these rules. Outside garbage, you only find
// well-formed groups, and garbage always terminates according to the rules above.

package com.devbridie.advent.day9

import com.devbridie.advent.fileToLine

fun main(args: Array<String>) {
    val test = fileToLine("day9/input")
    val score = calculateGroupScore(test)
    println(score)
}

fun preprocessString(string: String): String {
    return string.replace(Regex("!."), "").replace(Regex("<[^>]+>"), "")
}

fun calculateGroupScore(string: String): Int {
    val preprocessed = preprocessString(string)
    var score = 0
    var counter = 0
    preprocessed.forEach {
        when (it) {
            '{' -> counter++
            '}' -> {score +=counter; counter--; }
            ',' -> {}
        }
    }
    return score
}
// --- Part Two ---

// The logic you've constructed forms a single round of the Knot Hash algorithm; running the full
// thing requires many of these rounds. Some input and output processing is also required.

// First, from now on, your input should be taken not as a list of numbers, but as a string of bytes
// instead. Unless otherwise specified, convert characters to bytes using their ASCII codes.

// Once you have determined the sequence of lengths to use, add the following lengths to the end of
// the sequence: 17, 31, 73, 47, 23.

// Second, instead of merely running one round like you did above, run a total of 64 rounds, using
// the same length sequence in each round. The current position and skip size should be preserved
// between rounds.

// Once the rounds are complete, you will be left with the numbers from 0 to 255 in some order,
// called the sparse hash. Your next task is to reduce these to a list of only 16 numbers called
// the dense hash. To do this, use numeric bitwise XOR to combine each consecutive block of 16 numbers
// in the sparse hash (there are 16 such blocks in a list of 256 numbers).

// Perform this operation on each of the sixteen blocks of sixteen numbers in your sparse hash to
// determine the sixteen numbers in your dense hash.

// Finally, the standard way to represent a Knot Hash is as a single hexadecimal string; the final
// output is the dense hash in hexadecimal notation. Because each number in your dense hash will be
// between 0 and 255 (inclusive), always represent each number as two hexadecimal digits
// (including a leading zero as necessary).

// Treating your puzzle input as a string of ASCII characters, what is the Knot Hash of your puzzle
// input? Ignore any leading or trailing whitespace you might encounter.

package com.devbridie.advent.day10

import com.devbridie.advent.fileToLine

fun main(args: Array<String>) {
    val file = fileToLine("day10/input")
    println(createHash(file.toAsciiList()))
}

fun String.toAsciiList(): List<Int> = this.map { it.toInt() }

fun createHash(asciiValues: List<Int>): String {
    val additionalSequence = listOf(17, 31, 73, 47, 23)
    val lengths = asciiValues + additionalSequence

    val after64Rounds = (0 until 63).fold(knotHash((0 until 256).toList(), lengths), { acc, _ ->
        knotHash(acc.list, lengths, acc.loc, acc.skipSize)
    })

    val sparseHash = after64Rounds.list
    val dense = sparseHash.windowed(16, 16).map { calculateDenseHash(it) }
    val hexaList = dense.map { it.toString(16) }.map { if (it.length == 1) "0" + it else it }
    return hexaList.joinToString(separator = "")
}

fun calculateDenseHash(list: List<Int>) = list.reduce { acc, i -> acc xor i }
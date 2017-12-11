// --- Day 10: Knot Hash ---

// This hash function simulates tying a knot in a circle of string with 256 marks on it. Based on
// the input to be hashed, the function repeatedly selects a span of string, brings the ends
// together, and gives the span a half-twist to reverse the order of the marks within it.
// After doing this many times, the order of the marks is used to build the resulting hash.

// To achieve this, begin with a list of numbers from 0 to 255, a current position which begins at 0
// (the first element in the list), a skip size (which starts at 0), and a sequence of lengths
// (your puzzle input). Then, for each length:

// - Reverse the order of that length of elements in the list, starting with the element at the current position.
// - Move the current position forward by that length plus the skip size.
// - Increase the skip size by one.

// The list is circular; if the current position and the length try to reverse elements beyond the
// end of the list, the operation reverses using as many extra elements as it needs from the front
// of the list. If the current position moves past the end of the list, it wraps around to the front.
// Lengths larger than the size of the list are invalid.

// Once this process is complete, what is the result of multiplying the first two numbers in the list?

package com.devbridie.advent.day10

import com.devbridie.advent.fileToLine

fun main(args: Array<String>) {
    val file = fileToLine("day10/input")
    val lengths = file.split(",").map { it.toInt() }
    val result = knotHash((0 until 256).toList(), lengths)
    val out = result.list
    println(out[0] * out[1])
}

fun knotHashStep(length: Int, step: KnotHashResult): KnotHashResult {
    with(step) {
        val newList = getListWithReversedSection(list, loc, length)
        val newLoc = loc + length + skipSize
        val newSkipSize = skipSize + 1
        return KnotHashResult(newList, newLoc, newSkipSize)
    }
}

data class KnotHashResult(val list: List<Int>, val loc: Int, val skipSize: Int)

fun knotHash(list: List<Int>, lengths: List<Int>, loc: Int = 0, skipSize: Int = 0): KnotHashResult {
    return lengths.fold(KnotHashResult(list, loc, skipSize), { acc, length -> knotHashStep(length, acc) })
}

fun List<Int>.getModularSublist(start: Int, length: Int): List<Int> {
    return this.shiftCircular(start).take(length)
}

fun List<Int>.shiftCircular(amount: Int): List<Int> {
    val modAmount = amount % size
    return when {
        amount < 0 -> subList(size + modAmount, size) + subList(0, size + modAmount)
        amount == 0 -> this
        amount > 0 -> subList(modAmount, size) + subList(0, modAmount)
        else -> throw Exception()
    }
}

fun getListWithReversedSection(list: List<Int>, start: Int, length: Int): List<Int> {
    val shifted = list.shiftCircular(start)

    val reversedPart = shifted.getModularSublist(0, length).asReversed()
    val restLength = shifted.size - length
    val restPart = shifted.getModularSublist(length, restLength)

    val reassembled = reversedPart + restPart
    return reassembled.shiftCircular(-start)
}
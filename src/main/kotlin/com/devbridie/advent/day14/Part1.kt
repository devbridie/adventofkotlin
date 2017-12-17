// --- Day 14: Disk Defragmentation ---

// The disk in question consists of a 128x128 grid; each square of the grid is either free or used.
// On this disk, the state of the grid is tracked by the bits in a sequence of knot hashes.

// A total of 128 knot hashes are calculated, each corresponding to a single row in the grid;
// each hash contains 128 bits which correspond to individual grid squares.
// Each bit of a hash indicates whether that square is free (0) or used (1).

// The hash inputs are a key string (your puzzle input), a dash, and a number from 0 to 127
// corresponding to the row.

// The output of a knot hash is traditionally represented by 32 hexadecimal digits;
// each of these digits correspond to 4 bits, for a total of 4 * 32 = 128 bits.

// Given your actual key string, how many squares are used?


package com.devbridie.advent.day14

import com.devbridie.advent.day10.createHash

fun main(args: Array<String>) {
    println(squaresUsed("wenycdww"))
}

fun createDiskRow(hash: String): String {
    return hash.map { hex -> Integer.parseInt(hex.toString(), 16) }
            .map { it.toString(2) }
            .map { binString -> "0".repeat(4 - binString.length) + binString }
            .map { it.replace("0", ".").replace("1", "#") }
            .joinToString(separator = "")
}

fun createDiskRows(input: String): List<String> {
    val hashInputs = (0 until 128).map { input + "-" + it }
    val hashes = hashInputs.map { inputString -> createHash(inputString.map { it.toInt() }) }
    return hashes.map { hashRow -> println(createDiskRow(hashRow)); createDiskRow(hashRow) }
}

fun squaresUsed(input: String): Int {
    val diskRows = createDiskRows(input)
    return diskRows.map { it.count { it == '#' } }.sum()
}

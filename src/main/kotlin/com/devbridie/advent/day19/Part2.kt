// --- Part Two ---

// The packet is curious how many steps it needs to go.

// How many steps does the packet need to go?

package com.devbridie.advent.day19

import com.devbridie.advent.fileToLines


fun main(args: Array<String>) {
    val diagram = parseLines(fileToLines("day19/input"))
    println(getStepsWalked(diagram))
}

fun getStepsWalked(diagram: Map<Position, Char>): Int {
    return positionSequence(diagram.findStartingPoint(), diagram).count()
}
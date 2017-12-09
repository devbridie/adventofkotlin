// --- Part Two ---

// Now, the jumps are even stranger: after each jump, if the offset was three or more, instead
// decrease it by 1. Otherwise, increase it by 1 as before.

package com.devbridie.advent.day5

import com.devbridie.advent.fileToLines

data class Program2(val jumpLocations: MutableList<Int>) {
    fun isWithinRange(programCounter: Int): Boolean = programCounter < jumpLocations.size

    fun step(programCounter: Int): Int {
        val jumpDelta = jumpLocations[programCounter]
        if (jumpDelta >= 3) {
            jumpLocations[programCounter]--
        } else {
            jumpLocations[programCounter]++
        }

        return programCounter + jumpDelta
    }
}

fun main(args: Array<String>) {
    val jumpOffsets = fileToLines("day5/input").map { it.toInt() }.toMutableList()
    println(followJumps2(jumpOffsets))
}

fun followJumps2(jumpOffsets: MutableList<Int>): Int {
    val program = Program2(jumpOffsets)
    var jumps = 0
    var programCounter = 0
    while (program.isWithinRange(programCounter)) {
        programCounter = program.step(programCounter)
        jumps++
    }
    return jumps
}
// --- Day 5: A Maze of Twisty Trampolines, All Alike ---

// An urgent interrupt arrives from the CPU: it's trapped in a maze of jump instructions, and it
// would like assistance from any programs with spare cycles to help find the exit.

// The message includes a list of the offsets for each jump. Jumps are relative: -1 moves to the
// previous instruction, and 2 skips the next one. Start at the first instruction in the list.
// The goal is to follow the jumps until one leads outside the list.

// In addition, these instructions are a little strange; after each jump, the offset of that
// instruction increases by 1. So, if you come across an offset of 3, you would move three
// instructions forward, but change it to a 4 for the next time it is encountered.

// How many steps does it take to reach the exit?

package com.devbridie.advent.day5

import com.devbridie.advent.fileToLines

data class Program(val jumpLocations: MutableList<Int>) {
    fun isWithinRange(programCounter: Int): Boolean = programCounter < jumpLocations.size

    fun step(programCounter: Int): Int {
        val jumpDelta = jumpLocations[programCounter]
        jumpLocations[programCounter]++

        return programCounter + jumpDelta
    }
}

fun main(args: Array<String>) {
    val jumpOffsets = fileToLines("day5/input").map { it.toInt() }.toMutableList()
    println(followJumps(jumpOffsets))
}

fun followJumps(jumpOffsets: MutableList<Int>): Int {
    val program = Program(jumpOffsets)
    var jumps = 0
    var programCounter = 0
    while (program.isWithinRange(programCounter)) {
        programCounter = program.step(programCounter)
        jumps++
    }
    return jumps
}
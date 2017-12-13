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

fun main(args: Array<String>) {
    val jumpOffsets = fileToLines("day5/input").map { it.toInt() }
    println(jumpsToReachExit(jumpOffsets))
}

data class ProgramState(val jumpLocations: List<Int>, val programCounter: Int)

fun programStateSequence(initialJumpLocations: List<Int>): Sequence<ProgramState> {
    return generateSequence(ProgramState(initialJumpLocations, 0)) { (jumpLocations, programCounter) ->
        if (programCounter >= jumpLocations.size) return@generateSequence null
        val jumpDelta = jumpLocations[programCounter]

        val newJumpLocations = jumpLocations.mapIndexed { index, jump ->
            if (index == programCounter) jump + 1 else jump
        }
        val newProgramCounter = programCounter + jumpDelta
        ProgramState(newJumpLocations, newProgramCounter)
    }
}

fun jumpsToReachExit(jumpOffsets: List<Int>): Int = programStateSequence(jumpOffsets).count() - 1
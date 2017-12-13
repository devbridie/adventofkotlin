// --- Part Two ---

// Now, the jumps are even stranger: after each jump, if the offset was three or more, instead
// decrease it by 1. Otherwise, increase it by 1 as before.

package com.devbridie.advent.day5

import com.devbridie.advent.fileToLines

fun main(args: Array<String>) {
    val jumpOffsets = fileToLines("day5/input").map { it.toInt() }
    println(jumpsToReachExit2(jumpOffsets))
}

fun programStateSequence2(initialJumpLocations: List<Int>): Sequence<ProgramState> {
    return generateSequence(ProgramState(initialJumpLocations, 0)) { (jumpLocations, programCounter) ->
        if (programCounter >= jumpLocations.size) return@generateSequence null
        val jumpDelta = jumpLocations[programCounter]

        val newJumpLocations = jumpLocations.toMutableList() // mapIndexed and update here is very slow
        if (jumpDelta >= 3) newJumpLocations[programCounter]--
        else newJumpLocations[programCounter]++

        val newProgramCounter = programCounter + jumpDelta
        ProgramState(newJumpLocations, newProgramCounter)
    }
}

fun jumpsToReachExit2(jumpOffsets: List<Int>): Int = programStateSequence2(jumpOffsets).count() - 1
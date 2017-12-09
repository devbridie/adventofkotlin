// --- Day 8: I Heard You Like Registers ---

// You receive a signal directly from the CPU. Because of your recent assistance with jump
// instructions, it would like you to compute the result of a series of unusual register
// instructions.

// Each instruction consists of several parts: the register to modify, whether to increase or
// decrease that register's value, the amount by which to increase or decrease it, and a condition.
// If the condition fails, skip the instruction without modifying the register. The registers all
// start at 0.

// What is the largest value in any register after completing the instructions in your puzzle input?

package com.devbridie.advent.day8

import com.devbridie.advent.fileToLines

fun main(args: Array<String>) {
    val lines = fileToLines("day8/input")
    val instructions = lines.map { parseInstruction(it) }
    val state = execute(instructions)
    println(getHighestValueInAnyRegister(state))
}

fun getHighestValueInAnyRegister(programState: ProgramState): Int {
    return programState.map { it.value }.max()!!
}
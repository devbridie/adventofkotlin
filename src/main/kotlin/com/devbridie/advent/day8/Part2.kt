// --- Part Two ---

// To be safe, the CPU also needs to know the highest value held in any register during this process
// so that it can decide how much memory to allocate to these operations. For example, in the above
// instructions, the highest value ever held was 10 (in register c after the third instruction was
// evaluated).

package com.devbridie.advent.day8

import com.devbridie.advent.fileToLines

fun main(args: Array<String>) {
    val lines = fileToLines("day8/input")
    val instructions = lines.map { parseInstruction(it) }
    val history = executeWithHistory(instructions)
    println(getHighestValueSeen(history))
}

fun getHighestValueSeen(history: List<ProgramState>) = history.filter { it.isNotEmpty() }.flatMap { it.values }.max()!!
// --- Part Two ---

// Out of curiosity, the debugger would also like to know the size of the loop: starting from a
// state that has already been seen, how many block redistribution cycles must be performed before
// that same state is seen again?

// How many cycles are in the infinite loop that arises from the configuration in your puzzle input?

package com.devbridie.advent.day6

import com.devbridie.advent.fileToLine

fun main(args: Array<String>) {
    val banks = fileToLine("day6/input").split("\t").map { it.toInt() }
    val states = redistributeBanks2(banks)
    println(findLoopLength(states))
}

fun redistributeBanks2(startBanks: List<Int>): List<List<Int>> {
    val states = mutableListOf<List<Int>>()
    fun containsTwice(banks: List<Int>): Boolean = states.filter { it == banks }.size == 2
    banksSequence(startBanks).takeWhile { !containsTwice(it) }.forEach { states.add(it) }
    return states
}

fun findLoopLength(history: List<List<Int>>): Int {
    return (history.size - 1) - history.indexOfFirst { it == history.last() }
}

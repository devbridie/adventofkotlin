// --- Day 6: Memory Reallocation ---

// In this area, there are sixteen memory banks; each memory bank can hold any number of blocks.
// The goal of the reallocation routine is to balance the blocks between the memory banks.

// The reallocation routine operates in cycles. In each cycle, it finds the memory bank with the
// most blocks (ties won by the lowest-numbered memory bank) and redistributes those blocks among
// the banks. To do this, it removes all of the blocks from the selected bank, then moves to the
// next (by index) memory bank and inserts one of the blocks. It continues doing this until it runs
// out of blocks; if it reaches the last memory bank, it wraps around to the first one.

// Given the initial block counts in your puzzle input, how many redistribution cycles must be completed before a configuration is produced that has been seen before?

package com.devbridie.advent.day6

import com.devbridie.advent.fileToLine

fun main(args: Array<String>) {
    val banks = fileToLine("day6/input").split("\t").map { it.toInt() }
    println(redistributeBanks(banks).size)
}

fun redistributeBanks(startBanks: List<Int>): List<List<Int>> {
    val states = mutableListOf<List<Int>>()
    banksSequence(startBanks).takeWhile { !states.contains(it) }.forEach { states.add(it) }
    return states
}

fun banksSequence(startBanks: List<Int>): Sequence<List<Int>> {
    return generateSequence(startBanks, { previous ->
        val (highestIndex, highestValue) = previous.withIndex().maxBy { it.value }!!
        val mutable = previous.toMutableList()
        mutable[highestIndex] = 0
        val registersToIncrement = (1..highestValue).map { (highestIndex + it) % previous.size }
        registersToIncrement.fold(mutable, { _, slot -> mutable.apply { mutable[slot] += 1 } })
    })
}
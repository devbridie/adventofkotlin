// --- Part Two ---

// It sounds like the goal is to find the only two numbers in each row where one evenly divides the
// other - that is, where the result of the division operation is a whole number. They would like
// you to find those numbers on each line, divide them, and add up each line's result.

package com.devbridie.advent.day2

import com.devbridie.advent.fileToLines

fun main(args: Array<String>) {
    val input = fileToLines("day2/input")
    val spreadsheet = parseSpreadsheet(input)
    println(dividingChecksum(spreadsheet))
}

fun dividingChecksum(spreadsheet: Spreadsheet): Int {
    return spreadsheet.map { row ->
        row.combineSelf()
                .filter { (a, b) -> a != b }
                .filter { (a, b) -> a % b == 0 }
                .map { (a, b) -> a / b }
                .first()
    }.sum()
}

fun List<Int>.combineSelf(): List<Pair<Int, Int>> { // match every item with every other item
    return withIndex().flatMap { (index, firstItem) ->
        this.withIndex().filter { it.index != index }.map { it.value }.map { firstItem to it }
    }
}
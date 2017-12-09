// The spreadsheet consists of rows of apparently-random numbers. To make sure the recovery process
// is on the right track, they need you to calculate the spreadsheet's checksum. For each row,
// determine the difference between the largest value and the smallest value; the checksum is the
// sum of all of these differences.


package com.devbridie.advent.day2

import com.devbridie.advent.fileToLines

typealias Spreadsheet = List<List<Int>>
fun parseSpreadsheet(lines: List<String>): Spreadsheet = lines.map { it.split(Regex("[ \t]")).map { it.toInt() } }

fun main(args: Array<String>) {
    val input = fileToLines("day2/input")
    val spreadsheet = parseSpreadsheet(input)
    println(checksum(spreadsheet))
}

fun checksum(spreadsheet: Spreadsheet): Int {
    return spreadsheet.map { row -> row.min()!! to row.max()!! }.map { (min, max) -> max - min }.sum()
}
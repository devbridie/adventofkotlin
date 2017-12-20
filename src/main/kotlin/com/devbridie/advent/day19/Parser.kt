package com.devbridie.advent.day19

data class Position(val x: Int, val y: Int)

fun parseLines(lines: List<String>): Map<Position, Char> {
    return lines.mapIndexed { y, line -> line.mapIndexed { x, char -> Position(x, y) to char } }.flatMap { it }
            .filter { it.second != ' ' }.toMap()
}
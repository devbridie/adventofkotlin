// --- Part Two ---

// How many steps away is the furthest he ever got from his starting depth?

package com.devbridie.advent.day11

import com.devbridie.advent.fileToLine

fun main(args: Array<String>) {
    val line = fileToLine("day11/input")
    val directions = line.split(",").map { parseDirection(it) }
    println(getMaximumDistance(directions))
}

fun getMaximumDistance(directionList: List<Direction>): Int {
    val origin = Position(0,0)
    val initial = listOf(WalkHistoryItem(origin, 0))
    val history = directionList.fold(initial, { acc, direction ->
        val newPosition = acc.last().position + direction
        acc + WalkHistoryItem(newPosition, origin.distance(newPosition))
    })
    return history.map { it.distance }.max()!!
}

data class WalkHistoryItem(val position: Position, val distance: Int)
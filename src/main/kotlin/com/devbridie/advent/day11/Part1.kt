// --- Day 11: Hex Ed ---

// The hexagons ("hexes") in this grid are aligned such that adjacent hexes can be found to the
// north, northeast, southeast, south, southwest, and northwest:

// You have the path the child process took. Starting where he started, you need to determine
// the fewest number of steps required to reach him.
// (A "step" means to move from the hex you are in to any adjacent hex.)

package com.devbridie.advent.day11

import com.devbridie.advent.fileToLine

fun main(args: Array<String>) {
    val line = fileToLine("day11/input")
    val directions = line.split(",").map { parseDirection(it) }
    println(getDistance(directions))
}

fun getDistance(directionList: List<Direction>): Int {
    val origin = Position(0,0)
    return origin.distance(origin + directionList)
}

fun parseDirection(string: String) = when (string) {
    "n" -> Direction.NORTH
    "ne" -> Direction.NORTHEAST
    "se" -> Direction.SOUTHEAST
    "s" -> Direction.SOUTH
    "sw" -> Direction.SOUTHWEST
    "nw" -> Direction.NORTHWEST
    else -> throw Exception("Unrecognized direction $string")
}

enum class Direction {
    NORTH, NORTHEAST, SOUTHEAST, SOUTH, SOUTHWEST, NORTHWEST
}

// Hexagon reference: https://www.redblobgames.com/grids/hexagons/
data class Position(val x: Int, val y: Int) {
    operator fun plus(direction: Direction) = when (direction) {
        Direction.NORTH -> Position(x, y - 1)
        Direction.NORTHEAST -> Position(x + 1, y - 1)
        Direction.SOUTHEAST -> Position(x + 1, y)
        Direction.SOUTH -> Position(x, y + 1)
        Direction.SOUTHWEST -> Position(x - 1, y + 1)
        Direction.NORTHWEST -> Position(x - 1, y)
    }

    operator fun plus(directionList: List<Direction>) = directionList.fold(this, { acc, direction -> acc + direction })

    fun distance(other: Position): Int = (Math.abs(this.x - other.x) + Math.abs(this.x + this.y - other.x - other.y) + Math.abs(this.y - other.y)) / 2
}
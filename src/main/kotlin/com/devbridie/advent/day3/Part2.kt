// --- Part Two ---

// As a stress test on the system, the programs here clear the grid and then store the value 1 in
// square 1. Then, in the same allocation order as shown above, they store the sum of the values in
// all adjacent squares, including diagonals.

// What is the first value written that is larger than your puzzle input?

package com.devbridie.advent.day3

data class Delta(val x: Int, val y: Int)

data class Position(val x: Int, val y: Int) {
    fun positionIn(direction: Direction) = this + direction.toDelta()

    operator fun plus(delta: Delta): Position = Position(x + delta.x, y + delta.y)
}

fun createPositionSequence(): Sequence<Position> {
    val visited = mutableListOf<Position>()
    return generateSequence(Position(0, 0) to Direction.EAST, { previous ->
        val (previousPosition, previousDirection) = previous
        visited.add(previousPosition)
        val newPosition = previousPosition.positionIn(previousDirection)
        val left = previousDirection.counterClockwise()
        val positionToLeft = newPosition.positionIn(left)
        val newDirection = if (!visited.contains(positionToLeft)) left else previousDirection
        newPosition to newDirection
    }).map { it.first }
}

enum class Direction {
    NORTH, WEST, SOUTH, EAST;

    fun counterClockwise(): Direction = Direction.values()[(ordinal + 1) % Direction.values().size]
}

fun Direction.toDelta(): Delta = when (this) {
    Direction.NORTH -> Delta(0, 1)
    Direction.WEST -> Delta(-1, 0)
    Direction.SOUTH -> Delta(0, -1)
    Direction.EAST -> Delta(1, 0)
}

class Grid(val map: HashMap<Position, Int> = hashMapOf()) : Map<Position, Int> by map {
    operator fun set(position: Position, value: Int) {
        map.put(position, value)
    }

    operator fun set(x: Int, y: Int, value: Int) {
        map.put(Position(x, y), value)
    }

    override operator fun get(key: Position) = map[key] ?: 0
}

fun main(args: Array<String>) {
    val input = 325489

    val grid = Grid()
    grid[0, 0] = 1
    val sequence = createPositionSequence().drop(1)
    val adjacentSums = sequence.map {
        val sum = getAdjacentPositions(it).map { grid[it] }.sum()
        grid[it] = if (sum == 0) 1 else sum
        return@map sum
    }
    println(adjacentSums.find { it > input })
}

fun List<Int>.combineAll(): List<Pair<Int, Int>> { // match every item with every other item
    return flatMap { firstItem -> this.map { firstItem to it } }
}

fun getAdjacentPositions(position: Position) = listOf(-1, 0, 1).combineAll().map { position + Delta(it.first, it.second) }
// --- Day 3: Spiral Memory ---

// You come across an experimental new kind of memory stored on an infinite two-dimensional grid.

// Each square on the grid is allocated in a spiral pattern starting at a location marked 1 and
// then counting up while spiraling outward.

// While this is very space-efficient (no squares are skipped), requested data must be carried back
// to square 1 (the location of the only access port for this memory system) by programs that can
// only move up, down, left, or right. They always take the shortest path: the Manhattan Distance
// between the location of the data and square 1.

// How many steps are required to carry the data from the square identified in your puzzle input all
// the way to the access port?

package com.devbridie.advent.day3

/**  5th square   *
 * 17 16 15 14 13 *
 * 18          12 *
 * 19          11 *
 * 20        9 10 *
 * 21 22 23 24 25 *
 */

class Square(val side: Int) {
    fun lowestNumber() = Square(side - 2).highestNumber() + 1

    fun highestNumber() = side * side

    fun bottomRight() = highestNumber()

    fun contains(element: Int) = lowestNumber() <= element && element <= highestNumber()

    fun corners(): List<Int> = (0..3).map { bottomRight() - (side - 1) * it }

    fun middles() = corners().map { it - side / 2 }
}

fun generateSquares() = generateSequence(1, { it + 2 }).map { Square(it) }


fun distanceToNumberOnSquare(number: Int): Int {
    if (number == 1) return 0
    val square = generateSquares().find { it.contains(number) }!!
    val smallestDistanceFromMiddle = square.middles().map { Math.abs(number - it) }.min()!!
    val distanceToMiddle = square.side / 2
    return smallestDistanceFromMiddle + distanceToMiddle
}

fun main(args: Array<String>) {
    println(distanceToNumberOnSquare(325489))
}
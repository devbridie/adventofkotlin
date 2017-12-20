// --- Day 19: A Series of Tubes ---

// Somehow, a network packet got lost and ended up here.
// It's trying to follow a routing diagram (your puzzle input), but it's confused about where to go.

// Its starting point is just off the top of the diagram.
// Lines (drawn with |, -, and +) show the path it needs to take, starting by going down onto the
// only line connected to the top of the diagram. It needs to follow this path until it reaches the
// end (located somewhere within the diagram) and stop there.

// Sometimes, the lines cross over each other; in these cases, it needs to continue going the same
// direction, and only turn left or right when there's no other option. In addition, someone has left
// letters on the line; these also don't change its direction, but it can use them to keep track of
// where it's been.

// The little packet looks up at you, hoping you can help it find the way. What letters will it see
// (in the order it would see them) if it follows the path?

package com.devbridie.advent.day19

import com.devbridie.advent.day3.Delta
import com.devbridie.advent.fileToLines


fun main(args: Array<String>) {
    val diagram = parseLines(fileToLines("day19/input"))
    println(lettersEncounteredOnPath(diagram))
}

fun lettersEncounteredOnPath(diagram: Map<Position, Char>): String {
    val start = diagram.findStartingPoint()
    val seq = positionSequence(start, diagram)
    return seq.map { it.second }.filter { it.isLetter() }.joinToString(separator = "")
}

fun Map<Position, Char>.findStartingPoint() = this.filter { (position, value) -> position.y == 0 && value == '|' }.entries.first().key

fun positionSequence(start: Position, diagram: Map<Position, Char>): Sequence<Pair<Position, Char>> {
    return generateSequence(start to Direction.SOUTH, { (previousPosition, previousDirection) ->
        val walkNextPosition = previousPosition.positionIn(previousDirection)
        if (diagram.containsKey(walkNextPosition)) walkNextPosition to previousDirection
        else {
            val possibleTurns = Direction.values().filter { it != previousDirection.opposite() }.map { previousPosition.positionIn(it) to it }.filter { (p, _) -> diagram.containsKey(p) }
            if (possibleTurns.isEmpty()) null
            else possibleTurns.first { diagram.containsKey(it.first) }
        }
    }).map { (position, _) -> position to diagram[position]!! }
}

enum class Direction {
    NORTH, EAST, SOUTH, WEST
}

fun Direction.toDelta(): Delta = when (this) {
    Direction.NORTH -> Delta(0, -1)
    Direction.WEST -> Delta(-1, 0)
    Direction.SOUTH -> Delta(0, 1)
    Direction.EAST -> Delta(1, 0)
}

fun Direction.opposite(): Direction = when (this) {
    Direction.NORTH -> Direction.SOUTH
    Direction.WEST -> Direction.EAST
    Direction.SOUTH -> Direction.NORTH
    Direction.EAST -> Direction.WEST
}

fun Position.positionIn(direction: Direction) = this + direction.toDelta()
operator fun Position.plus(delta: Delta): Position = Position(x + delta.x, y + delta.y)
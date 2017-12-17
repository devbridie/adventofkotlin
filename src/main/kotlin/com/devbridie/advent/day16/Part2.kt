// --- Part Two ---

// Now that you're starting to get a feel for the dance moves, you turn your attention to the dance as a whole.

// Keeping the positions they ended up in from their previous dance, the programs perform it again and again:
// including the first dance, a total of one billion (1000000000) times.

// In what order are the programs standing after their billion dances?

package com.devbridie.advent.day16

import com.devbridie.advent.fileToLine


fun main(args: Array<String>) {
    val moves = parseDanceMoves(fileToLine("day16/input"))
    println(danceOneBillionTimes(moves, initialPositions()).joinToString(separator = ""))
}

fun danceOneBillionTimes(moves: List<DanceMove>, positions: List<Char>): List<Char> {
    // We cannot naively calculate this answer:
    // There are 10000 moves in the input, dancing a billion times results in 1e+13 moves executed.
    // If you did one dance move in 1 ns, this would still take 7 days to execute.
    // Therefore, we will find a cycle with p = 10000 * 1000000000 / 16! = 0.5.
    // Then we will subtract full cycles from 10000 * 1000000000 to find the answer.
    val cycleLength = findCycleLength(moves, positions)

    val test = positionSequence(positions, infiniteDanceMoveSequence(moves))
    val totalMoves = 1000000000L * moves.size.toLong()
    val remainder = totalMoves % cycleLength.toLong()
    return test.elementAt(remainder.toInt())
}

fun findCycleLength(moves: List<DanceMove>, initial: List<Char>): Int {
    val cycleStates = positionSequence(initial, infiniteDanceMoveSequence(moves)).drop(1).takeWhile { it != initial }
    return cycleStates.count() + 1
}

fun infiniteDanceMoveSequence(moves: List<DanceMove>): Sequence<DanceMove> {
    var i = 0
    return generateSequence {
        moves[i % moves.size].also { i++ }
    }
}


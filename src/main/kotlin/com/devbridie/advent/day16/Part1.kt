// --- Day 16: Permutation Promenade ---

// You come upon a very unusual sight; a group of programs here appear to be dancing.

// There are sixteen programs in total, named a through p.
// They start by standing in a line: a stands in position 0, b stands in position 1,
// and so on until p, which stands in position 15.

// The programs' dance consists of a sequence of dance moves:

// - Spin, written sX, makes X programs move from the end to the front, but maintain their order otherwise.
// - Exchange, written xA/B, makes the programs at positions A and B swap places.
// - Partner, written pA/B, makes the programs named A and B swap places.

// You watch the dance for a while and record their dance moves (your puzzle input).
// In what order are the programs standing after their dance?

package com.devbridie.advent.day16

import com.devbridie.advent.fileToLine

sealed class DanceMove
data class SpinMove(val length: Int) : DanceMove()
data class ExchangeMove(val firstPosition: Int, val secondPosition: Int) : DanceMove()
data class PartnerMove(val firstProgram: Char, val secondProgram: Char) : DanceMove()

fun main(args: Array<String>) {
    val moves = parseDanceMoves(fileToLine("day16/input"))
    println(getLastPosition(initialPositions(), moves).joinToString(separator = ""))
}

fun getLastPosition(positions: List<Char>, moves: List<DanceMove>): List<Char> {
    return positionSequence(positions, moves.asSequence()).last()
}

fun positionSequence(positions: List<Char>, moves: Sequence<DanceMove>): Sequence<List<Char>> {
    val moveIterator = moves.iterator()
    return generateSequence(positions) { previous ->
        if (moveIterator.hasNext()) executeDanceMove(previous, moveIterator.next())
        else null
    }
}

fun initialPositions() = ('a'..'p').toList()

fun executeDanceMove(programs: List<Char>, move: DanceMove): List<Char> = when (move) {
    is SpinMove -> programs.takeLast(move.length) + programs.dropLast(move.length)
    is ExchangeMove -> programs.toMutableList().apply {
        this[move.secondPosition] = programs[move.firstPosition]
        this[move.firstPosition] = programs[move.secondPosition]
    }

    is PartnerMove -> programs.toMutableList().apply {
        this[programs.indexOf(move.firstProgram)] = move.secondProgram
        this[programs.indexOf(move.secondProgram)] = move.firstProgram
    }
}

// --- Day 12: Digital Plumber ---

// Walking along the memory banks of the stream, you find a small village that is experiencing
// a little confusion: some programs can't communicate with each other.

// Programs in this village communicate using a fixed system of pipes.
// Messages are passed between programs using these pipes, but most programs aren't connected to each
// other directly. Instead, programs pass messages between each other until the message
// reaches the intended recipient.

// For some reason, though, some of these messages aren't ever reaching their intended recipient,
// and the programs suspect that some pipes are missing. They would like you to investigate.

// You walk through the village and record the ID of each program and the IDs with which it can
// communicate directly (your puzzle input). Each program has one or more programs with which it
// can communicate, and these pipes are bidirectional; if 8 says it can communicate with 11, then
// 11 will say it can communicate with 8.

// You need to figure out how many programs are in the group that contains program ID 0.

// How many programs are in the group that contains program ID 0?

package com.devbridie.advent.day12

import com.devbridie.advent.fileToLines


fun main(args: Array<String>) {
    val links = linesToLinks(fileToLines("day12/input"))
    println(getAmountOfProgramsInGroup(links))
}

data class Link(val side1: Int, val side2: Int)

fun linesToLinks(lines: List<String>) = lines.flatMap { lineToLinks(it) }

fun lineToLinks(line: String): List<Link> {
    val (from, to) = line.split(" <-> ")
    val fromInt = from.toInt()
    return to.split(", ").map { Link(fromInt, it.toInt()) }
}

fun getAmountOfProgramsInGroup(links: List<Link>, start: Int = 0): Int {
    return discoverProgramsInGroup(links, start).distinct().size
}

fun List<Link>.findWithProgram(program: Int) = filter { it.side1 == program }.map { it.side2 } + filter { it.side2 == program }.map { it.side1 }

fun discoverProgramsInGroup(links: List<Link>, start: Int, visited: List<Int> = listOf()): List<Int> {
    val newPrograms = links.findWithProgram(start).filter { !visited.contains(it) }
    return newPrograms.fold(visited, { visitedAcc, newNode -> discoverProgramsInGroup(links, newNode, visitedAcc + newNode) })
}
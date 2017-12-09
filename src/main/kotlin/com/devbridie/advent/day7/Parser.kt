package com.devbridie.advent.day7

fun parseLine(line: String): Pair<Disc, List<Link>> {
    val (discName, unparsedWeight) = line.split(" ")
    val weight = unparsedWeight.drop(1).dropLast(1).toInt()
    val disc = Disc(discName, weight)

    val linkSplit = line.split(" -> ")
    val links = if (linkSplit.size == 1) listOf() else linkSplit[1].split(", ").map { Link(discName, it) }

    return disc to links
}

fun parseLines(lines: List<String>): Pair<List<Disc>, List<Link>> {
    val parsedLines = lines.map { parseLine(it) }
    return parsedLines.map { it.first } to parsedLines.flatMap { it.second }
}
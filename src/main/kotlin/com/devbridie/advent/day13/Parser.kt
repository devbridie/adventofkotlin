package com.devbridie.advent.day13

data class Line(val depth: Int, val range: Int)

fun parseLine(line: String): Line {
    val (depth, range) = line.split(": ")
    return Line(depth.toInt(), range.toInt())
}

fun linesToLayers(lines: List<Line>): List<Layer> {
    val maxDepth = lines.map { it.depth }.max()!!
    return (0..maxDepth).map { layer ->
        val line = lines.find { it.depth == layer }
        if (line == null) EmptyLayer else FirewallLayer(line.range)
    }
}
package com.devbridie.advent.day20

data class Vector3(val x: Int, val y: Int, val z: Int) {
    override fun toString(): String {
        return "($x, $y, $z)"
    }
}
data class Particle(val position: Vector3, val velocity: Vector3, val acceleration: Vector3)

fun parseLine(line: String): Particle {
    val (p, v, a) = line.split(", ").map { it.drop(2) }.map { parseVector(it) }
    return Particle(p, v, a)
}

fun parseVector(vector: String): Vector3 {
    val (x, y, z) = vector.drop(1).dropLast(1).split(",").map { it.toInt() }
    return Vector3(x, y, z)
}
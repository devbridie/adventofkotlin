// --- Day 20: Particle Swarm ---

// Suddenly, the GPU contacts you, asking for help. Someone has asked it to simulate too many
// particles, and it won't be able to finish them all in time to render the next frame at this rate.

// It transmits to you a buffer (your puzzle input) listing each particle in order
// (starting with particle 0, then particle 1, particle 2, and so on). For each particle,
// it provides the X, Y, and Z coordinates for the particle's position (p), velocity (v), and
// acceleration (a), each in the format <X,Y,Z>.

// Because of seemingly tenuous rationale involving z-buffering, the GPU would like to know which
// particle will stay closest to position <0,0,0> in the long term. Measure this using the Manhattan distance,
// which in this situation is simply the sum of the absolute values of a particle's X, Y, and Z position.

// Which particle will stay closest to position <0,0,0> in the long term?

package com.devbridie.advent.day20

import com.devbridie.advent.fileToLines

fun main(args: Array<String>) {
    val particles = fileToLines("day20/input").map { parseLine(it) }
    println(getClosestParticleIndex(particles))
}


fun getClosestParticleIndex(particles: List<Particle>): Int {
    // as t to infinity, a is n^2. So it is the dominating factor.
    val closest = particles.minBy { it.acceleration.distance() }
    return particles.indexOf(closest)
}

fun Vector3.distance(other: Vector3 = Vector3(0, 0, 0)): Int {
    return Math.abs(this.x - other.x) + Math.abs(this.y - other.y) + Math.abs(this.z - other.z)
}


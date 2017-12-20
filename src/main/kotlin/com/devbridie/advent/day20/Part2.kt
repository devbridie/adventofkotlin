// --- Part Two ---

// To simplify the problem further, the GPU would like to remove any particles that collide.
// Particles collide if their positions ever exactly match.
// Because particles are updated simultaneously, more than two particles can collide at the same time and place.
// Once particles collide, they are removed and cannot collide with anything else after that tick.

// How many particles are left after all collisions are resolved?

package com.devbridie.advent.day20

import com.devbridie.advent.fileToLines

fun main(args: Array<String>) {
    val particles = fileToLines("day20/input").map { parseLine(it) }
    println(survivingParticles(particles))
}

fun survivingParticles(particles: List<Particle>, iterations: Int = 1000): Int {
    return (0..iterations).fold(particles, { survivors, _ ->
        val crashed = survivors.groupBy { it.position }.filter { it.value.size > 1 }.flatMap { it.value }
        simulationStep(survivors - crashed)
    }).count()
}

fun simulationStep(particles: List<Particle>): List<Particle> = particles.map { simulationStep(it) }

fun simulationStep(particle: Particle): Particle {
    val newVelocity = particle.velocity + particle.acceleration
    val newPosition = particle.position + newVelocity
    return particle.copy(velocity = newVelocity, position = newPosition)
}

operator fun Vector3.plus(other: Vector3): Vector3 {
    return Vector3(this.x + other.x, this.y + other.y, this.z + other.z)
}
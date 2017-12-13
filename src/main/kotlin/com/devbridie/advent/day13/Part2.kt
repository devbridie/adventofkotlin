// --- Part Two ---

// Now, you need to pass through the firewall without being caught - easier said than done.

// You can't control the speed of the packet, but you can delay it any number of picoseconds.
// For each picosecond you delay the packet before beginning your trip, all security scanners move one step.
// You're not in the firewall during this time; you don't enter layer 0 until you stop delaying the packet.

// What is the fewest number of picoseconds that you need to delay the packet to pass through the firewall
// without being caught?

package com.devbridie.advent.day13

import com.devbridie.advent.fileToLines


fun main(args: Array<String>) {
    val layers = fileToLines("day13/input").map { parseLine(it) }.let { linesToLayers(it) }
    println(smallestDelayWithoutCaught(layers))
}

fun smallestDelayWithoutCaught(layers: List<Layer>): Int {
    return generateSequence(0) { it + 1 }.indexOfFirst { !isCaught(layers, it) }
}

fun isCaught(layers: List<Layer>, delay: Int): Boolean {
    return simulationStepSequence(layers, delay).any {
        val scanner = it.scanners[it.depth];
        if (scanner is ScannerPosition) scanner.scannerPosition == 0
        else false
    }
}

fun simulationStepSequence(layers: List<Layer>, delay: Int): Sequence<SimulationStep> {
    return generateSequence(SimulationStep(0, layers.map { getScannerState(it, delay) }), { previousState ->
        val newDepth = previousState.depth + 1
        if (newDepth >= layers.size) return@generateSequence null
        SimulationStep(newDepth, layers.map { getScannerState(it, delay + newDepth) })
    })
}

fun getSeverityOfTrip(layers: List<Layer>, delay: Int): Int {
    return simulationStepSequence(layers, delay).map { getSeverityOfSimulationStep(layers, it) }.sum()
}
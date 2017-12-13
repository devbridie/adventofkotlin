// --- Day 13: Packet Scanners ---

// You need to cross a vast firewall. The firewall consists of several layers, each with a security
// scanner that moves back and forth across the layer. To succeed, you must not be detected by a scanner.

// By studying the firewall briefly, you are able to record (in your puzzle input) the depth of each
// layer and the range of the scanning area for the scanner within it, written as depth: range.
// Each layer has a thickness of exactly 1. A layer at depth 0 begins immediately inside the firewall;
// a layer at depth 1 would start immediately after that.

// Within each layer, a security scanner moves back and forth within its range. Each security scanner
// starts at the top and moves down until it reaches the bottom, then moves up until it reaches the top,
// and repeats. A security scanner takes one picosecond to move one step.

// Your plan is to hitch a ride on a packet about to move through the firewall.
// The packet will travel along the top of each layer, and it moves at one layer per picosecond.
// Each picosecond, the packet moves one layer forward (its first move takes it into layer 0),
// and then the scanners move one step. If there is a scanner at the top of the layer as your packet
// enters it, you are caught. (If a scanner moves into the top of its layer while you are there,
// you are not caught: it doesn't have time to notice you before you leave.)

// The severity of getting caught on a layer is equal to its depth multiplied by its range.
// (Ignore layers in which you do not get caught.) The severity of the whole trip is the sum of these values.

// Given the details of the firewall you've recorded, if you leave immediately,
// what is the severity of your whole trip?

package com.devbridie.advent.day13

import com.devbridie.advent.fileToLines

fun main(args: Array<String>) {
    val layers = fileToLines("day13/input").map { parseLine(it) }.let { linesToLayers(it) }
    println(getSeverityOfTrip(layers))
}

fun getScannerPosition(range: Int, time: Int): Int {
    val values = ((0 until range) + (range - 2 downTo 1))
    return values[time % values.size]
}

fun getScannerState(layer: Layer, time: Int) = when (layer) {
    is EmptyLayer -> NoScanner
    is FirewallLayer -> ScannerPosition(getScannerPosition(layer.range, time))
}

fun simulationStepSequence(layers: List<Layer>): Sequence<SimulationStep> {
    return generateSequence(SimulationStep(0, layers.map { getScannerState(it, 0) }), { previousState ->
        val newDepth = previousState.depth + 1
        if (newDepth >= layers.size) return@generateSequence null
        SimulationStep(newDepth, layers.map { getScannerState(it, newDepth) })
    })
}

fun getSeverityOfSimulationStep(layers: List<Layer>, simulationStep: SimulationStep): Int {
    val depth = simulationStep.depth
    val scannerState = simulationStep.scanners[depth]
    val currentLayer = layers[depth]
    return when {
        currentLayer is FirewallLayer && scannerState is ScannerPosition && scannerState.scannerPosition == 0 -> currentLayer.range * depth
        else -> 0
    }
}

fun getSeverityOfTrip(layers: List<Layer>) = simulationStepSequence(layers).map { getSeverityOfSimulationStep(layers, it) }.sum()
package com.devbridie.advent.day13

import com.devbridie.advent.fileToLines
import org.junit.Assert.assertEquals
import org.junit.Test


class Part1Test {
    @Test
    fun `firewall states (depth = 4)`() {
        val sequence = generateSequence(0) { it + 1  }.map { getScannerPosition(4, it) }.iterator()
        assertEquals(0, sequence.next())
        assertEquals(1, sequence.next())
        assertEquals(2, sequence.next())
        assertEquals(3, sequence.next())
        assertEquals(2, sequence.next())
        assertEquals(1, sequence.next())
        assertEquals(0, sequence.next())
        assertEquals(1, sequence.next())
    }

    @Test
    fun `firewall states (depth = 2)`() {
        val sequence = generateSequence(0) { it + 1  }.map { getScannerPosition(2, it) }.iterator()
        assertEquals(0, sequence.next())
        assertEquals(1, sequence.next())
        assertEquals(0, sequence.next())
        assertEquals(1, sequence.next())
        assertEquals(0, sequence.next())
        assertEquals(1, sequence.next())
    }

    val example = listOf(FirewallLayer(3), FirewallLayer(2), EmptyLayer, EmptyLayer, FirewallLayer(4), EmptyLayer, FirewallLayer(4))

    @Test
    fun `parse layers example`() {
        val layers = linesToLayers(fileToLines("day13/example").map { parseLine(it) })
        assertEquals(example, layers)
    }

    @Test
    fun `travel states example`() {
        val travel = simulationStepSequence(example).iterator()
        assertEquals(SimulationStep(0, listOf(ScannerPosition(0), ScannerPosition(0), NoScanner, NoScanner, ScannerPosition(0), NoScanner, ScannerPosition(0))), travel.next())
        assertEquals(SimulationStep(1, listOf(ScannerPosition(1), ScannerPosition(1), NoScanner, NoScanner, ScannerPosition(1), NoScanner, ScannerPosition(1))), travel.next())
        assertEquals(SimulationStep(2, listOf(ScannerPosition(2), ScannerPosition(0), NoScanner, NoScanner, ScannerPosition(2), NoScanner, ScannerPosition(2))), travel.next())
        assertEquals(SimulationStep(3, listOf(ScannerPosition(1), ScannerPosition(1), NoScanner, NoScanner, ScannerPosition(3), NoScanner, ScannerPosition(3))), travel.next())
        assertEquals(SimulationStep(4, listOf(ScannerPosition(0), ScannerPosition(0), NoScanner, NoScanner, ScannerPosition(2), NoScanner, ScannerPosition(2))), travel.next())
        assertEquals(SimulationStep(5, listOf(ScannerPosition(1), ScannerPosition(1), NoScanner, NoScanner, ScannerPosition(1), NoScanner, ScannerPosition(1))), travel.next())
        assertEquals(SimulationStep(6, listOf(ScannerPosition(2), ScannerPosition(0), NoScanner, NoScanner, ScannerPosition(0), NoScanner, ScannerPosition(0))), travel.next())
    }

    @Test
    fun `travel severity example`() {
        assertEquals(0 * 3 + 6 * 4, getSeverityOfTrip(example))
    }
}
package com.devbridie.advent.day13

import org.junit.Assert.assertEquals
import org.junit.Test


class Part2Test {
    val example = listOf(FirewallLayer(3), FirewallLayer(2), EmptyLayer, EmptyLayer, FirewallLayer(4), EmptyLayer, FirewallLayer(4))

    @Test
    fun `wait 10 states`() {
        val travel = simulationStepSequence(example, 10).iterator()
        assertEquals(SimulationStep(0, listOf(ScannerPosition(2), ScannerPosition(0), NoScanner, NoScanner, ScannerPosition(2), NoScanner, ScannerPosition(2))), travel.next())
        assertEquals(SimulationStep(1, listOf(ScannerPosition(1), ScannerPosition(1), NoScanner, NoScanner, ScannerPosition(1), NoScanner, ScannerPosition(1))), travel.next())
        assertEquals(SimulationStep(2, listOf(ScannerPosition(0), ScannerPosition(0), NoScanner, NoScanner, ScannerPosition(0), NoScanner, ScannerPosition(0))), travel.next())
        assertEquals(SimulationStep(3, listOf(ScannerPosition(1), ScannerPosition(1), NoScanner, NoScanner, ScannerPosition(1), NoScanner, ScannerPosition(1))), travel.next())
        assertEquals(SimulationStep(4, listOf(ScannerPosition(2), ScannerPosition(0), NoScanner, NoScanner, ScannerPosition(2), NoScanner, ScannerPosition(2))), travel.next())
        assertEquals(SimulationStep(5, listOf(ScannerPosition(1), ScannerPosition(1), NoScanner, NoScanner, ScannerPosition(3), NoScanner, ScannerPosition(3))), travel.next())
        assertEquals(SimulationStep(6, listOf(ScannerPosition(0), ScannerPosition(0), NoScanner, NoScanner, ScannerPosition(2), NoScanner, ScannerPosition(2))), travel.next())
    }

    @Test
    fun `calculate severity example delay 10`() {
        assertEquals(0, getSeverityOfTrip(example, 10))
    }

    @Test
    fun `delay 0 is the same severity`() {
        assertEquals(getSeverityOfTrip(example), getSeverityOfTrip(example, 0))
    }

    @Test
    fun `find smallest delay without being caught`() {
        assertEquals(10, smallestDelayWithoutCaught(example))
    }
}
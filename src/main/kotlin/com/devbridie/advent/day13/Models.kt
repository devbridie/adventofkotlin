package com.devbridie.advent.day13

sealed class Layer
object EmptyLayer : Layer()
data class FirewallLayer(val range: Int) : Layer()

sealed class ScannerState
object NoScanner : ScannerState()
data class ScannerPosition(val scannerPosition: Int) : ScannerState()

data class SimulationStep(val depth: Int, val scanners: List<ScannerState>)
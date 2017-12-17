package com.devbridie.advent.day17

import org.junit.Assert.assertEquals
import org.junit.Test

class Part1Test {
    @Test
    fun `example spinlock sequence`() {
        val sequence = spinlockSequence(3).iterator()
        assertEquals(SpinlockState(0, listOf(0)), sequence.next())
        assertEquals(SpinlockState(1, listOf(0, 1)), sequence.next())
        assertEquals(SpinlockState(1, listOf(0, 2, 1)), sequence.next())
        assertEquals(SpinlockState(2, listOf(0, 2, 3, 1)), sequence.next())
        assertEquals(SpinlockState(2, listOf(0, 2, 4, 3, 1)), sequence.next())
        assertEquals(SpinlockState(1, listOf(0, 5, 2, 4, 3, 1)), sequence.next())
        assertEquals(SpinlockState(5, listOf(0, 5, 2, 4, 3, 6, 1)), sequence.next())
        assertEquals(SpinlockState(2, listOf(0, 5, 7, 2, 4, 3, 6, 1)), sequence.next())
        assertEquals(SpinlockState(6, listOf(0, 5, 7, 2, 4, 3, 8, 6, 1)), sequence.next())
        assertEquals(SpinlockState(1, listOf(0, 9, 5, 7, 2, 4, 3, 8, 6, 1)), sequence.next())
    }

    @Test
    fun `example spinlock answer`() {
        assertEquals(638, shortCircuitSpinlock(3))
    }
}
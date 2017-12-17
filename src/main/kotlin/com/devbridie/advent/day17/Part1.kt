// --- Day 17: Spinlock ---

// Suddenly, whirling in the distance, you notice what looks like a massive, pixelated hurricane:
// a deadly spinlock. This spinlock isn't just consuming computing power, but memory, too; vast,
// digital mountains are being ripped from the ground and consumed by the vortex.

// This spinlock's algorithm is simple but efficient, quickly consuming everything in its path.
// It starts with a circular buffer containing only the value 0, which it marks as the current position.
// It then steps forward through the circular buffer some number of steps (your puzzle input)
// before inserting the first new value, 1, after the value it stopped on.
// The inserted value becomes the current position. Then, it steps forward from there the same
// number of steps, and wherever it stops, inserts after it the second new value, 2, and uses that
// as the new current position again.

// It repeats this process of stepping forward, inserting a new value, and using the location of the
// inserted value as the new current position a total of 2017 times, inserting 2017 as its final operation,
// and ending with a total of 2018 values (including 0) in the circular buffer.

// Perhaps, if you can identify the value that will ultimately be after the last value written (2017),
// you can short-circuit the spinlock. In this example, that would be 638.

// What is the value after 2017 in your completed circular buffer?

package com.devbridie.advent.day17

fun main(args: Array<String>) {
    val input = 386
    println(shortCircuitSpinlock(input))
}

fun shortCircuitSpinlock(input: Int): Int {
    val (_, buffer) = spinlockSequence(input).take(2017+1).last()
    return buffer[buffer.indexOf(2017)+1]
}

fun spinlockSequence(input: Int): Sequence<SpinlockState> {
    return generateSequence(SpinlockState(0, listOf(0))) { previous ->
        val skipPosition = (previous.position + input) % previous.buffer.size
        val newPosition = skipPosition + 1
        SpinlockState(newPosition, previous.buffer.toMutableList().apply {
            add(newPosition, previous.buffer.size)
        })
    }
}

data class SpinlockState(val position: Int, val buffer: List<Int>)
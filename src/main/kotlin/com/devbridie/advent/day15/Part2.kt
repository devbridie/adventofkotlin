// --- Part Two ---

// In the interest of trying to align a little better,
// the generators get more picky about the numbers they actually give to the judge.

// They still generate values in the same way,
// but now they only hand a value to the judge when it meets their criteria:

// - Generator A looks for values that are multiples of 4.
// - Generator B looks for values that are multiples of 8.

// Each generator functions completely independently: they both go through values entirely on their
// own, only occasionally handing an acceptable value to the judge, and otherwise working through the same
// sequence of values as before until they find one.

// The judge still waits for each generator to provide it with a value before comparing them
// (using the same comparison method as before). It keeps track of the order it receives values;
// the first values from each generator are compared, then the second values from each generator,
// then the third values, and so on.

// This change makes the generators much slower, and the judge is getting impatient;
// it is now only willing to consider 5 million pairs.

// After 5 million pairs, but using this new generator logic, what is the judge's final count?

package com.devbridie.advent.day15

fun main(args: Array<String>) {
    val generatorA = onlyMultiples(createGenerator(703, 16807), 4)
    val generatorB = onlyMultiples(createGenerator(516, 48271), 8)
    println(judge(generatorA.zip(generatorB), 5_000_000))
}

fun onlyMultiples(sequence: Sequence<Long>, number: Long) = sequence.filter { it % number == 0L }
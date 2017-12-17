// --- Day 15: Dueling Generators ---

// Here, you encounter a pair of dueling generators. The generators, called generator A and
// generator B, are trying to agree on a sequence of numbers. However, one of them is malfunctioning,
// and so the sequences don't always match.

// As they do this, a judge waits for each of them to generate its next value, compares the lowest
// 16 bits of both values, and keeps track of the number of times those parts of the values match.

// The generators both work on the same principle. To create its next value, a generator will take
// the previous value it produced, multiply it by a factor (generator A uses 16807; generator B uses 48271),
// and then keep the remainder of dividing that resulting product by 2147483647.
// That final remainder is the value it produces next.

// To calculate each generator's first value, it instead uses a specific starting value as its
// "previous value" (as listed in your puzzle input).

// To get a significant sample, the judge would like to consider 40 million pairs.

// After 40 million pairs, what is the judge's final count?

package com.devbridie.advent.day15

fun main(args: Array<String>) {
    val generatorA = createGenerator(703, 16807)
    val generatorB = createGenerator(516, 48271)
    println(judge(generatorA.zip(generatorB), 40_000_000))
}

fun judge(sequence: Sequence<Pair<Long, Long>>, amount: Int) = sequence.take(amount).filter(last16BitsEqual).count()

fun createGenerator(init: Long, factor: Long) = generateSequence(init * factor) { previous ->
    (previous * factor) % 2147483647L
}

val last16BitsEqual = { (first, second): Pair<Long, Long> ->
    first.and(0xffff) == second.and(0xffff)
}
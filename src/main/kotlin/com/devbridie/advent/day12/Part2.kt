// --- Part Two ---

// There are more programs than just the ones in the group containing program ID 0.
// The rest of them have no way of reaching that group, and still might have no way of reaching each other.

// A group is a collection of programs that can all communicate via pipes either directly or indirectly.
// The programs you identified just a moment ago are all part of the same group.
// Now, they would like you to determine the total number of groups.

// How many groups are there in total?

package com.devbridie.advent.day12

import com.devbridie.advent.fileToLines

fun main(args: Array<String>) {
    val links = linesToLinks(fileToLines("day12/input"))
    println(getAmountOfGroups(links))
}

data class GroupRemainder(val group: List<Int>, val remainder: List<Int>)

fun findGroupRemainder(links: List<Link>, ungrouped: List<Int>): GroupRemainder {
    val group = discoverProgramsInGroup(links, ungrouped.first())
    return GroupRemainder(group, ungrouped - group)
}

fun groupSequence(links: List<Link>): Sequence<List<Int>> {
    val programs = getAllPrograms(links)
    val sequence = generateSequence(findGroupRemainder(links, programs), { previous ->
        if (previous.remainder.isEmpty()) null
        else findGroupRemainder(links, previous.remainder)
    })
    return sequence.map { it.group }
}

fun getAllPrograms(links: List<Link>) = links.flatMap { listOf(it.side1, it.side2) }.distinct()

fun getAmountOfGroups(links: List<Link>) = groupSequence(links).count()
package com.devbridie.advent.day7

import com.devbridie.advent.fileToLines


fun main(args: Array<String>) {
    val (_, links) = parseLines(fileToLines("day7/input"))
    println(findBottomName(links))
}

fun findBottomName(links: List<Link>, current: Link = links[0]): String {
    val bottomOfCurrent = current.bottom
    val hasBelow = links.find { it.top == bottomOfCurrent }
    return if (hasBelow == null) bottomOfCurrent else findBottomName(links, hasBelow)
}
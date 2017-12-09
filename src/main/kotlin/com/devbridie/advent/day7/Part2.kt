package com.devbridie.advent.day7

import com.devbridie.advent.fileToLines


fun main(args: Array<String>) {
    val (discs, links) = parseLines(fileToLines("day7/input"))
    println(findSmallestWeightAdjustment(discs, links))
}

fun findSmallestWeightAdjustment(discs: List<Disc>, links: List<Link>): Int {
    val bottom = discs.findByName(findBottomName(links))
    val invalidDiscs = findInvalidBottoms(bottom, discs, links)

    val lightestError = invalidDiscs.first()
    val topDiscNames = links.discsOn(lightestError)
    val topDiscs = topDiscNames.map { discs.findByName(it) }

    data class StackWeight(val bottom: Disc, val weight: Int)

    val stackWeights = topDiscs.map { StackWeight(it, getWeightOfStack(it, discs, links)) }
    val wrongWeight = stackWeights.find { current -> stackWeights.filter { current.weight == it.weight }.count() == 1 }!!
    val correctWeight = stackWeights.first { it.weight != wrongWeight.weight }

    val weightDelta = correctWeight.weight - wrongWeight.weight
    return topDiscs.findByName(wrongWeight.bottom.name).weight + weightDelta
}

fun getWeightOfStack(disc: Disc, discs: List<Disc>, links: List<Link>): Int {
    val linksFrom = links.filter { it.bottom == disc.name }
    val discsOnTop = linksFrom.map { it.top }
    val weights = discsOnTop.map { top -> getWeightOfStack(discs.findByName(top), discs, links) }
    return disc.weight + weights.sum()
}

fun findInvalidBottoms(disc: Disc, discs: List<Disc>, links: List<Link>): List<Disc> {
    val topDiscNames = links.discsOn(disc)
    val topDiscs = topDiscNames.map { top -> discs.findByName(top) }
    val weights = topDiscs.map { getWeightOfStack(it, discs, links) }
    val distinct = weights.distinct()
    val invalidChildren = topDiscs.map { findInvalidBottoms(it, discs, links) }.flatten()
    return if (distinct.size == 2) {
        invalidChildren + disc
    } else {
        invalidChildren
    }
}


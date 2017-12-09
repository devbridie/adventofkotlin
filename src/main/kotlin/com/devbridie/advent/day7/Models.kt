package com.devbridie.advent.day7

data class Link(val bottom: String, val top: String)
data class Disc(val name: String, val weight: Int)

fun List<Disc>.findByName(name: String) = find { it.name == name }!!

fun List<Link>.discsOn(name: String) = filter { it.bottom == name }.map { it.top }
fun List<Link>.discsOn(disc: Disc) = discsOn(disc.name)
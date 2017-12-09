package com.devbridie.advent

fun fileToLine(file: String): String {
    return Thread.currentThread().contextClassLoader.getResource(file).openStream().bufferedReader().readLine()
}

fun fileToLines(file: String): List<String> {
    return Thread.currentThread().contextClassLoader.getResource(file).openStream().bufferedReader().readLines()
}
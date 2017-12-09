package com.devbridie.advent.day8

import com.devbridie.advent.fileToLines
import org.junit.Assert.assertEquals
import org.junit.Test

class ParserTest {
    @Test
    fun `test parser on example`() {
        testFile("day8/example")
    }

    @Test
    fun `test parser on input`() {
        testFile("day8/input")
    }
}

fun testFile(file: String) {
    val lines = fileToLines(file)
    val instructions = lines.map { parseInstruction(it) }.map { toString(it) }
    lines.zip(instructions).forEach { (expected, actual) ->
        assertEquals(expected, actual)
    }
}

fun toString(operator: Operation): String {
    return when (operator) {
        Operation.INCREASE -> "inc"
        Operation.DECREASE -> "dec"
    }
}

fun toString(operator: Operator): String {
    return when(operator) {
        Operator.LT -> "<"
        Operator.GT -> ">"
        Operator.LTE -> "<="
        Operator.GTE -> ">="
        Operator.EQ -> "=="
        Operator.NEQ -> "!="
    }
}

fun toString(expression: Expression): String {
    return when (expression) {
        is ValueExpression -> expression.value.toString()
        is RegisterExpression -> expression.name
    }
}

fun toString(action: Action): String {
    return listOf(action.register, toString(action.operation), action.amount.toString()).joinToString(separator = " ")
}

fun toString(condition: Condition): String {
    return listOf(toString(condition.left), toString(condition.operator), toString(condition.right)).joinToString(separator = " ")
}

fun toString(instruction: Instruction): String {
    return listOf(toString(instruction.action), "if", toString(instruction.condition)).joinToString(separator = " ")
}
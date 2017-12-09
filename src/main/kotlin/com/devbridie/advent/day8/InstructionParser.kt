package com.devbridie.advent.day8

sealed class ParseException(reason: String) : Exception(reason)
class OperatorParseException(operator: String) : ParseException("Could not parse operator $operator")

fun parseInstruction(string: String): Instruction {
    operator fun List<String>.component6() = this[5]
    operator fun List<String>.component7() = this[6]
    val (register, operation, value, _, left, operator, right) = string.split(" ")
    return Instruction(
            Action(
                    register,
                    parseOperation(operation),
                    value.toInt()
            ),
            Condition(
                    parseExpression(left),
                    parseOperator(operator),
                    parseExpression(right)
            )
    )
}

fun parseOperation(string: String): Operation {
    return when (string) {
        "inc" -> Operation.INCREASE
        "dec" -> Operation.DECREASE
        else -> throw OperatorParseException(string)
    }
}

fun parseExpression(string: String): Expression {
    return when {
        string.matches(Regex("^[-0-9]+$")) -> ValueExpression(string.toInt())
        else -> RegisterExpression(string)
    }
}

fun parseOperator(string: String): Operator {
    return when (string) {
        "<" -> Operator.LT
        ">" -> Operator.GT
        "<=" -> Operator.LTE
        ">=" -> Operator.GTE
        "==" -> Operator.EQ
        "!=" -> Operator.NEQ
        else -> throw OperatorParseException(string)
    }
}

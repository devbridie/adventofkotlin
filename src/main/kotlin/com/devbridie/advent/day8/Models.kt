package com.devbridie.advent.day8

enum class Operation { INCREASE, DECREASE; }

data class Action(val register: String, val operation: Operation, val amount: Int)

sealed class Expression
data class RegisterExpression(val name: String) : Expression()
data class ValueExpression(val value: Int) : Expression()

enum class Operator {
    LT, LTE,
    GT, GTE,
    EQ, NEQ
}

data class Condition(val left: Expression, val operator: Operator, val right: Expression)

data class Instruction(val action: Action, val condition: Condition)
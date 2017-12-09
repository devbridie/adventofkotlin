package com.devbridie.advent.day8

class ProgramState(val registerMap: Map<String, Int> = mapOf()) : Map<String, Int> by registerMap {
    override operator fun get(key: String) = registerMap[key] ?: 0

    override fun toString(): String {
        return registerMap.toString()
    }
}

fun execute(instructions: List<Instruction>): ProgramState {
    return instructions.fold(ProgramState(), { programState, instruction ->
        step(instruction, programState)
    })
}

fun executeWithHistory(instructions: List<Instruction>): List<ProgramState> {
    return instructions.fold(listOf(), { history, instruction ->
        val lastState = history.lastOrNull() ?: ProgramState()
        history + step(instruction, lastState)
    })
}

fun step(instruction: Instruction, programState: ProgramState): ProgramState = when {
    programState.check(instruction.condition) -> programState.execute(instruction.action)
    else -> programState
}


fun Operator.toFunction(): (Int, Int) -> Boolean {
    return when (this) {
        Operator.LT -> { a, b -> a < b }
        Operator.GT -> { a, b -> a > b }
        Operator.LTE -> { a, b -> a <= b }
        Operator.GTE -> { a, b -> a >= b }
        Operator.EQ -> { a, b -> a == b }
        Operator.NEQ -> { a, b -> a != b }
    }
}

fun ProgramState.execute(action: Action): ProgramState {
    val value = this[action.register]
    val newValue = when (action.operation) {
        Operation.INCREASE -> value + action.amount
        Operation.DECREASE -> value - action.amount
    }
    return ProgramState(this + Pair(action.register, newValue))
}

fun ProgramState.eval(expression: Expression): Int {
    return when (expression) {
        is ValueExpression -> expression.value
        is RegisterExpression -> this[expression.name]
    }
}

fun ProgramState.check(condition: Condition): Boolean {
    val operatorFunction = condition.operator.toFunction()
    return operatorFunction(eval(condition.left), eval(condition.right))
}
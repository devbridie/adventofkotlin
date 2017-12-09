package com.devbridie.advent.day8

import org.junit.Assert.assertEquals
import org.junit.Test

class Day8TestEndToEnd {
    @Test
    fun `short e2e`() {
        testProgram {
            +("r1 inc 100 if u == 0" - { assertEquals(100, this["r1"]) })
            +("r2 inc 100 if r1 == 100" - { assertEquals(100, this["r2"]) })
            +("r3 inc 100 if r1 > 99" - { assertEquals(100, this["r3"]) })
            +("r4 dec 100 if r1 < 101" - { assertEquals(-100, this["r4"]) })
            +("r5 inc -100 if r1 != 100" - { assertEquals(0, this["r5"]) })
            +("r6 dec -100 if r1 != 100" - { assertEquals(0, this["r6"]) })
            +("r7 inc -100 if r1 == 100" - { assertEquals(-100, this["r7"]) })
            +("r8 dec -100 if r1 == 100" - { assertEquals(100, this["r8"]) })
            +("r9 inc -50 if r1 == 100" - { assertEquals(-50, this["r9"]) })
            +("r9 dec -5 if r1 == 100" - { assertEquals(-45, this["r9"]) })
            +("r10 dec -5 if r1 >= 100" - { assertEquals(5, this["r10"]) })
            +("r11 dec -5 if r1 <= 100" - { assertEquals(5, this["r11"]) })
        }
    }
}


typealias PostExecuteTest = ProgramState.() -> Unit

data class InstructionTest(val instruction: Instruction, val test: PostExecuteTest)

fun testProgram(i: TestBuilder.() -> Unit) {
    TestBuilder().apply {
        i(this)
        tests.fold(ProgramState(), { acc, (instruction, test) ->
            step(instruction, acc).apply {
                test()
            }
        })
    }
}

class TestBuilder {
    val tests = mutableListOf<InstructionTest>()
    operator fun Instruction.unaryPlus() {
        tests.add(InstructionTest(this, {}))
    }

    operator fun String.unaryPlus() {
        tests.add(InstructionTest(parseInstruction(this), {}))
    }

    operator fun InstructionTest.unaryPlus() {
        tests += this
    }

    operator fun String.minus(test: PostExecuteTest): InstructionTest {
        return InstructionTest(parseInstruction(this), test)
    }
}
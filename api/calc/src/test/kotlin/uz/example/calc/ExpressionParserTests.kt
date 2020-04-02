package uz.example.calc

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import uz.example.calc.parser.ExpressionParser

class ExpressionParserTests {

    private val expressionParser = ExpressionParser()

    @Test
    fun simpleAdd() {
        val result = expressionParser.evaluate("1+3")

        assertEquals(4.0, result)
    }

    @Test
    fun divide() {
        val result = expressionParser.evaluate("4/5")

        assertEquals(4 / 5.0, result)
    }

    @Test
    fun subtract() {
        val result = expressionParser.evaluate("10-9-9")

        assertEquals(-8.0, result)
    }

    @Test
    fun simpleUnaryMinus() {
        val result = expressionParser.evaluate("-9")

        assertEquals(-9.0, result)
    }

    @Test
    fun negativeAdd() {
        val result = expressionParser.evaluate("-9-9.6")

        assertEquals(-18.6, result)
    }

    @Test
    fun positiveDivisionByZero() {
        val result = expressionParser.evaluate("9/0")

        assertEquals(Double.POSITIVE_INFINITY, result)
    }

    @Test
    fun negativeDivisionByZero() {
        val result = expressionParser.evaluate("-9.0/0")

        assertEquals(Double.NEGATIVE_INFINITY, result)
    }
}
package uz.example.calc.repository

import org.springframework.stereotype.Repository
import uz.example.calc.model.MathRule
import uz.example.calc.parser.ExpressionParser

@Repository
class MathRepository {

    private val expressionParser = ExpressionParser()

    fun evaluate(rule: MathRule): Double {

        expressionParser.enableLog(true)

        return expressionParser.evaluate(rule.formula)
    }

}
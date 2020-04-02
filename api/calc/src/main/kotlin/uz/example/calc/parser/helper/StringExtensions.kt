package uz.example.calc.parser.helper

import uz.example.calc.parser.enum.FunctionalOperators
import uz.example.calc.parser.enum.NormalOperators

infix fun <T> String.isIn(operators: Array<T>): Boolean {

    for (operator in operators) {
        if (operator is NormalOperators) {
            if (this == operator.sign) {
                return true
            }
        } else if (operator is FunctionalOperators) {
            if (this.contains(operator.func)) {
                return true
            } else if (this.contains(FunctionalOperators.logx.func)) {
                return true
            }
        }
    }
    return false
}

infix fun <T> String.notIn(operators: Array<T>): Boolean {
    return !(this isIn operators)
}



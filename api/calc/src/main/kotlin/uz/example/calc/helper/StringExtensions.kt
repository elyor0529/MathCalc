package uz.example.calc.helper

fun String.toDoubleOrZero() = this.toDoubleOrNull() ?: 0.0

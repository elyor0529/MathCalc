package uz.example.calc.parser

import uz.example.calc.helper.toDoubleOrZero

class ExpressionParser {

    private fun multiply(s: String): String {
        val b = s.split('*').map { it.toDoubleOrZero() }

        return (b[0] * b[1]).toString()
    }

    private fun divide(s: String): String {
        val b = s.split('/').map { it.toDoubleOrZero() }

        return (b[0] / b[1]).toString()
    }

    private fun add(s: String): String {
        var t = s.replace(Regex("""^\+"""), "").replace(Regex("""\++"""), "+")
        val b = t.split('+').map { it.toDoubleOrZero() }

        return (b[0] + b[1]).toString()
    }

    private fun subtract(s: String): String {
        val t = s.replace(Regex("""(\+-|-\+)"""), "-")
        if ("--" in t) return add(t.replace("--", "+"))
        val b = t.split('-').map { it.toDoubleOrZero() }

        return (if (b.size == 3) -b[1] - b[2] else b[0] - b[1]).toString()
    }

    private fun format(s: String): String {
        var t = s.replace(Regex("""[()]"""), "")
        val reMD = Regex("""\d+\.?\d*\s*[*/]\s*[+-]?\d+\.?\d*""")
        val reM = Regex("""\*""")
        val reAS = Regex("""-?\d+\.?\d*\s*[+-]\s*[+-]?\d+\.?\d*""")
        val reA = Regex("""\d\+""")

        while (true) {
            val match = reMD.find(t) ?: break
            val exp = match.value
            val match2 = reM.find(exp)

            t = if (match2 != null)
                t.replace(exp, multiply(exp))
            else
                t.replace(exp, divide(exp))
        }

        while (true) {
            val match = reAS.find(t) ?: break
            val exp = match.value
            val match2 = reA.find(exp)

            t = if (match2 != null)
                t.replace(exp, add(exp))
            else
                t.replace(exp, subtract(exp))
        }

        return t
    }

    fun evaluate(s: String): Double {
        var t = s.replace(Regex("""\s"""), "").replace("""^\+""", "")
        val rePara = Regex("""\([^()]*\)""")

        while (true) {
            val match = rePara.find(t) ?: break
            val exp = match.value
            t = t.replace(exp, format(exp))
        }

        return format(t).toDoubleOrZero()
    }

}
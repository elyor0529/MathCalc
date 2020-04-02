package uz.example.calc.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import uz.example.calc.model.MathRule
import uz.example.calc.repository.MathRepository
import javax.validation.Valid

@RestController
@RequestMapping("/math")
@CrossOrigin("*")
class MathController(@Autowired private val repository: MathRepository) {

    @PostMapping("eval")
    fun evaluate(@Valid @RequestBody rule: MathRule): Double {
        return repository.evaluate(rule)
    }

}
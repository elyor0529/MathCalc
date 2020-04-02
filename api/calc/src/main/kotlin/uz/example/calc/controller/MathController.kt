package uz.example.calc.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import uz.example.calc.model.MathRule
import uz.example.calc.repository.MathRepository
import javax.validation.Valid

@RestController
@RequestMapping("/math")
class MathController(@Autowired private val repository: MathRepository) {

    @PostMapping("calc")
    fun calc(@Valid @RequestBody rule: MathRule): Double {
        return repository.execute(rule)
    }

}
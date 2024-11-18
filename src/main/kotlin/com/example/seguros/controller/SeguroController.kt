package com.example.seguros.controller

import com.example.seguros.exceptions.ValidationException
import com.example.seguros.model.Seguro
import com.example.seguros.service.SeguroService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/seguros")
class SeguroController {

    @Autowired
    lateinit var seguroService: SeguroService

    @GetMapping("/{id}")
    fun get(@PathVariable id:String): ResponseEntity<Seguro>? {
        if (id.isBlank() || id == "a"){
            throw ValidationException("El id no puede estar vacío")
        }
        return null
    }
}
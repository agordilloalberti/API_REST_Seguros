package com.example.seguros.service

import com.example.seguros.exceptions.NotFoundException
import com.example.seguros.model.Seguro
import org.springframework.stereotype.Service

@Service
class SeguroService {

    fun get(id:String){

        val seguro: Seguro? = null

        if (seguro==null){
            throw NotFoundException("El seguro no ha sido encontrado")
        }
    }
}
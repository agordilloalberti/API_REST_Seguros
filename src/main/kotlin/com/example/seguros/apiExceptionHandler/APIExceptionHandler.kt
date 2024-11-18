package com.example.seguros.apiExceptionHandler

import com.example.seguros.exceptions.ErrorRespuesta
import com.example.seguros.exceptions.NotFoundException
import com.example.seguros.exceptions.ValidationException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import kotlin.Exception

@ControllerAdvice
class APIExceptionHandler {

    @ExceptionHandler(
        IllegalArgumentException::class
        ,NumberFormatException::class
        , ValidationException::class
    )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleBadRequest(request:HttpServletRequest,e: Exception): ErrorRespuesta{
        return ErrorRespuesta(e.message,request.requestURI)
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    fun handleNotFound(request: HttpServletRequest,e:Exception): ErrorRespuesta{
        return ErrorRespuesta(e.message,request.requestURI)
    }
}
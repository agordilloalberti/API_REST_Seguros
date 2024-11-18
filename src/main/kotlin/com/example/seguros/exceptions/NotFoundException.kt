package com.example.seguros.exceptions

class NotFoundException(mensaje: String) : Exception("Not Found exception (404). $mensaje"){}
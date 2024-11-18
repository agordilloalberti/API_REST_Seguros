package com.example.seguros.repository

import com.example.seguros.model.Seguro
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SeguroRepository : JpaRepository<Seguro, Int> {
}
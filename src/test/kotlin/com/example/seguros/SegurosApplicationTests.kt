package com.example.seguros

import com.example.seguros.utils.DniValidator
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertTrue

@SpringBootTest
class SegurosApplicationTests {

	@Test
	fun contextLoads() {
	}

	@Test
	fun testDniFormat() {

		val dniValidator: DniValidator = DniValidator

		assertTrue(dniValidator.checkDniFormat("717171717B"))

		assertTrue(dniValidator.checkValidDni("15309325L"))

	}

}

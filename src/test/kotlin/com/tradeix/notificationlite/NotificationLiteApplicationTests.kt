package com.tradeix.notificationlite

import com.fasterxml.jackson.databind.ObjectMapper
import com.tradeix.notificationlite.type.Invoice
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import java.time.Instant
import java.util.*

class NotificationLiteApplicationTests {

	@Test
	fun contextLoads() {
		val invoice = Invoice(
				"1234",
				1,
				"test",
				"test",
				"test",
				mapOf("1" to "one"),
				Instant.now().toString(),
				Instant.now().toString(),
				Currency.getInstance("USD"),
				BigDecimal.TEN
		)

		val payload = ObjectMapper().writeValueAsString(invoice)

	}
}

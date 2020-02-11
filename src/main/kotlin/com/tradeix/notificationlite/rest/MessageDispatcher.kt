package com.tradeix.notificationlite.rest

import com.fasterxml.jackson.databind.ObjectMapper
import com.tradeix.notificationlite.type.Invoice
import mu.KLogging
import okhttp3.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class MessageDispatcher {

    companion object: KLogging() {
        val JSON = MediaType.parse("application/json; charset=utf-8")
    }

    @Value(value = "\${outgoing.address}")
    private val outgoingAddress : String = "localhost/events"

    private val mapper = ObjectMapper()

    fun sendToUser(invoice: Invoice) {

        val okHttpClient = OkHttpClient()

        val payload = mapper.writeValueAsString(invoice)

        val requestBody: RequestBody = RequestBody.create(JSON, payload);

        val request = Request.Builder()
                .method("POST",requestBody)
                .url(outgoingAddress)
                .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                throw Exception(e.message)
            }
            override fun onResponse(call: Call, response: Response) {
                when {
                    (response.code() < 400) -> logger.info("Call successful, response : $response")
                    else -> throw Exception("Invalid response code ${response.code()}")
                }

            }
        })
    }
}
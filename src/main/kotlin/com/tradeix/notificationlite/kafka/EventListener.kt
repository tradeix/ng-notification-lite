package com.tradeix.notificationlite.kafka


import com.tradeix.notificationlite.rest.MessageDispatcher
import com.tradeix.notificationlite.type.Invoice
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class EventListener(
        @Autowired
        val messageDispatcher: MessageDispatcher
){
    companion object: KLogging()

    @KafkaListener(topics = ["\${kafka.topic.name}"], containerFactory = "eventKafkaListenerContainerFactory")
    fun eventListener(invoice : Invoice) {
        logger.info("Received invoice : $invoice")
        try {
            messageDispatcher.sendToUser(invoice)
        }
        catch(e: Exception){
            logger.error("failed to send invoice. Id: ${invoice.invoiceId}, Error : ${e.message}")
        }
    }
}
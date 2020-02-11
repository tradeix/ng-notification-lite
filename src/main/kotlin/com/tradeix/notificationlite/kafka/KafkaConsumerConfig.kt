package com.tradeix.notificationlite.kafka

import com.tradeix.notificationlite.type.Invoice
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer2
import org.springframework.kafka.support.serializer.JsonDeserializer

@EnableKafka
@Configuration
class KafkaConsumerConfig {

    @Value(value = "\${kafka.consumer.group}")
    private val consumerGroup : String? = null

    @Value(value = "\${kafka.bootstrapAddress}")
    private val bootstrapAddress: String? = null

    @Value(value = "\${kafka.consumer.batchSize}")
    private val batchSize : Int = 500

    fun consumerFactory() : ConsumerFactory<String, Invoice>{
        val props: MutableMap<String, Any?> = HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        props[ConsumerConfig.GROUP_ID_CONFIG] = consumerGroup

        val keyErrorHandlingDeserializer = ErrorHandlingDeserializer2(StringDeserializer())

        val valueErrorHandlingDeserializer = ErrorHandlingDeserializer2(JsonDeserializer(Invoice::class.java))

        props[ConsumerConfig.FETCH_MAX_BYTES_CONFIG] = batchSize // to avoid over-consuming in one poll
        return DefaultKafkaConsumerFactory(props, keyErrorHandlingDeserializer, valueErrorHandlingDeserializer)
    }

    @Bean
    fun eventKafkaListenerContainerFactory() : ConcurrentKafkaListenerContainerFactory<String, Invoice>{
        val factory = ConcurrentKafkaListenerContainerFactory<String, Invoice>()
        factory.consumerFactory = consumerFactory()
        return factory
    }
}
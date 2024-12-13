package com.abacatepay.spring_demo.bean

import com.abacatepay.client.AbacatePay
import com.abacatepay.client.AbacatePayClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfig(
    @Value("\${abacatepay.api.key}") private val apiKey: String
) {

    @Bean
    fun abacatePayClient(): AbacatePay {
        return AbacatePayClient(apiKey = apiKey)
    }
}
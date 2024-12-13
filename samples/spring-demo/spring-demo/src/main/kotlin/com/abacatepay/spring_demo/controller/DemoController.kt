package com.abacatepay.spring_demo.controller

import com.abacatepay.client.AbacatePay
import kotlinx.coroutines.runBlocking
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/abacatepay")
class DemoController(private val abacatePayClient: AbacatePay) {

    @GetMapping
    fun getAbacatePayBillings(): Any {
        return runBlocking {
            abacatePayClient.listBillings()
        }
    }
}
package com.abacatepay.spring_demo

import com.abacatepay.client.AbacatePay
import com.abacatepay.spring_demo.controller.DemoController
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
class SpringDemoApplicationTests {

    @Test
    fun loadMock() {
        val abacatePayMock = Mockito.mock(AbacatePay::class.java)
        val demoController = DemoController(abacatePayMock)
        Assertions.assertNotNull(demoController)
    }
}

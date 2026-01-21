package com.abacatepay.model.request

import com.abacatepay.model.enums.WithdrawMethod
import kotlinx.serialization.Serializable

@Serializable
data class WithdrawRequest(
    val externalId: String,
    val method: WithdrawMethod = WithdrawMethod.PIX,
    val amount: Int,
    val pix: PixRequest,
    val description: String? = null,
)
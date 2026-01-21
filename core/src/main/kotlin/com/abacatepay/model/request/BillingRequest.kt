package com.abacatepay.model.request

import com.abacatepay.model.enums.PaymentFrequency
import com.abacatepay.model.enums.PaymentMethod
import kotlinx.serialization.Serializable


@Serializable
data class BillingRequest(
    val frequency: PaymentFrequency,
    val methods: List<PaymentMethod>,
    val products: List<ProductRequest>,
    val returnUrl: String,
    val completionUrl: String,
    val customerId: String? = null,
    val customer: CustomerRequest? = null,
    val allowCoupons: Boolean = false,
    val coupons: List<String> = emptyList(),
    val externalId: String? = null,
    val metadata: Map<String, String> = emptyMap(),
)

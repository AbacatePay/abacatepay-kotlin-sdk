package com.abacatepay.model.response

import com.abacatepay.model.enums.BillingStatus
import com.abacatepay.model.enums.PaymentFrequency
import com.abacatepay.model.enums.PaymentMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable
data class BillingResponse(
    val id: String,
    val url: String,
    val status: BillingStatus,
    val devMode: Boolean,
    @SerialName("methods")
    val paymentMethods: List<PaymentMethod>,
    val products: List<ProductResponse>,
    val frequency: PaymentFrequency,
    @Deprecated("this field is deprecated and may be removed in a future release.")
    val amount: Int,
    val nextBilling: String,
    val customer: CustomerResponse,
    val allowCoupons: Boolean,
    val coupons: List<String>,
)
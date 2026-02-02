package com.abacatepay.model.response

import com.abacatepay.model.enums.BillingStatus
import com.abacatepay.model.enums.PaymentFrequency
import com.abacatepay.model.enums.PaymentMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * Represents a response from a billing operation.
 *
 * @property id Unique identifier for the billing operation.
 * @property url The URL associated with the billing operation, potentially for review or follow-up.
 * @property status The current status of the billing operation (e.g., PENDING, PAID, CANCELLED).
 * @property devMode Indicates whether the operation was conducted in development mode.
 * @property paymentMethods A list of payment methods available within the billing operation (e.g., CARD, PIX).
 * @property products List of products involved in this billing operation.
 * @property frequency Indicates the payment frequency, such as one-time or recurring payments.
 * @property amount Deprecated field indicating the amount associated with the billing operation.
 * @property nextBilling A string representing the next billing date or cycle.
 * @property customer Metadata and details about the customer associated with the billing operation.
 * @property allowCoupons Specifies if coupons are allowed for this billing operation.
 * @property coupons A list of coupon codes applied or available for use with the billing operation.
 */
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
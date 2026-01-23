package com.abacatepay.model.request

import com.abacatepay.model.enums.PaymentFrequency
import com.abacatepay.model.enums.PaymentMethod
import kotlinx.serialization.Serializable


/**
 * Represents a request for creating a billing operation in the AbacatePay.
 *
 * @property frequency The frequency of the billing operation
 * @property methods The list of payment methods supported for the billing operation.
 * @property products The list of products involved in the billing operation, including their quantity and pricing details.
 * @property returnUrl The URL to which the customer should be redirected after completing the payment process.
 * @property completionUrl The URL to notify upon successful completion of the billing process.
 * @property customerId An optional identifier for the customer, if available.
 * @property customer An optional customer information payload containing personal details such as name, email, and tax ID.
 * @property allowCoupons A flag indicating whether coupon usage is allowed for the billing operation.
 * @property coupons A list of coupon codes applicable to the billing operation.
 * @property externalId An optional external identifier for the billing operation, useful for system integrations.
 * @property metadata A map of additional key-value metadata associated with the billing operation.
 */
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

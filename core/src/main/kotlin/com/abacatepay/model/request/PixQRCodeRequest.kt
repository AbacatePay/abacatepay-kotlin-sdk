package com.abacatepay.model.request

import kotlinx.serialization.Serializable

/**
 * Represents a request for generating a PIX QR code in the AbacatePay system.
 *
 * @property amount The monetary amount to be associated with the generated QR code, expressed in cents.
 * @property expiresIn The time duration (in seconds) after which the QR code will expire and no longer be valid.
 * @property description An optional description or note associated with the QR code and its intended purpose.
 * @property customer Optional customer details encapsulated within a [CustomerRequest] object, providing information such as name and contact data.
 * @property metadata A map containing additional optional key-value pairs for referencing metadata related to the PIX QR code.
 */
@Serializable
data class PixQRCodeRequest(
    val amount: Int,
    val expiresIn: Int,
    val description: String?,
    val customer: CustomerRequest? = null,
    val metadata: Map<String, String> = emptyMap(),
)
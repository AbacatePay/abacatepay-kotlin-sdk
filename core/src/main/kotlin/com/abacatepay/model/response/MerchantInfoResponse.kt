package com.abacatepay.model.response

import kotlinx.serialization.Serializable

/**
 * Represents the response structure containing merchant-related information.
 *
 * @property name The name of the merchant.
 * @property website The website URL associated with the merchant.
 * @property createdAt The timestamp indicating when the merchant was created.
 */
@Serializable
data class MerchantInfoResponse(
    val name: String,
    val website: String,
    val createdAt: String,
)

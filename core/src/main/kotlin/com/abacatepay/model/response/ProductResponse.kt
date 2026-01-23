package com.abacatepay.model.response

import kotlinx.serialization.Serializable

/**
 * Represents the response structure for a product.
 *
 * @property id Unique identifier for the product.
 * @property externalId An identifier for the product provided by external systems or integrations.
 * @property quantity The quantity of the product associated with the response, typically indicating availability or allocation.
 */
@Serializable
data class ProductResponse(
    val id: String,
    val externalId: String,
    val quantity: Int,
)
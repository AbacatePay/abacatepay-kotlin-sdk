package com.abacatepay.model.request

import kotlinx.serialization.Serializable

/**
 * Represents a product request within a transactional or billing operation.
 *
 * @property externalId A unique identifier for the product, used for tracking and integration with external systems.
 * @property name The name of the product being requested.
 * @property quantity The quantity of the product to be included in the request.
 * @property price The price of a single unit of the product, expressed in cents.
 * @property description An optional description of the product, providing additional details or context.
 */
@Serializable
data class ProductRequest(
    val externalId: String,

    val name: String,

    val quantity: Int,

    val price: Int,

    val description: String?
)
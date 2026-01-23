package com.abacatepay.model.response

import kotlinx.serialization.Serializable

/**
 * Represents the generic response structure returned by the AbacatePay API.
 *
 * This class encapsulates both successful and error responses from the API.
 * The `data` property contains the successful result of the operation, while the `error` property,
 * if populated, provides details about the error encountered.
 *
 * @param T The type of the data returned in the response.
 *
 * @property data The payload of the response, or `null` if the operation failed.
 * @property error A string detailing the error, or `null` if the operation was successful.
 */
@Serializable
data class AbacatePayResponse<T>(
    val success: Boolean? = null,
    val data: T? = null,
    val error: String? = null
)

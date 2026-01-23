package com.abacatepay.model.response

import com.abacatepay.model.request.CustomerRequest
import kotlinx.serialization.Serializable

/**
 * Represents a response containing customer-related information.
 *
 * @property id Unique identifier for the customer.
 * @property metadata Contains detailed information about the customer, such as name,
 * contact details, and other essential attributes required for processing.
 */
@Serializable
data class CustomerResponse(
    val id: String,
    val metadata: CustomerRequest
)
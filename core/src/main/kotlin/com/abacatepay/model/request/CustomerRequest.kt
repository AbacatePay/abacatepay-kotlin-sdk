package com.abacatepay.model.request

import kotlinx.serialization.Serializable

/**
 * Represents the customer's information required for processing requests.
 *
 * @property name Full name of the customer.
 * @property cellphone Customer's cellphone number.
 * @property email Customer's email address.
 * @property taxId Customer's tax identification number.
 * @property zipCode Customer's zip code.
 */
@Serializable
data class CustomerRequest(
    val name: String,
    val cellphone: String,
    val email: String,
    val taxId: String,
    val zipCode: String? = null,
)

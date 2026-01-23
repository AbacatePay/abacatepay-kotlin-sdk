package com.abacatepay.model.response

import kotlinx.serialization.Serializable

/**
 * Represents the response structure for balance information.
 *
 * @property available The amount of funds available for use.
 * @property pending The amount of funds currently pending or in processing.
 * @property blocked The amount of funds that are blocked or restricted.
 */
@Serializable
data class BalanceResponse(
    val available: Int,
    val pending: Int,
    val blocked: Int,
)

/**
 * Represents the response structure for a store.
 *
 * @property id The unique identifier for the store.
 * @property name The name of the store.
 * @property balance The balance information associated with the store, including details about available, pending,
 * and blocked amounts.
 */
@Serializable
data class StoreResponse(
    val id: String,
    val name: String,
    val balance: BalanceResponse
)
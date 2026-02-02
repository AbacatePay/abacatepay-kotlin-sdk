package com.abacatepay.model.response

import kotlinx.serialization.Serializable


/**
 * Represents the response structure containing Monthly Recurring Revenue (MRR) and subscription information.
 *
 * @property mrr The total Monthly Recurring Revenue calculated based on active subscriptions.
 * @property totalActiveSubscriptions The total number of active subscriptions contributing to the MRR value.
 */
@Serializable
data class MRRResponse(
    val mrr: Int,
    val totalActiveSubscriptions: Int,
)

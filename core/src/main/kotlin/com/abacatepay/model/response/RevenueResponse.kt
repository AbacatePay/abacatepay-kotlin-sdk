package com.abacatepay.model.response

import kotlinx.serialization.Serializable

/**
 * Represents a summary of transactions with aggregated data.
 *
 * @property amount The total monetary value of all transactions, typically represented in cents.
 * @property count The total number of individual transactions included in the summary.
 */
@Serializable
data class TransactionSummaryResponse(
    val amount: Int,
    val count: Int,
)

/**
 * Represents a response containing a mapping of transaction summaries per day.
 *
 * @property transactionPerDay A map where the keys are strings representing specific dates (in YYYY-MM-DD format)
 * and the values are instances of [TransactionSummaryResponse], which provide a summary
 * of transactions for the respective date.
 */
@Serializable
data class TransactionPerDayResponse(
    val transactionPerDay: Map<String, TransactionSummaryResponse>,
)

/**
 * Represents the response structure containing revenue-related details.
 *
 * @property totalRevenue The total revenue amount represented as an integer.
 * @property totalTransactions The total number of transactions counted.
 * @property transactionsPerDay A detailed breakdown of transaction summaries organized by day.
 */
@Serializable
data class RevenueResponse(
    val totalRevenue: Int,
    val totalTransactions: Int,
    val transactionsPerDay: TransactionPerDayResponse,
)

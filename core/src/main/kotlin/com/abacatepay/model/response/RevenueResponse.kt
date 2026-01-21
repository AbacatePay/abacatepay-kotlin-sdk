package com.abacatepay.model.response

import kotlinx.serialization.Serializable

@Serializable
data class TransactionSummaryResponse(
    val amount: Int,
    val count: Int,
)

@Serializable
data class TransactionPerDayResponse(
    val transactionPerDay: Map<String, TransactionSummaryResponse>,
)


@Serializable
data class RevenueResponse(
    val totalRevenue: Int,
    val totalTransactions: Int,
    val transactionsPerDay: TransactionPerDayResponse,
)

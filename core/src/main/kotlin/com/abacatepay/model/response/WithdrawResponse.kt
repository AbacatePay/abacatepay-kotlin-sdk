package com.abacatepay.model.response

import com.abacatepay.model.enums.TransactionKind
import com.abacatepay.model.enums.WithdrawStatus

data class WithdrawResponse(
    val id: String,
    val status: WithdrawStatus,
    val devMode: Boolean,
    val receiptUrl: String,
    val kind: TransactionKind,
    val amount: Int,
    val platformFee: Int,
    val createdAt: String,
    val updatedAt: String,
    val externalId: String?,
)

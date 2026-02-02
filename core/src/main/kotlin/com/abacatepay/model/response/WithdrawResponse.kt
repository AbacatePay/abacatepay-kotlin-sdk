package com.abacatepay.model.response

import com.abacatepay.model.enums.TransactionKind
import com.abacatepay.model.enums.WithdrawStatus
import kotlinx.serialization.Serializable

/**
 * Represents the response structure for a withdrawal operation.
 *
 * @property id The unique identifier for the withdrawal transaction.
 * @property status The current status of the withdrawal
 * @property devMode Indicates whether the withdrawal operation is being executed in development mode.
 * @property receiptUrl The URL of the receipt associated with the withdrawal transaction.
 * @property kind The type of transaction being performed, such as PAYMENT or WITHDRAW.
 * @property amount The amount of funds involved in the withdrawal represented in cents.
 * @property platformFee The fee charged by the platform for processing the withdrawal, represented in cents.
 * @property createdAt The timestamp indicating when the withdrawal transaction was created.
 * @property updatedAt The timestamp indicating when the withdrawal transaction was last updated.
 * @property externalId An optional identifier for the withdrawal provided by external systems or integrations.
 */
@Serializable
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

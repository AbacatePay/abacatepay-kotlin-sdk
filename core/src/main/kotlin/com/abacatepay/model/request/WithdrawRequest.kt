package com.abacatepay.model.request

import com.abacatepay.model.enums.WithdrawMethod
import kotlinx.serialization.Serializable

/**
 * Represents a request for initiating a withdrawal operation.
 *
 * @property externalId A unique identifier for the withdrawal request, used for tracking and integration purposes.
 * @property method The withdrawal method to be used. Defaults to [WithdrawMethod.PIX].
 * @property amount The amount to be withdrawn, expressed in cents.
 * @property pix Contains the PIX details required for processing the withdrawal request.
 * @property description An optional description or note associated with the withdrawal request.
 */
@Serializable
data class WithdrawRequest(
    val externalId: String,
    val method: WithdrawMethod = WithdrawMethod.PIX,
    val amount: Int,
    val pix: PixRequest,
    val description: String? = null,
)
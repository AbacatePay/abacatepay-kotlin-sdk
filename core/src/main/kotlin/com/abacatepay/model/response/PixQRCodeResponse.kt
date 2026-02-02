package com.abacatepay.model.response

import com.abacatepay.model.enums.PixQRCodeStatus
import kotlinx.serialization.Serializable

/**
 * Represents the response structure for a Pix QR Code transaction.
 *
 * @property id Unique identifier of the Pix QR Code transaction.
 * @property amount The monetary value associated with the transaction, in cents.
 * @property status The current status of the Pix QR Code transaction (e.g., PENDING, PAID, EXPIRED).
 * @property devMode Indicates whether the Pix QR Code was created in development mode.
 * @property brCode The raw Brazilian Payment System (BR Code) string for the Pix QR Code.
 * @property brCodeBase64 The base64-encoded representation of the BR Code for easy sharing or display.
 * @property platformFee The fee amount charged by the platform for processing this transaction, in cents.
 * @property createdAt The timestamp indicating when the Pix QR Code transaction was created.
 * @property updatedAt The timestamp indicating the last update to the Pix QR Code transaction.
 * @property expiresAt The timestamp indicating when the Pix QR Code transaction expires.
 */
@Serializable
data class PixQRCodeResponse(
    val id: String,
    val amount: Int,
    val status: PixQRCodeStatus,
    val devMode: Boolean,
    val brCode: String,
    val brCodeBase64: String,
    val platformFee: Int,
    val createdAt: String,
    val updatedAt: String,
    val expiresAt: String,
)

@Serializable
data class PixQRCodeStatusResponse(
    val status: PixQRCodeStatus,
    val expiresAt: String
)
package com.abacatepay.model.response

import com.abacatepay.model.enums.PixQRCodeStatus
import kotlinx.serialization.Serializable

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
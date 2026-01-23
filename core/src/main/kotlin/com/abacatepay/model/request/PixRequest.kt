package com.abacatepay.model.request

import com.abacatepay.model.enums.PixKeyType
import kotlinx.serialization.Serializable

/**
 * Represents the data structure required to identify a PIX key for transactions in the AbacatePay system.
 *
 * @property type The type of the PIX key, such as CPF, CNPJ, PHONE, EMAIL, RANDOM, or BR_CODE.
 *                This determines the format and purpose of the key.
 * @property key The actual PIX key value associated with the type, used to uniquely identify a user or entity
 *               in the PIX payment system.
 */
@Serializable
data class PixRequest(
    val type: PixKeyType,
    val key: String,
)
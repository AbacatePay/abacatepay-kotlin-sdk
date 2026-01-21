package com.abacatepay.model.enums

import kotlinx.serialization.Serializable

@Serializable
enum class PixKeyType{
    CPF,
    CNPJ,
    PHONE,
    EMAIL,
    RANDOM,
    BR_CODE
}
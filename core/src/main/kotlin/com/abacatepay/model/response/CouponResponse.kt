package com.abacatepay.model.response

import com.abacatepay.model.enums.CouponDiscountKind
import com.abacatepay.model.enums.CouponStatus
import kotlinx.serialization.Serializable


@Serializable
data class CouponResponse(
    val id: String,
    val discountKind: CouponDiscountKind,
    val discount: Int,
    val status: CouponStatus,
    val createdAt: String,
    val updatedAt: String,
    val notes: String?,
    val maxRedeems: Int = 10,
    val redeemsCount: Int,
    val devMode: Boolean,
    val metadata: Map<String, String>,
)
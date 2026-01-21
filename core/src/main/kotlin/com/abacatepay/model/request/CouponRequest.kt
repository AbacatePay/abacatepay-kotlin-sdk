package com.abacatepay.model.request

import com.abacatepay.model.enums.CouponDiscountKind
import kotlinx.serialization.Serializable


@Serializable
data class Coupon(
    val code: String,
    val discountKind: CouponDiscountKind,
    val discount: Int,
    val notes: String? = null,
    val maxRedeems: Int = -1,
    val metadata: Map<String, String> = emptyMap(),
)

@Serializable
data class CouponRequest(
    val data: Coupon,
)
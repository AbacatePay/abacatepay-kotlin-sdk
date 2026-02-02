package com.abacatepay.model.request

import com.abacatepay.model.enums.CouponDiscountKind
import kotlinx.serialization.Serializable

/**
 * Represents a discount coupon that can be applied to transactions.
 *
 * @property code The unique code that identifies the coupon.
 * @property discountKind The type of discount provided by the coupon.
 * @property discount The value of the discount.
 * @property notes Optional notes or additional information about the coupon.
 * @property maxRedeems The maximum number of times the coupon can be redeemed. If set to -1, there is no limit.
 * @property metadata Additional metadata associated with the coupon, represented as key-value pairs.
 */
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
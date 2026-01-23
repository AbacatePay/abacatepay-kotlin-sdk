package com.abacatepay.model.response

import com.abacatepay.model.enums.CouponDiscountKind
import com.abacatepay.model.enums.CouponStatus
import kotlinx.serialization.Serializable


/**
 * Represents the response structure for a coupon.
 *
 * @property id Unique identifier for the coupon.
 * @property discountKind The type of discount applied (e.g., fixed amount or percentage).
 * @property discount The value of the discount associated with the coupon.
 * @property status The current status of the coupon (e.g., active, inactive, or expired).
 * @property createdAt The timestamp indicating when the coupon was created.
 * @property updatedAt The timestamp indicating the last update to the coupon.
 * @property notes Optional additional information or remarks about the coupon.
 * @property maxRedeems The maximum number of times the coupon can be redeemed. Defaults to 10.
 * @property redeemsCount The count of how many times the coupon has been redeemed.
 * @property devMode Indicates whether the coupon is enabled in development mode.
 * @property metadata A map of key-value pairs containing additional metadata associated with the coupon.
 */
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
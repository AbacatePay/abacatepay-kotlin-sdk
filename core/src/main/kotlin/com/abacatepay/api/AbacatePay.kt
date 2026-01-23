package com.abacatepay.api

import com.abacatepay.model.request.BillingRequest
import com.abacatepay.model.request.CouponRequest
import com.abacatepay.model.request.CustomerRequest
import com.abacatepay.model.request.PixQRCodeRequest
import com.abacatepay.model.request.SimulatePaymentRequest
import com.abacatepay.model.request.WithdrawRequest
import com.abacatepay.model.response.AbacatePayResponse
import com.abacatepay.model.response.BillingResponse
import com.abacatepay.model.response.CouponResponse
import com.abacatepay.model.response.CustomerResponse
import com.abacatepay.model.response.MRRResponse
import com.abacatepay.model.response.MerchantInfoResponse
import com.abacatepay.model.response.PixQRCodeResponse
import com.abacatepay.model.response.PixQRCodeStatusResponse
import com.abacatepay.model.response.RevenueResponse
import com.abacatepay.model.response.StoreResponse
import com.abacatepay.model.response.WithdrawResponse

/**
 * AbacatePay represents the interface structure for interacting with the AbacatePay API.
 * This interface provides various subinterfaces to handle specific domains such as customers,
 * billing, PIX QR Codes, coupons, withdrawals, store information, and revenue-related data.
 */
interface AbacatePay {

    /**
     * Provides operations to manage customers in the AbacatePay API.
     *
     * @see <a href="https://docs.abacatepay.com/pages/client/reference">Customer</a>
     */
    interface Customer {

        suspend fun create(body: CustomerRequest): Result<AbacatePayResponse<CustomerResponse>>

        suspend fun list() : Result<AbacatePayResponse<List<CustomerResponse>>>

    }

    /**
     * Provides functionality for interacting with the billing operations of the AbacatePay API.
     *
     * @see <a href="https://docs.abacatepay.com/pages/payment/reference">Billing</a>
     */
    interface Billing {

        suspend fun create(body: BillingRequest): Result<AbacatePayResponse<BillingResponse>>

        suspend fun list(): Result<AbacatePayResponse<List<BillingResponse>>>
    }

    /**
     * Provides functionality for managing Pix QR Code operations in the AbacatePay API.
     *
     * @see <a href="https://docs.abacatepay.com/pages/pix-qrcode/reference">Pix QR Code</a>
     */
    interface PixQRCode {
        suspend fun create(body: PixQRCodeRequest) : Result<AbacatePayResponse<PixQRCodeResponse>>

        suspend fun simulatePayment(qrCodeId: String, body: SimulatePaymentRequest? = null) : Result<AbacatePayResponse<PixQRCodeResponse>>

        suspend fun check(qrCodeId: String) : Result<AbacatePayResponse<PixQRCodeStatusResponse>>
    }

    /**
     * Provides functionality for managing coupon operations in the AbacatePay API.
     *
     * @see <a href="https://docs.abacatepay.com/pages/coupon/reference">Coupon</a>
     */
    interface Coupon {

        suspend fun create(body: CouponRequest): Result<AbacatePayResponse<CouponResponse>>

        suspend fun list(): Result<AbacatePayResponse<List<CouponResponse>>>
    }

    /**
     * Provides functionality to interact with the AbacatePay API for managing withdrawal operations.
     *
     * @see <a href="https://docs.abacatepay.com/pages/withdraw/reference">Withdraw</a>
     */
    interface Withdraw {
        suspend fun create(body: WithdrawRequest): Result<AbacatePayResponse<WithdrawResponse>>

        suspend fun list(): Result<AbacatePayResponse<List<WithdrawResponse>>>

        suspend fun get(externalId: String) : Result<AbacatePayResponse<WithdrawResponse>>
    }

    /**
     * Provides functionality for managing store operations in the AbacatePay API.
     *
     * @see <a href="https://docs.abacatepay.com/pages/store/reference">Store</a>
     */
    interface Store {
        suspend fun get() : Result<AbacatePayResponse<StoreResponse>>
    }


    /**
     * Provides functionality for interacting with the Public Monthly Recurring Revenue (MRR)
     * operations in the AbacatePay API.
     *
     * @see <a href="https://docs.abacatepay.com/pages/trustMRR/reference">Public MRR</a>
     */
    interface PublicMRR {
        suspend fun getMRR() : Result<AbacatePayResponse<MRRResponse>>
        suspend fun getMerchantInfo() : Result<AbacatePayResponse<MerchantInfoResponse>>
        suspend fun getRevenue(startDate: String, endDate: String) : Result<AbacatePayResponse<RevenueResponse>>
    }

}
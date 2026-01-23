package com.abacatepay.internal

import io.ktor.http.Headers
import io.ktor.http.headers

internal object Constants {
    const val ABACATEPAY_API_HOST = "https://api.abacatepay.com"
    const val SDK_VERSION = "0.0.5"
    const val DEFAULT_USER_AGENT = "kotlin-sdk $SDK_VERSION"
}
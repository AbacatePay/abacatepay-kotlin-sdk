package com.abacatepay.exception

/**
 * Represents a generic exception thrown by the AbacatePay SDK.
 *
 * This exception is used to encapsulate errors encountered during API interactions,
 * including both server-side and client-side issues. It contains a descriptive error
 * message along with the associated HTTP status code.
 *
 * @constructor Creates an instance of `AbacatePayGenericException` with the specified error message and status code.
 * @param message A descriptive message detailing the cause of the exception.
 * @param status The HTTP status code associated with the error.
 */
class AbacatePayGenericException(message: String, val status: Int) : RuntimeException(message)
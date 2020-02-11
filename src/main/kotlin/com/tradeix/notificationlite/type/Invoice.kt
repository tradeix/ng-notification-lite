package com.tradeix.notificationlite.type

import java.math.BigDecimal
import java.util.*

class Invoice {
    var invoiceId: String? = null
    var version: Int? = null
    var buyerRef: String? = null
    var supplierRef: String? = null
    var invoiceNumber: String? = null
    var extraData: Map<String, String>? = null
    var createdAt: String? = null
    var updatedAt: String? = null
    var currency: Currency? = null
    var amount: BigDecimal? = null

    constructor() {}

    constructor(
            invoiceId: String?,
            version: Int?,
            buyerRef: String?,
            supplierRef: String?,
            invoiceNumber: String?,
            extraData: Map<String, String>?,
            createdAt: String?,
            updatedAt: String?,
            currency: Currency?,
            amount: BigDecimal? ) {

        this.invoiceId = invoiceId
        this.version = version
        this.buyerRef = buyerRef
        this.supplierRef = supplierRef
        this.invoiceNumber = invoiceNumber
        this.extraData = extraData
        this.createdAt = createdAt
        this.updatedAt = updatedAt
        this.currency = currency
        this.amount = amount
    }

    override fun toString(): String {
        return "Event => " +
                "invoiceId: $invoiceId ; " +
                "version : $version; " +
                "buyerRef : $buyerRef ;" +
                "supplierRef : $supplierRef ;" +
                "invoiceNumber : $invoiceNumber ;" +
                "extraData : $extraData ;" +
                "createdAt : $createdAt ;" +
                "updatedAt : $updatedAt ;" +
                "currency : $currency ;" +
                "amount : $amount;"
    }
}
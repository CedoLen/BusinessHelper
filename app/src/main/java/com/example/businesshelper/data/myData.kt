package com.example.businesshelper.data

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Status(
    val id: String?="",
    val title:String?=""
)
@IgnoreExtraProperties
data class TypeInCome(
    val id: String?="",
    val title:String?=""
)
@IgnoreExtraProperties
data class TypeExpenses(
    val id: String?="",
    val title:String?=""
)

data class fullFinance(
    var title: String?="",
    var type:String?="",
    var futureSum:Long=0,
    var currentSum:Long=0
)

@IgnoreExtraProperties
data class Expenses(
    var type:String?="",
    var date:String?="",
    var sum:Long=0
)

@IgnoreExtraProperties
data class InCome(
    var type:String?="",
    var date:String?="",
    var sum:Long=0
)

@IgnoreExtraProperties
data class Account(
    val id:String?="",
    val email: String?="",
    val password:String?="",
    val fullName:String?="",
    val company:String?="",
    val phone:String?="",
    val inn:String?="",
    val kpp:String?="",
    val legalAddress:String?="",
    val actualAddress:String?="",
)
@IgnoreExtraProperties
data class Order(
    var id: String?="",
    var status: String?="",
    var dateRegistration:String?="",
    var dateIssue:String?="",
    var deliveryAddress:String?="",
    var priceDelivery:Long?=0,
    var totalPrice:Long?=0,
    var counterCompany: String?="",
    var counterparty: Counterparty?=null,
    var basket:Map<String,CountProducts>?=null
)
data class CountProducts(
    var product: Product?=null,
    var count:Int?=0
)

@IgnoreExtraProperties
data class Counterparty(
    var id:String?="",
    var email: String?="",
    var company:String?="",
    var phone:String?="",
    var inn:String?="",
    var kpp:String?="",
    var legalAddress:String?="",
    var actualAddress:String?=""
)
@IgnoreExtraProperties
data class Product(
    val id: String?="",
    var title:String?="",
    var price:Long?=0,
    var unit:String?="",
    var rawMaterials:Int?=0,
    var salary:Int?=0,
    var socialNeeds:Int?=0,
    var depreciation:Int?=0,
    var taxes:Int?=0,
    var storage:Int?=0,
    var other:Int?=0,
    val dateRegistration:String?=""
)



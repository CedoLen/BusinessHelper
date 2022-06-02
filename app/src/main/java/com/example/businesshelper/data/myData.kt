package com.example.businesshelper.data

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import java.util.ArrayList

@IgnoreExtraProperties
data class Status(
    var title:String?=""
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
    val dateRegistration:String?="",
    val dateIssue:String?="",
    val deliveryAddress:String?="",
    val priceDelivery:Long?=0,
    val totalPrice:Long?=0
)
@IgnoreExtraProperties
data class Counterparty(
    val email: String?="",
    val company:String?="",
    val phone:String?="",
    val inn:String?="",
    val kpp:String?="",
    val legalAddress:String?="",
    val actualAddress:String?=""
)
@IgnoreExtraProperties
data class Product(
    val title:String?="",
    val price:Long?=0,
    val unit:String?="",
    val rawMaterials:Double,
    val salary:Double,
    val socialNeeds:Double,
    val depreciation:Double,
    val taxes:Double,
    val storage:Double,
    val other:Double,
    val dateRegistration:String?=""
)
@IgnoreExtraProperties
data class ProductOrder(
val products:List<Product>?=null,
val orders:List<Order>?=null,
val count:Long?=0
)

fun getRandomString(length: Int) : String {
    val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
    return (1..length)
        .map { charset.random() }
        .joinToString("")
}

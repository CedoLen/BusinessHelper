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
    val title:String?="",
    val price:Long?=0,
    val unit:String?="",
    val rawMaterials:Double?=0.0,
    val salary:Double?=0.0,
    val socialNeeds:Double?=0.0,
    val depreciation:Double?=0.0,
    val taxes:Double?=0.0,
    val storage:Double?=0.0,
    val other:Double?=0.0,
    val dateRegistration:String?=""
)
@IgnoreExtraProperties
data class ProductOrder(
val products:List<Product>?=null,
val orders:List<Order>?=null,
val count:Long?=0
)

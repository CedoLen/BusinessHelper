package com.example.businesshelper.data.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.HashMap

data class Valut(
    var ID:String?="",
    var NumCode:String?="",
    var CharCode:String?="",
    var Nominal:Int?=0,
    var Name:String?="",
    var Value:String?="",
    var Previous:String?=""
    )

data class Currencies(
    var Date:String?="",
    var PreviousDate:String?="",
    var PreviousURL:String?="",
    var Timestamp:String?="",

    @SerializedName("Valute")
    @Expose
    var Valute:HashMap<String,Valut>?=null
)

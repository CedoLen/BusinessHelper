package com.example.businesshelper.data.api

data class Currency(val ID:String, val NumCode:String, val CharCode:String, val Nominal:Int, val Name:String, val Value:Double, val Previous:Double)
data class Currencies(val success:Boolean, val data:List<Currency>)

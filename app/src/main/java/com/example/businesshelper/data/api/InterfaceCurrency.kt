package com.example.businesshelper.data.api

import retrofit2.Call
import retrofit2.http.GET

interface InterfaceCurrency {
    @GET("daily_json.js")
    fun getCurrency(): Call<Currencies>
}
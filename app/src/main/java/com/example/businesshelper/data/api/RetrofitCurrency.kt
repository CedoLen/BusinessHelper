package com.example.businesshelper.data.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitCurrency {
    var gson = GsonBuilder()
        .setLenient()
        .create()

    fun getRetrofit()= Retrofit.Builder()
        .baseUrl("https://www.cbr-xml-daily.ru/daily_json.js/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}
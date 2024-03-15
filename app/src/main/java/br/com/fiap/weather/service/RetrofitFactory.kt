package br.com.fiap.weather.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {
    private val URL = "https://goweather.herokuapp.com/weather/"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getCityService(): CityService {
        return retrofitFactory.create(CityService::class.java)
    }
}
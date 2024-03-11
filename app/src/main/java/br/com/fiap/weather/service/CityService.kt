package br.com.fiap.weather.service

import br.com.fiap.weather.model.City
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CityService {
    //    https://goweather.herokuapp.com/weather/

    //    https://goweather.herokuapp.com/weather/fortaleza
    @GET("{name}")
    fun getCityByName(@Path("name") name: String): Call<City>
}
package br.com.fiap.weather.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import br.com.fiap.weather.R
import br.com.fiap.weather.model.City
import br.com.fiap.weather.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CityScreen(
    navController: NavController,
    name: String
) {
    var cityInfoState by remember {
        mutableStateOf<City?>(null)
    }

    val call = RetrofitFactory().getCityService().getCityByName(name)
    call.enqueue(object: Callback<City> {
        override fun onResponse(
            call: Call<City>,
            response: Response<City>
        ) {
            val city = response.body()
            city?.let {
                // Atualiza as informações da cidade
                cityInfoState = it
            }
        }

        override fun onFailure(
            call: Call<City>,
            t: Throwable
        ) {
            Log.e("API Error", "Erro ao chamar a API: ${t.message}", t)
        }

    })
    Column {
        Button(onClick = {
            navController.navigate("home")
        }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "ícone para voltar"
            )
        }
        Text(text = "Info for $name")
            Column {

            }
        // Exibindo as informações da cidade se estiverem disponíveis
        cityInfoState?.let { city ->
            CityItem(city = city)
        }
    }
}

@Composable
fun CityItem(city: City) {
    Card {
        Column {
            Text(text = "Temperature: ${city.temperature}")
            Text(text = "Description: ${city.description}")
        }
    }
}
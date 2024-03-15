package br.com.fiap.weather.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    var showError by remember {
        mutableStateOf(false)
    }

    val call = RetrofitFactory().getCityService().getCityByName(name)
    call.enqueue(object: Callback<City> {
        override fun onResponse(
            call: Call<City>,
            response: Response<City>
        ) {
            if (response.isSuccessful) {
                val city = response.body()
                city?.let {
                    // Verifica se os campos de texto estão vazios
                    if (it.temperature.isEmpty() || it.description.isEmpty()) {
                        showError = true
                    } else {
                        // Atualiza as informações da cidade
                        cityInfoState = it
                    }
                }
            } else {
                // Se a resposta não for bem-sucedida, define o estado de exibição de erro como true
                showError = true
                Log.e("API Error", "Erro ao chamar a API: ${response.code()}")
            }
        }

        override fun onFailure(
            call: Call<City>,
            t: Throwable
        ) {
            Log.e("API Error", "Erro ao chamar a API: ${t.message}", t)
            showError = true
        }

    })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(35.dp)
    ) {
        Button(
            onClick = { navController.navigate("home") },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color(0xFF00BCD4),
                containerColor = Color(0xFFF9FAFA)
            ),
            modifier = Modifier.width(40.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "ícone para voltar"
            )
        }
        Spacer(modifier = Modifier.height(200.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = name,
                color = Color(0xFFF9FAFA),
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.height(15.dp))
            if (showError) {
                // Exibe a mensagem de erro apenas se o estado showError for true
                Text(
                    text = "⚠️ Erro ao carregar informações. \nPor favor, tente novamente com outro local.",
                    color = Color(0xFFFDFCFC),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            } else {
                // Verifica se cityInfoState não é nulo antes de exibir as informações da cidade
                cityInfoState?.let { city ->
                    CityInfo(city = city)
                }
            }
        }
    }
}

@Composable
fun CityInfo(city: City) {
    when (city.description) {
        "Sunny" -> city.description = "Ensolarado ☀️"
        "Clear" -> city.description = "Limpo 🌤️"
        "Partly clear" -> city.description = "Parcialmente limpo ⛅"
        "Cloudy" -> city.description = "Nublado ☁️"
        "Partly cloudy" -> city.description = "Parcialmente nublado 🌥️"
        "Patchy rain nearby" -> city.description = "Chuva nas proximidades 🌧️"
        "Rainny" -> city.description = "Chuva ⛈️"
        "Raining" -> city.description = "Chuva ⛈️"
        "Rain" -> city.description = "Chuva ⛈️"
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = city.temperature,
            color = Color(0xFFF9FAFA),
            fontSize = 80.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = city.description,
            color = Color(0xFFF9FAFA),
            fontSize = 22.sp
        )
    }
}
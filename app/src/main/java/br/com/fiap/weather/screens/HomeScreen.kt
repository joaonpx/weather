package br.com.fiap.weather.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    var cityName by remember {
        mutableStateOf("")
    }
    Column {
        Text(text = "Digite uma cidade para buscar o clima")
        OutlinedTextField(
            value = cityName,
            onValueChange = {
                cityName = it
            },
            label = {
                Text(text = "Nome da cidade")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words
            )
        )
        Button(onClick = {
            navController.navigate("city/$cityName")
        }) {
            Text(text = "Buscar")
        }
    }

}
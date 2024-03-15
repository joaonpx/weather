package br.com.fiap.weather.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    var cityName by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize().padding(35.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = br.com.fiap.weather.R.drawable.baseline_cloud_24),
            contentDescription = "Logo"
        )
        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = "Digite para buscar o clima",
            color = Color(0xFFF9FAFA),
            fontSize = 19.sp
        )
        Spacer(modifier = Modifier.height(15.dp))
        OutlinedTextField(
            value = cityName,
            onValueChange = {
                cityName = it
            },
            label = {
                Text(text = "País, estado, cidade", color = Color(0xFFF9FAFA))
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFF9FAFA),
                unfocusedBorderColor = Color(0xFFF9FAFA),
                focusedLabelColor = Color(0xFFF9FAFA),
                unfocusedLabelColor = Color(0xFFF9FAFA)
            )
        )
        Spacer(modifier = Modifier.height(15.dp))
        Button(
            onClick = { navController.navigate("city/$cityName") },
            colors = ButtonDefaults.buttonColors(contentColor = Color(0xFF198686), containerColor = Color(0xFFF9FAFA))
        ) {
            Text(text = "Buscar", fontSize = 16.sp)
        }
    }

}
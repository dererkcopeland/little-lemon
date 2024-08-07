package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Onboarding () {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column{
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.padding(10.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        )
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .align(CenterHorizontally),
            color = colorResource(id = R.color.green)) {
            Text(
                modifier = Modifier.padding(50.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = Color.White,
                text = "Let's get to know you")
        }

        Text(
            modifier = Modifier.padding(10.dp, 20.dp, 0.dp, 0.dp),
            text = "Personal Information:",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,)
        OutlinedTextField(
            value = firstName,
            modifier = Modifier.padding(10.dp)
                .align(CenterHorizontally)
                .fillMaxWidth(),
            onValueChange = { firstName = it },
            label = { Text("First Name")})
        OutlinedTextField(
            value = lastName,
            modifier = Modifier.padding(10.dp)
                .align(CenterHorizontally)
                .fillMaxWidth(),
            onValueChange = { lastName = it },
            label = { Text("Last Name")})
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.padding(10.dp)
                .fillMaxWidth()
                .align(CenterHorizontally),
            label = { Text("Email")})
        Button(
            modifier = Modifier.padding(10.dp)
                .align(CenterHorizontally)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.yellow),
                contentColor = colorResource(id = R.color.green)
            ),
            onClick = { /*TODO*/ }) {
            Text(text = "Register")
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnboardingPreview() {
    Onboarding()
}
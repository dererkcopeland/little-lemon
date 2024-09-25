package com.example.littlelemon

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun Home(navController: NavHostController){
    Text(text = "Home")
    Button(onClick = {
        navController.navigate(Profile.route)
    }) {
        Text(text = "Profile")
    }
}
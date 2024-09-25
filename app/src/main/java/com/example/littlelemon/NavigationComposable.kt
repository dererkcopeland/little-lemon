package com.example.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController){

    val sharedPreferences = LocalContext.current.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
    val email = sharedPreferences.getString("email", "")
    val firstName = sharedPreferences.getString("firstName", "")
    val lastName = sharedPreferences.getString("lastName", "")
    val isRegistered = !email.isNullOrBlank() && !firstName.isNullOrBlank() && !lastName.isNullOrBlank()

    var startDestination: String = Home.route

    if(!isRegistered){
        startDestination = Onboarding.route
    }

    NavHost(navController = navController, startDestination = startDestination){
        composable(Home.route){
            Home(navController)
        }
        composable(Onboarding.route){
            Onboarding(navController)
        }
        composable(Profile.route){
            Profile(navController)
        }
    }
}
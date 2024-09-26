package com.example.littlelemon

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    private val client: HttpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

//    private val menuItemsLiveData = MutableLiveData<List<MenuItemRoom>>()

    private val database by lazy {
        Room.databaseBuilder(applicationContext, MenuDatabase::class.java, "menu.db").build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        setContent {
            LittleLemonTheme {

                LaunchedEffect(Unit) {
                    // Retrieve menu items from API and save to database
                    if (database.menuDao().getAllMenuItems().value.isNullOrEmpty()) {
                        retrieveMenu()
                    }
                }
                // Observe menu items from database
                val menuItems: List<MenuItemRoom> by database.menuDao().getAllMenuItems().observeAsState(emptyList())
                Log.d("OnCreate", "Menu items size: ${menuItems.size}")
                // Pass menu items to Navigation composable
                Navigation(navController = rememberNavController(), menuItems)
            }
        }
    }

    private suspend fun retrieveMenu() {

        // Get menu items from api call
        val response =
            client.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
                .bodyAsText()

        // Parse JSON response
        val menuNetwork = Gson().fromJson(response, MenuNetwork::class.java)
        // Convert MenuNetwork to MenuItemRoom
        val menuItems = menuNetwork.menu.map {
            MenuItemRoom(it.id, it.title, it.description, it.price, it.image, category = it.category)
        }
        // Save menu items to database
        lifecycleScope.launch(Dispatchers.IO) {
            menuItems.forEach {
                if (database.menuDao().getMenuItem(it.id) == null){
                    database.menuDao().saveMenuItem(it)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    val menuItems: List<MenuItemRoom> = emptyList()
    Navigation(rememberNavController(), menuItems)
}


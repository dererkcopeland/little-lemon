package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
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


class MainActivity : ComponentActivity() {

    private val client: HttpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    private val menuItemsLiveData = MutableLiveData<List<String>>()

    private val database by lazy {
        Room.databaseBuilder(applicationContext, MenuDatabase::class.java, "menu.db").build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        setContent {
            LittleLemonTheme {
                val menuItems: List<MenuItemRoom> by database.menuDao().getAllMenuItems().observeAsState(
                    emptyList())

                Navigation(navController = rememberNavController(), menuItems)
            }
        }
    }

    private suspend fun getMenu(): MenuNetwork {
        val response = client.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            .bodyAsText()

        return response.let { Gson().fromJson(it, MenuNetwork::class.java) }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    val menuItems: List<MenuItemRoom> = emptyList()
    Navigation(rememberNavController(), menuItems)
}


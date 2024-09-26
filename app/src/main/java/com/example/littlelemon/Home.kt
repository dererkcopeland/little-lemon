package com.example.littlelemon

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.ui.theme.Green
import com.example.littlelemon.ui.theme.Yellow

@Preview(showSystemUi = true)
@Composable
fun HomePreview(){
    Home(rememberNavController(), mutableListOf())
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Home(navController: NavHostController, menu: MutableList<MenuItemRoom>){

    var menuList: MutableList<MenuItemRoom> = remember { mutableStateListOf() }

    var starters by remember { mutableStateOf(true)}
    var mains by remember { mutableStateOf(true)}
    var desserts by remember { mutableStateOf(true)}
    var drinks by remember { mutableStateOf(true)}

    var searchPhrase by remember { mutableStateOf("") }

    menuList.clear()
    menuList.addAll(menu)

    if (starters) {
        //Add all starters to menuList
        menuList.addAll(menu.filter { it.category == "starters" })
    } else {
        //Remove all starters from menuList
        menuList.removeAll(menuList.filter { it.category == "starters" })
    }
    if (mains) {
        menuList.addAll(menu.filter { it.category == "mains" })
    } else {
        menuList.removeAll(menu.filter { it.category == "mains" })
    }
    if (desserts) {
        menuList.addAll(menu.filter { it.category == "desserts" })
    } else {
        menuList.removeAll(menuList.filter { it.category == "desserts" })
    }
    if (drinks) {
        menuList.addAll(menu.filter { it.category == "drinks" })
    } else {
        menuList.removeAll(menuList.filter { it.category == "drinks" })
    }

    if (searchPhrase.isNotBlank()) {
        menuList = menuList.filter { it.title.lowercase().contains(searchPhrase.lowercase()) }.toMutableStateList()
    }

    //Sort menuList by id
    menuList.sortBy { it.id }

    //Remove duplicates
    menuList = menuList.distinct().toMutableStateList()


    Column(modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.padding(10.dp))
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.height(40.dp)
                    .padding(top = 10.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "profile",
                modifier = Modifier.width(50.dp).padding(10.dp)
                    .combinedClickable {
                        navController.navigate(Profile.route)
                    }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Green)
                .padding(10.dp)
        ) {

            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = "Little Lemon",
                style = TextStyle(fontFamily = FontFamily(Font(R.font.markazitextregular))),
                color = Yellow,
                fontSize = MaterialTheme.typography.displayLarge.fontSize
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
            ){
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Chicago",
                        style = TextStyle(fontFamily = FontFamily(Font(R.font.markazitextregular))),
                        fontSize = MaterialTheme.typography.displayMedium.fontSize,
                        color = Color.White
                    )
                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.hero_image),
                    contentDescription = "hero image",
                    modifier = Modifier.width(120.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.FillWidth
                )
            }
            OutlinedTextField(
                value = searchPhrase,
                placeholder = { Text(text = "Enter search phrase") },
                onValueChange = { searchPhrase = it },
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp).background(Color.White),
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "search") },
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 10.dp, end = 10.dp)
        ) {
            Text(
                text = "ORDER FOR DELIVERY!",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                FilterChip(
                    label = { Text(text = "Starters") },
                    onClick = { starters = !starters },
                    selected = starters,
                    colors = FilterChipDefaults.filterChipColors(
                        labelColor = Green
                    )
                )
                FilterChip(
                    onClick = { mains = !mains },
                    selected = mains,
                    label = { Text(text = "Mains") },
                    colors = FilterChipDefaults.filterChipColors(
                        labelColor = Green
                    )
                )
                FilterChip(
                    onClick = { desserts = !desserts },
                    selected = desserts,
                    label = { Text(text = "Desserts") },
                    colors = FilterChipDefaults.filterChipColors(
                        labelColor = Green
                    )
                )
                FilterChip(
                    onClick = { drinks = !drinks },
                    selected = drinks,
                    label = { Text(text = "Drinks") },
                    colors = FilterChipDefaults.filterChipColors(
                        labelColor = Green
                    )
                )
            }

            HorizontalDivider(
                modifier = Modifier.padding(10.dp),
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
        ){
            items(menuList.size){
                MenuItem(menuList[it])
            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun MenuItemPreview(){
    MenuItem(menuItem = MenuItemRoom(1, "Pizza",
        "Testing Testing Testing Testing Testing ", "1.00", category = "mains",
        image = "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/images/pizzas.jpg"))
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(menuItem: MenuItemRoom){
    Column(
        modifier = Modifier.fillMaxWidth(),
    ){
        Text(
            text = menuItem.title,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(10.dp),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = Modifier
                    .width(200.dp)
            ) {
                Text(
                    text = menuItem.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(10.dp),
                )
                Text(
                    text = "$" + menuItem.price.toDouble(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(10.dp),
                )
            }
            GlideImage(
                modifier = Modifier.width(100.dp).height(100.dp),
                model = menuItem.image,
                contentDescription = menuItem.title,
                contentScale = ContentScale.Crop
            )

        }
        HorizontalDivider(
            modifier = Modifier.padding(10.dp),
        )

    }
}
package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Preview(showSystemUi = true)
@Composable
fun HomePreview(){
    Home(rememberNavController(), emptyList())
}

@Composable
fun Home(navController: NavHostController, menuItems: List<MenuItemRoom>){
    Column(modifier = Modifier.fillMaxSize()) {
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
                modifier = Modifier.width(50.dp).padding(10.dp))
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Green)
                .padding(10.dp)
        ) {

            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = "Little Lemon",
                style = MaterialTheme.typography.displayLarge,
                color = Color.Yellow
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
            ){
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Chicago",
                        style = MaterialTheme.typography.displayMedium,
                        color = Color.White
                    )
                    Text(
                        modifier = Modifier.padding(top = 5.dp),
                        text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White
                        )
                }

                Image(
                    painter = painterResource(id = R.drawable.hero_image),
                    contentDescription = "hero image",
                    modifier = Modifier.width(150.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
            }
            OutlinedTextField(
                value = "Enter search phrase",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp).background(Color.White),
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "search") },
            )
        }

    }
}
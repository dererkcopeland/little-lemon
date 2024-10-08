package com.example.littlelemon

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class MenuItemNetwork(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String
)

@SerialName("menu")
class MenuNetwork(
    val menu: List<MenuItemNetwork>
)
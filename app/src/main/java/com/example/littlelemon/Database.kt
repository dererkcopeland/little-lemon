package com.example.littlelemon

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity
data class MenuItemRoom(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String
) {
}

@Dao
interface MenuDao {
    @Query("SELECT * FROM MenuItemRoom")
    fun getAllMenuItems(): LiveData<List<MenuItemRoom>>

    @Insert
    fun saveMenuItem(menuItem: MenuItemRoom)

    @Delete
    fun deleteMenuItem(menuItem: MenuItemRoom)

    @Query("SELECT * FROM MenuItemRoom WHERE id = :id")
    fun getMenuItem(id: Int): MenuItemRoom?
}

@Database(entities = [MenuItemRoom::class], version = 1, exportSchema = false)
abstract class MenuDatabase : RoomDatabase() {
    abstract fun menuDao(): MenuDao

}
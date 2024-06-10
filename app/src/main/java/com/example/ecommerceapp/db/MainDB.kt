package com.example.ecommerceapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ecommerceapp.models.CartItem
import com.example.ecommerceapp.models.ProductItem
import com.example.ecommerceapp.db.Dao.CartItemDao
import com.example.ecommerceapp.db.Dao.ProductDao

@Database(
    entities = [CartItem::class, ProductItem::class],
    version = 1
)
abstract class MainDB: RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun cartItemDao(): CartItemDao

    companion object {
        @Volatile
        private var INSTANCE: MainDB? = null

        fun getInstance(context: Context): MainDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDB::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
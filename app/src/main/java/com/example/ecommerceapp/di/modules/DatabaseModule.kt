package com.example.ecommerceapp.di.modules

import android.content.Context
import com.example.ecommerceapp.db.Dao.CartItemDao
import com.example.ecommerceapp.db.Dao.ProductDao
import com.example.ecommerceapp.db.MainDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): MainDB {
        return MainDB.getInstance(appContext)
    }

    @Provides
    fun provideProductDao(database: MainDB): ProductDao {
        return database.productDao()
    }

    @Provides
    fun provideCartItemDao(database: MainDB): CartItemDao {
        return database.cartItemDao()
    }

}
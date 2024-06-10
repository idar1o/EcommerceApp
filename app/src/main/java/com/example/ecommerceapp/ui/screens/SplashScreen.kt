package com.example.ecommerceapp.ui.screens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ecommerceapp.MainViewModel
import com.example.ecommerceapp.R
import com.example.ecommerceapp.utils.Constants.MAIN_SCREEN
import com.example.ecommerceapp.utils.Constants.SPLASH_SCREEN
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController, vm: MainViewModel){

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(painterResource(id = R.drawable.app_logo),
            modifier = Modifier
            .size(180.dp),
            contentDescription = "",
        )
    }
    LaunchedEffect(Unit) {
        // Инициализация данных
        vm.getAllProducts()


        // Задержка для показа SplashScreen
        delay(3000)

        // Переход к следующему экрану после инициализации
        navController.navigate(MAIN_SCREEN) {
            popUpTo(SPLASH_SCREEN) { inclusive = true }
        }
    }

}
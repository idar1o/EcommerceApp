package com.example.ecommerceapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ecommerceapp.MainViewModel
import com.example.ecommerceapp.ui.screens.CartScreen
import com.example.ecommerceapp.ui.screens.MainScreen
import com.example.ecommerceapp.ui.screens.SplashScreen
import com.example.ecommerceapp.utils.Constants.CART_SCREEN
import com.example.ecommerceapp.utils.Constants.MAIN_SCREEN
import com.example.ecommerceapp.utils.Constants.SPLASH_SCREEN

sealed class DestinationScreen(val route: String){

    data object Main: DestinationScreen(MAIN_SCREEN)
    data object Splash: DestinationScreen(SPLASH_SCREEN)
    data object Cart: DestinationScreen(CART_SCREEN)

}
@Composable
fun SetupNavHost(navController: NavHostController, vm: MainViewModel){

    NavHost(navController = navController, startDestination =  DestinationScreen.Splash.route){
        composable(DestinationScreen.Main.route) {
            MainScreen(navController = navController, vm = vm)
        }
        composable(DestinationScreen.Splash.route) {
            SplashScreen(navController = navController, vm = vm)
        }
        composable(DestinationScreen.Cart.route) {
            CartScreen(navController = navController, vm = vm)
        }
    }
}

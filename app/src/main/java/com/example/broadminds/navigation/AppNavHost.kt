package com.example.broadminds.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.broadminds.ui.theme.screens.about.AboutScreen
//import com.example.broadminds.ui.theme.screens.home.AboutScreen
import com.example.broadminds.ui.theme.screens.home.HomeScreen
//import com.example.broadminds.ui.theme.screens.home.LoginScreen
import com.example.broadminds.ui.theme.screens.login.LoginScreen
import com.example.broadminds.ui.theme.screens.products.AddIdeasScreen
//import com.example.broadminds.ui.theme.screens.products.AddProductsScreen
import com.example.broadminds.ui.theme.screens.products.UpdateProductsScreen
import com.example.broadminds.ui.theme.screens.products.ViewProductsScreen
import com.example.broadminds.ui.theme.screens.products.ViewUploadsScreen
import com.example.broadminds.ui.theme.screens.register.RegisterScreen
//import com.example.broadminds.ui.theme.screens.splash.SplashScreen

@Composable
fun AppNavHost(modifier: Modifier=Modifier,
               navController: NavHostController= rememberNavController(),
               startDestination: String= ROUTE_LOGIN){
    NavHost(navController = navController, modifier=modifier, startDestination = startDestination){
        composable(ROUTE_HOME){
            HomeScreen(navController)
        }
        composable(ROUTE_ABOUT){
           AboutScreen(navController)
        }
        composable(ROUTE_REGISTER){
            RegisterScreen(navController)
        }
        composable(ROUTE_LOGIN){
            LoginScreen(navController)
        }
        composable(ROUTE_ADD_IDEAS){
            AddIdeasScreen(navController)
        }
        composable(ROUTE_VIEW_UPLOADS){
            ViewProductsScreen(navController)
        }
        composable(ROUTE_UPDATE_PRODUCT+ "/{id}"){
                passedData ->
            UpdateProductsScreen(
                navController,passedData.arguments?.getString("id")!!)
        }
        composable(ROUTE_VIEW_UPLOAD){
            ViewUploadsScreen(navController)
        }
    }
}

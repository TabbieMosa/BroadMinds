package com.example.broadminds.ui.theme.screens.splash

import android.content.res.Configuration
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.broadminds.R
import com.example.broadminds.navigation.ROUTE_LOGIN
import kotlinx.coroutines.delay
import java.lang.reflect.Modifier

@Composable
fun SplashScreen(navController: NavHostController) {

    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    // Animation
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            // tween Animation
            animationSpec = tween(
                durationMillis = 1500,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                }))
        // Customize the delay time
        delay(1000L)
        navController.navigate(ROUTE_LOGIN)
    }

    // Image

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
//

        Image(painter = painterResource(id = R.drawable.broad),
            contentDescription = "Logo",
//            modifier = Modifier
////                .fillMaxSize()
//               ,
            contentScale= ContentScale.FillBounds
        )

        Text("BroadMinds")


//
//        Image(painter = painterResource(id = R.drawable.g2), contentDescription = "",
//            Modifier.fillMaxSize(),
//            contentScale = ContentScale.Crop

//        Image(painter = painterResource(id = R.drawable.logo),
//            contentDescription = "Logo",
//            modifier = Modifier.scale(scale.value)
//                .fillMaxSize()
        //)

        // Text("Everything Thrift")



    }




}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HomeScreenPreviewLight() {
    SplashScreen(rememberNavController())
}
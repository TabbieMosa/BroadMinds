package com.example.broadminds.ui.theme.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.broadminds.R
import com.example.broadminds.navigation.ROUTE_ABOUT
//import com.example.broadminds.navigation.ROUTE_ABOUT
import com.example.broadminds.navigation.ROUTE_ADD_IDEAS
import com.example.broadminds.navigation.ROUTE_VIEW_UPLOADS

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally) {
        var context= LocalContext.current
        //  var productdata=productviewmodel(navController,context)
        TopAppBar(title = { "BroadMinds" })
//        colors = TopAppBarDefaults.mediumTopAppBarColors(Color.LightGray)
        Box(modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
           Image(painter = painterResource(id = R.drawable.broadminds), contentDescription = "logo",
               contentScale = ContentScale.Crop,
               modifier = Modifier
                   .size(300.dp)
                   .clip(shape = CircleShape))
        }
        Text(text = "Welcome to Home page",
            color = Color.Black,
            fontFamily = FontFamily.Cursive,
            fontSize = 30.sp)
        Spacer(modifier = Modifier.height(100.dp))

        Button(onClick = {
            navController.navigate(ROUTE_ADD_IDEAS)
        },modifier = Modifier.fillMaxWidth()) {
            Text(text = "Add Idea")
        }
        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            navController.navigate(ROUTE_VIEW_UPLOADS)
        },modifier = Modifier.fillMaxWidth()) {
            Text(text = "View Ideas")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { navController.navigate(ROUTE_ABOUT)
                         },modifier=Modifier.fillMaxWidth()) {
            Text(text = "About")
        }


    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(rememberNavController())
}
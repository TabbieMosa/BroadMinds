package com.example.broadminds.ui.theme.screens.about

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.broadminds.R
import com.example.broadminds.navigation.ROUTE_HOME

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController){
    Box (modifier = Modifier
        .fillMaxWidth()
        .background(Color.White),){
    Column {
        TopAppBar(title = { Text(text = "BroadMinds",
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif)},
            colors = TopAppBarDefaults.mediumTopAppBarColors(Color.LightGray),
            navigationIcon = {
                IconButton(onClick = {navController.navigate(ROUTE_HOME)}) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Return",
                        tint = Color.Black)

                }
            }
        )
        Box (modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center){
        Image(painter = painterResource(id = R.drawable.broadminds)
            , contentDescription = "photo",
            modifier = Modifier
                .size(200.dp)
                .clip(shape = CircleShape)
            )
        }
        Text(text = "This is an app created for people to showcase ideas they have in mind." +
                "The aim of this app is for people to upload ideas and others to get insipration from the ideas posted by others." +
                "Also for those inspired by the ideas of others and need to use then contact the uploaders of the various ideas and give them a chance to make their ideas a reality.",
            fontFamily = FontFamily.Serif,
            color = Color.Black,
        )
        Text(text = "For more information contact us on:",
            color = Color.Black,
            fontFamily = FontFamily.Serif)
        Box (modifier = Modifier.fillMaxWidth()){
            IconButton(onClick = { /*TODO*/ }) {
                androidx.compose.material3.Icon(imageVector = Icons.Default.Call,
                    contentDescription = "tel", tint = Color.Black)
            }
            Text(text ="0729455763")
        }
    }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AboutScreenPreview(){
    AboutScreen(rememberNavController())
}
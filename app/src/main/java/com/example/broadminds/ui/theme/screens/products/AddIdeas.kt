package com.example.broadminds.ui.theme.screens.products

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.broadminds.data.ProductViewModel
import com.example.broadminds.navigation.ROUTE_ADD_IDEAS
import com.example.broadminds.navigation.ROUTE_HOME
import com.example.broadminds.navigation.ROUTE_VIEW_UPLOAD
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import java.util.UUID


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddIdeasScreen(navController: NavHostController) {
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .fillMaxSize()
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        TopAppBar(title = { Text(text = "BroadMinds",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold
        )},
            colors = TopAppBarDefaults.mediumTopAppBarColors(Color.LightGray),
            navigationIcon = {
                IconButton(onClick = { navController.navigate(ROUTE_HOME)}) {
                   Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Return",
                       tint = Color.Black)
                    
                }
            }
            )
        Text(
            text = "Add Ideas",
            fontSize = 30.sp,
            fontFamily = FontFamily.Cursive,
            color = Color.Red,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline
        )

        var userName by remember { mutableStateOf(TextFieldValue("")) }
        var useremail by remember { mutableStateOf(TextFieldValue("")) }
        var ideadescription by remember { mutableStateOf(TextFieldValue("")) }


        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
           placeholder = { Text(text = "User Name")},
            leadingIcon = {
                          Icon(imageVector = Icons.Default.Person, contentDescription = "name")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = useremail,
            onValueChange = { useremail = it },
            placeholder = { Text(text = "useremail")},
            leadingIcon = {
                          Icon(imageVector = Icons.Default.AccountBox, contentDescription = "mobile")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = ideadescription,
            onValueChange = { ideadescription = it },
           placeholder = { Text(text = "Idea Description")},
            leadingIcon = {
                          Icon(imageVector = Icons.Default.Create, contentDescription = "email")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )



        Button(onClick = {

            val productRepository = ProductViewModel(navController,context)
            productRepository.saveProduct(userName.text.trim(),useremail.text.trim(),
                ideadescription.text)
            navController.navigate(ROUTE_ADD_IDEAS)


        }, colors = ButtonDefaults.buttonColors(Color.Gray),
//            shape = RoundedCornerShape(5.dp)
            ) {
            Text(text = "Save")
        }
        fun saveProductWithImage(){

        }
        Spacer(modifier = Modifier.height(20.dp))

        //---------------------IMAGE PICKER START-----------------------------------//


        ImagePicker(Modifier,context, navController, userName.text.trim(), useremail.text.trim(), ideadescription.text.trim())


    }
}


@Composable
fun ImagePicker(modifier: Modifier = Modifier, context: Context, navController: NavHostController, name:String, email:String, ideadescription:String) {
    var hasImage by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            hasImage = uri != null
            imageUri = uri
        }
    )

    Column(modifier = modifier) {
        if (hasImage && imageUri != null) {
            val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
            Image(bitmap = bitmap.asImageBitmap(), contentDescription = "Selected image")
        }
        Column(
            modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { imagePicker.launch("image/*") },
                colors = ButtonDefaults.buttonColors(Color.Gray)
            ) {
                Text(text = "Select Image")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    // Upload logic
                    val storageRef = Firebase.storage.reference.child("images/${UUID.randomUUID()}")
                    val uploadTask = storageRef.putFile(imageUri!!)
                    uploadTask.addOnSuccessListener { _ ->
                        storageRef.downloadUrl.addOnSuccessListener { uri ->
                            val imageUrl = uri.toString()
                            // Save image details to Firestore
                            saveImageDetailsToFirestore(name, email, ideadescription, imageUrl)
                            // Navigate to the view uploads screen
                            navController.navigate(ROUTE_VIEW_UPLOAD)
                        }
                    }.addOnFailureListener { exception ->
                        // Handle failure to upload image
                    }
                },
                colors = ButtonDefaults.buttonColors(Color.Gray)
            ) {
                Text(text = "Upload")
            }
            Button(
                onClick = {  // Upload logic
                    val storageRef = Firebase.storage.reference.child("images/${UUID.randomUUID()}")
                    val uploadTask = storageRef.putFile(imageUri!!)
                    uploadTask.addOnSuccessListener { _ ->
                        storageRef.downloadUrl.addOnSuccessListener { uri ->
                            val imageUrl = uri.toString()
                            // Save image details to Firestore
                            saveImageDetailsToFirestore(name, email, ideadescription, imageUrl)
                            // Navigate to the view uploads screen
                            navController.navigate(ROUTE_VIEW_UPLOAD)
                        }
                    }.addOnFailureListener { exception ->
                        // Handle failure to upload image
                    } },
                colors = ButtonDefaults.buttonColors(Color.Gray)
            ) {
                Text(text = "View Uploads",
                    color = Color.White)
            }
        }
    }
}

fun saveImageDetailsToFirestore(name: String, email: String, ideaDescription: String, imageUrl: String) {
    val ideasCollection = Firebase.firestore.collection("ideas")
    val newIdeaDocument = ideasCollection.document()

    val ideaData = hashMapOf(
        "name" to name,
        "email" to email,
        "ideaDescription" to ideaDescription,
        "imageUrl" to imageUrl
    )

    newIdeaDocument.set(ideaData)
        .addOnSuccessListener {
            // Document successfully written
        }
        .addOnFailureListener { e ->
            // Handle errors
        }
}

@Preview
@Composable
fun AddIdeasScreenPreview() {
    AddIdeasScreen(rememberNavController())

}


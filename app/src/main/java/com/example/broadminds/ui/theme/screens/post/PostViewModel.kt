package com.example.broadminds.ui.theme.screens.post

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.storage.storage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID

class PostViewModel : ViewModel() {
    private val database = Firebase.database.reference
    private val storage = Firebase.storage.reference

    fun uploadPost(message: String, imageUri: Uri?) {
        if (imageUri != null) {
            val fileName = UUID.randomUUID().toString()
            val imageRef = storage.child("images/$fileName")

            imageRef.putFile(imageUri)
                .addOnSuccessListener { taskSnapshot ->
                    imageRef.downloadUrl.addOnSuccessListener { uri ->
                        val post = Post(message, uri.toString())
                        database.child("posts").push().setValue(post)
                    }
                }
        }
    }

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> get() = _posts

    init {
        database.child("posts").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val postList = mutableListOf<Post>()
                snapshot.children.forEach { child ->
                    val post = child.getValue(Post::class.java)
                    post?.let { postList.add(it) }
                }
                _posts.value = postList
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }
}

data class Post(
    var message: String? = null,
    var imageUrl: String? = null
) {
    constructor() : this(null, null)
}
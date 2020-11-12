package com.example.socialbee.network

import com.example.socialbee.model.Post
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

const val POST_COLLECTION_NAME = "post"

class FirestoreService {
    val firebaseFirestore = FirebaseFirestore.getInstance()
    val setting = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

    init {
        firebaseFirestore.firestoreSettings = setting
    }

    fun getPost(callback: Callback<List<Post>>) {
        firebaseFirestore.collection(POST_COLLECTION_NAME)
            .get()
            .addOnSuccessListener { result ->
                for (doc in result) {
                    // obtengo en una lista el resultado de firestore
                    val list = result.toObjects(Post:: class.java) // tenemos toda la lista de las colecciones
                    callback.onSuccess(list)
                    break
                }
            }
    }
}
package com.example.socialbee.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.socialbee.model.Post
import com.example.socialbee.network.Callback
import com.example.socialbee.network.FirestoreService
import java.lang.Exception

class PostViewModel: ViewModel() {

    // esto se encarga de comunicar con lo que hicimos en la UI con firestore
    // creamos una instancia de Firebase que ya la tenemos creada

    val firestoreService = FirestoreService()
    val listPost : MutableLiveData<List<Post>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh() {
        getPostFromFirebase()
    }

    fun getPostFromFirebase(){
        firestoreService.getPost(object : Callback<List<Post>> {
            override fun onSuccess(result: List<Post>?) {
                listPost.postValue(result)
                proccessFinished()
            }

            override fun onFailed(exception: Exception) {

            }
        })
    }

    fun proccessFinished(){
        isLoading.value = true
    }
}
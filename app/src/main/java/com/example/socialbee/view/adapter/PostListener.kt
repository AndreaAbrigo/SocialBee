package com.example.socialbee.view.adapter

import android.view.View
import com.example.socialbee.model.Post

interface PostListener {

    fun onPostClicked(post: Post, position: Int)


}
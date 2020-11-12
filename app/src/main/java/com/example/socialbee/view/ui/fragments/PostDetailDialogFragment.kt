package com.example.socialbee.view.ui.fragments

import android.graphics.Color
import android.graphics.Color.WHITE
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.socialbee.R
import com.example.socialbee.model.Post
import kotlinx.android.synthetic.main.fragment_post_detail_dialog.*

class PostDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_detail_dialog, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close_white)
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.setNavigationOnClickListener{
            dismiss()
        }

        val post = arguments?.getSerializable("post") as Post
        toolbar.title = post.title

        tvDetailTitle.text = post.title
        tvDescription.text = post.description
        tvLatitude.text = post.latitude
        tvLongitude.text = post.longitude
        Glide.with(this)
            .load(post.photo)
            .into(ivDetailImage)
        tvUser.text = post.user
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }


}

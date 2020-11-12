package com.example.socialbee.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.socialbee.R
import com.example.socialbee.model.Post
import com.example.socialbee.view.adapter.PostAdapter
import com.example.socialbee.view.adapter.PostListener
import com.example.socialbee.view.ui.activities.MainActivity
import com.example.socialbee.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.bnvMenu
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_post.*

class HomeFragment : Fragment(), PostListener {

    private lateinit var postAdapter: PostAdapter
    private lateinit var viewModel: PostViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)
        viewModel.refresh()
        postAdapter = PostAdapter(this)
        rvHome.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = postAdapter
        }
        observerViewModel()
    }

    fun observerViewModel(){
        viewModel.listPost.observe(viewLifecycleOwner, Observer<List<Post>> {
            post -> postAdapter.updateData(post)
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer<Boolean>{
            if (it != null)
                rlHome.visibility = View.INVISIBLE
        })
    }

    override fun onPostClicked(post: Post, position: Int) {
        val bundle = bundleOf(
            "post" to post)
        findNavController().navigate(R.id.postDetailDialogFragment, bundle)
    }



}
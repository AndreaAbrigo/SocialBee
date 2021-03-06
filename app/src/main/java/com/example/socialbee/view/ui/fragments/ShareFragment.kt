package com.example.socialbee.view.ui.fragments

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.socialbee.R
import kotlinx.android.synthetic.main.fragment_post_detail_dialog.*
import kotlinx.android.synthetic.main.fragment_share.*

class ShareFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_share, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*Desde el boton BACK del fragment de cierra la activity*/
        toolbarShare.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close_white)
        toolbarShare.setTitleTextColor(Color.WHITE)
        toolbarShare.setNavigationOnClickListener{
            dismiss()
            val act = getActivity()
            if (act != null) {
                act.finish()
            }
        }
    }

}
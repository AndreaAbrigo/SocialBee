package com.example.socialbee.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.os.bundleOf
import com.example.socialbee.R
import com.example.socialbee.view.ui.fragments.MapFragment
import com.example.socialbee.view.ui.fragments.MessageFragment
import com.example.socialbee.view.ui.fragments.ShareFragment

class MenuItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_item)

        /*Capturamos los datos que vienen del activityMain*/
        val cod = intent.getStringExtra("codigo")
        val latitud = intent.getStringExtra("latitude")
        val longitud = intent.getStringExtra("longitude")
        val user = intent.getStringExtra("user")

        if (cod=="map"){
            /*val textView = findViewById<TextView>(R.id.tvPrueba).apply {
                text = latitud
            }*/
/*
            CREAMOS UN BUNDLE PARA GUARDAR LOS VALORES Y ENVIARLOS AL MAPFRAMENT*/
            val bundle = bundleOf(
                "latitud" to latitud,
                "longitud" to longitud,
                "user" to user
            )
            /*CREAMOS EL FRAGMENTO*/
            val newFragment = MapFragment()
            /*LE PASAMOS EN BUNDLE AL FRAGMENTO*/
            newFragment.arguments = bundle
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.contenedor, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        else if(cod=="share"){
            val newFragment = ShareFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.contenedor, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        else if(cod=="message"){
            val newFragment = MessageFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.contenedor, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }


    }
}
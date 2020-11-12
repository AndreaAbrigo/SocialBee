package com.example.socialbee.view.ui.fragments

import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.socialbee.R
import com.example.socialbee.model.Post
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.android.synthetic.main.fragment_share.*
import java.security.AccessController.getContext

class MapFragment : DialogFragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*Desde el boton BACK del fragment de cierra la activity*/
        toolbarMap.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close_white)
        toolbarMap.setTitleTextColor(Color.WHITE)
        toolbarMap.setNavigationOnClickListener{
            dismiss()
            val act = getActivity()
            if (act != null) {
                act.finish()
            }
        }

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {

        /// Obtenemos los valores que vienen del MenuItemActivity
        /// El operador !! evalúa que no vengan valores nulos
/*        if (arguments != null){
            val latitude = (arguments!!.getString("latitud"))?.toDouble()
            val longitude = arguments!!.getString("longitud")?.toDouble()
            val user = arguments!!.getString("user")

        }*/

        val latitude = (arguments!!.getString("latitud"))?.toDouble()
        val longitude = arguments!!.getString("longitud")?.toDouble()
        val user = arguments!!.getString("user")

        /*val post = Post()*/

       /* /// Conversión de string a double
        val latitude : Double = latitu
        val longitude : Double = longitude.toDouble()*/

/*        val latitude = -34.581763
        val longitude = -58.404363*/

        val zoom = 16f
        val centerMap = LatLng(latitude!!, longitude!!)

        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(centerMap, zoom))

        val centerMark = LatLng(latitude, longitude)
        val markerOptions = MarkerOptions()
        markerOptions.position(centerMark)
        markerOptions.title(user)

        val bitmapDraw = context?.applicationContext?.let { ContextCompat.getDrawable(it, R.drawable.pin) as BitmapDrawable }
        val smallMarker = bitmapDraw?.bitmap?.let { Bitmap.createScaledBitmap(it, 150, 150, false) }

        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker))

        googleMap?.addMarker(markerOptions)
        googleMap?.setOnMarkerClickListener(this)


        googleMap?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.custom_map))


    }

    /// Si se hace click en el marcador se muestra el nombre del lugar donde se originó la publicación
    override fun onMarkerClick(p0: Marker?): Boolean {
        val user = arguments!!.getString("user")
        if (p0 != null) {
            if (p0.isInfoWindowShown()) {
                p0.hideInfoWindow();
            } else {
                p0.showInfoWindow();
            }
        }
        return true;
    }
}
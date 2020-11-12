package com.example.socialbee.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.socialbee.R
import com.example.socialbee.model.Post
import com.example.socialbee.view.ui.activities.MenuItemActivity
import kotlinx.android.synthetic.main.fragment_post_detail_dialog.*
import kotlinx.android.synthetic.main.item_post.view.*

class PostAdapter(val postListener: PostListener) : RecyclerView.Adapter<PostAdapter.ViewHolder>(){

    var listPost = ArrayList<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostAdapter.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent,false))


    override fun onBindViewHolder(holder: PostAdapter.ViewHolder, position: Int) {

        val post = listPost[position] as Post

        holder.tvPostName.text = post.title
        holder.tvLatitude.text = post.latitude
        holder.tvLongitude.text = post.longitude
        holder.tvUser.text = post.user
        /*holder.tvDetailDescription.text = post.description*/

        Glide.with(holder.itemView.context)
            .load(post.photo)
            .apply(RequestOptions.centerCropTransform())
            .into(holder.ivPostImage)

        holder.itemView.setOnClickListener{
            postListener.onPostClicked(post, position)
        }

        holder.setOnClickListener()

    }

    override fun getItemCount() = listPost.size

    fun updateData(data: List<Post>) {
        listPost.clear()
        listPost.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView), View.OnClickListener {
        /*Obtenemos una referencia del contexto para poder iniciar el intent*/
        val context = itemView.getContext()

        val tvPostName = itemView.findViewById<TextView>(R.id.tvItemTitle)
        val ivPostImage = itemView.findViewById<ImageView>(R.id.ivImage)
        val tvDetailDescription = itemView.findViewById<TextView>(R.id.tvDescription)
        val tvUser = itemView.findViewById<TextView>(R.id.tvUser)
        val tvLatitude = itemView.findViewById<TextView>(R.id.tvLatitude)
        val tvLongitude = itemView.findViewById<TextView>(R.id.tvLongitude)
        val btnLocation = itemView.findViewById<ImageButton>(R.id.ibLocation)
        val btnShare = itemView.findViewById<ImageButton>(R.id.ibShare)
        val btnMessage = itemView.findViewById<ImageButton>(R.id.ibMessage)

        fun setOnClickListener() {
            btnLocation.setOnClickListener(this)
            btnShare.setOnClickListener(this)
            btnMessage.setOnClickListener(this)
        }

        override fun onClick(p0: View?){
            var id = p0?.getId()

            // Se obtinen los datos de la card para pasar al mapa
            var lat = tvLatitude.text
            var lon = tvLongitude.text
            var user = tvUser.text

            when(id){
                R.id.ibLocation -> run {
                    val intent = Intent(context, MenuItemActivity::class.java).apply{
                        putExtra("codigo", "map")
                        /*putExtra("latitude", tvLatitude.toString())*/
                        putExtra("latitude", lat.toString())
                        putExtra("longitude", lon.toString())
                        putExtra("user", user.toString())
                        }
                    context.startActivity(intent)
                }
                R.id.ibShare -> run {
                    val intent = Intent(context, MenuItemActivity::class.java).apply{
                        putExtra("codigo", "share")
                    }
                    context.startActivity(intent)
                }
                R.id.ibMessage -> run {
                    val intent = Intent(context, MenuItemActivity::class.java).apply{
                        putExtra("codigo", "message")
                    }
                    context.startActivity(intent)
                }
            }
        }

    }

}
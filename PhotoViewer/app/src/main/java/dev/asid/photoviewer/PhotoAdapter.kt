package dev.asid.photoviewer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.io.File

class PhotoAdapter(private val photos: List<String>, private val context: Context) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {
    val photoList: ArrayList<String> = ArrayList()
    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gallery_item, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val imageFile = File(photoList[position])
        if (imageFile.exists()){
            Glide.with(context).load(photos[position]).into(holder.imageView)
        }

    }

    override fun getItemCount() = photos.size

}

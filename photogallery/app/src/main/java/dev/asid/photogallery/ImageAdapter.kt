package dev.asid.photogallery

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.asid.photogallery.MainActivity
import dev.asid.photogallery.R

class ImageAdapter (
    private val mainActivity: MainActivity,
    private val imageList:List<Image>)
    :RecyclerView.Adapter<ImageAdapter.ListItemHolder>() {
    inner class ListItemHolder(view: View) :
        RecyclerView.ViewHolder(view),
        View.OnClickListener{
            internal var image: ImageView = view.findViewById<ImageView>(R.id.image)
            internal var title: TextView = view.findViewById<TextView>(R.id.title)
        override fun onClick(v: View?) {
            mainActivity.showImage(adapterPosition)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_item, parent, false)
        return ListItemHolder(itemView)
    }

    override fun getItemCount(): Int {
        if (imageList!= null) {
            return imageList.size
        }
        return -1
    }

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {
        val image = imageList[position]
        holder.title.text = image.title
        holder.image.setImageURI(Uri.parse(image.uri))

    }
}
package com.example.testframe.ui.entertainment.place

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testframe.R
import com.squareup.picasso.Picasso
import kotlin.random.Random

class PlaceAdapter(
    private val articles: List<Place> = listOf<Place>(),
    private val context: Context
) : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {
    lateinit var onItemClickListener: OnItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_place, parent, false)
        return PlaceViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holders: PlaceViewHolder, position: Int) {
        val holder: PlaceViewHolder = holders
        val model: Place = articles[position]
        setBackgroundRandomColor(holder)
        Picasso.get().load(model.uriToImg).into(holder.imageView)
        holder.cinema_title.text = model.title
    }

    private fun setBackgroundRandomColor(holder: PlaceViewHolder) {
        val rnd = Random
        val color: Int = Color.argb(100, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        holder.imageView.setBackgroundColor(color)
        holder.progressBar.visibility = View.INVISIBLE
    }

    override fun getItemCount(): Int = articles.size

    interface OnItemClickListener{
        fun onItemClick(view: View, position: Int)
    }
    class PlaceViewHolder(itemView: View, onItemClickListener: OnItemClickListener) : RecyclerView.ViewHolder(
        itemView
    ), View.OnClickListener{
        var cinema_title: TextView
        val imageView: ImageView
        val progressBar: ProgressBar
        val onItemClickListener: OnItemClickListener

        init{

            itemView.setOnClickListener(this)
            cinema_title = itemView.findViewById(R.id.cinema_title)
            imageView = itemView.findViewById(R.id.img_cinema)
            progressBar = itemView.findViewById(R.id.progress_load_photo_place)
            this.onItemClickListener = onItemClickListener
        }

        override fun onClick(v: View) {
            onItemClickListener.onItemClick(v, adapterPosition)
        }
    }
}
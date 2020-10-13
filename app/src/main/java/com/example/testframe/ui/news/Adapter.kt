package com.example.newsapp

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.newsapp.models.Article
import com.example.testframe.R

class Adapter(private val articles:List<Article> = listOf<Article>(),
                private val context: Context
) : RecyclerView.Adapter<Adapter.MyViewHolder>() {
    lateinit var onItemClickListener: OnItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view:View= LayoutInflater.from(context).inflate(R.layout.item,parent,false)
        return MyViewHolder(view,onItemClickListener)
    }

    override fun onBindViewHolder(holders: MyViewHolder, position: Int) {
        val holder: MyViewHolder = holders
        val model: Article = articles[position]

        val requestOptions: RequestOptions = RequestOptions()
        requestOptions.placeholder(Utils.getRandomDrawbleColor())
        requestOptions.error(Utils.getRandomDrawbleColor())
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)
        requestOptions.centerCrop()

        Glide.with(context)
            .load(model.urlToImage)
            .apply(requestOptions)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.progressBar.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.progressBar.visibility = View.GONE
                    return false
                }
            })
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.imageView)

        holder.title.text = model.title
        holder.desc.text = model.description
        holder.source.text = model.source.name
        holder.time.text = " \u2022 " + Utils.DateToTimeFormat(model.publishedAt)
        holder.published_at.text = Utils.DateFormat(model.publishedAt)
        holder.author.text = model.author
    }

    override fun getItemCount(): Int = articles.size

    interface OnItemClickListener{
        fun onItemClick(view:View,position:Int)
    }
    class MyViewHolder(itemView: View, onItemClickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var title: TextView
        var desc: TextView
        var author: TextView
        var published_at: TextView
        var source: TextView
        var time: TextView
        val imageView: ImageView
        val progressBar: ProgressBar
        val onItemClickListener: OnItemClickListener

        init{

            itemView.setOnClickListener(this)
            title = itemView.findViewById(R.id.title)
            desc = itemView.findViewById(R.id.desc)
            author = itemView.findViewById(R.id.author)
            published_at = itemView.findViewById(R.id.publishedAt)
            source = itemView.findViewById(R.id.source)
            time = itemView.findViewById(R.id.time)
            imageView = itemView.findViewById(R.id.img)
            progressBar = itemView.findViewById(R.id.prograss_load_photo)
            this.onItemClickListener = onItemClickListener
        }

        override fun onClick(v: View) {
            onItemClickListener.onItemClick(v,adapterPosition)
        }
    }
}
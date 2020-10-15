package com.example.testframe.ui.entertainment.content

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testframe.R

class SliderAdapter : RecyclerView.Adapter<SliderAdapter.SliderItemViewHolder>() {

    private val data: ArrayList<String> = ArrayList();
    var callback: Callback? = null
    val clickListener = View.OnClickListener { v -> v?.let { callback?.onItemClicked(it) } }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderItemViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.slider_item, parent, false)

        itemView.setOnClickListener(clickListener)

        return SliderItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SliderItemViewHolder, position: Int) {
        holder.tvItem.text = data[position]
    }


    fun setData(data: ArrayList<String>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    interface Callback {
        fun onItemClicked(view: View)
    }
    class SliderItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvItem: TextView = itemView.findViewById(R.id.tv_item)
    }
}
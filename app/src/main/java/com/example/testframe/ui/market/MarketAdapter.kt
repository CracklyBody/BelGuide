package com.example.testframe.ui.market

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testframe.databinding.ItemMarketListBinding
import com.squareup.picasso.Picasso

class MarketAdapter (private var articles:List<ItemMarket> = listOf(),
                     private var listener: OnItemClickListener
) : RecyclerView.Adapter<MarketAdapter.MarketViewHolder>() {

    lateinit var onItemClickListener: OnItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMarketListBinding.inflate(layoutInflater,parent,false)
        return MarketViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: MarketViewHolder, position: Int)  = holder.bind(articles[position],listener)

    fun replaceData(data: List<ItemMarket>){
        articles = data
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = articles.size

    interface OnItemClickListener{
        fun onItemClick(view: View, position:Int,buttonId:Int)
    }
    class MarketViewHolder(private var binding: ItemMarketListBinding, onItemClickListener: OnItemClickListener) : RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        private val onItemClickListener: OnItemClickListener = onItemClickListener

        override fun onClick(v: View) {
            onItemClickListener.onItemClick(v,adapterPosition,binding.buyButton.id)
        }
        fun bind(item: ItemMarket, listener: OnItemClickListener){
            binding.marketItemText.text = item.title
            binding.marketPriceText.text = item.value.toString()
            Picasso.get().load(item.uriToImg).into(binding.marketItemImage)
            binding.executePendingBindings()
        }
    }
}
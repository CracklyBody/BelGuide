package com.example.testframe.ui.market

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.testframe.R
import com.squareup.picasso.Picasso

class MarketAdapter (private val articles:List<ItemMarket> = listOf<ItemMarket>(),
                     private val context: Context
) : RecyclerView.Adapter<MarketAdapter.MarketViewHolder>() {
    var sharedPreferences: SharedPreferences
    init {
        sharedPreferences = context.getSharedPreferences(0.toString(),
            AppCompatActivity.MODE_PRIVATE
        )
    }
    var onItemClickListener: MarketAdapter.OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketViewHolder {
        val view:View= LayoutInflater.from(context).inflate(R.layout.item_market_list,parent,false)

        return MarketViewHolder(view, onItemClickListener!!,sharedPreferences)
    }

    override fun onBindViewHolder(holders: MarketViewHolder, position: Int) {
        val holder: MarketAdapter.MarketViewHolder = holders
        val model: ItemMarket = articles.get(position)
        holder.market_name.setText(model.title)
        holder.market_price.setText(model.value.toString())
        Picasso.get().load(model.uriToImg).into(holder.imageView)

    }

    override fun getItemCount(): Int = articles.size

    interface OnItemClickListener{
        fun onItemClick(view: View, position:Int)
    }
    class MarketViewHolder(itemView: View, onItemClickListener: MarketAdapter.OnItemClickListener,var sharedPreferences: SharedPreferences) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var market_price: TextView
        var market_name: TextView
        var butt: Button
        val imageView: ImageView

        val onItemClickListener: MarketAdapter.OnItemClickListener

        init{

            itemView.setOnClickListener(this)
            market_price = itemView.findViewById(R.id.market_price_text)
            imageView = itemView.findViewById(R.id.market_item_image)
            market_name = itemView.findViewById(R.id.market_item_text)
            butt = itemView.findViewById(R.id.buy_button)
            butt.setOnClickListener(this)
            this.onItemClickListener = onItemClickListener
        }

        override fun onClick(v: View) {
            if(v.id == butt.id){
                val price = market_price.text.toString().toLong()
                val editor = sharedPreferences!!.edit()
                var wallet:Long = sharedPreferences!!.getLong("value",0)
                if(price <= wallet){
                    editor.putLong("value", wallet-price)
                    editor.apply()
                    v.rootView.findViewById<TextView>(R.id.text_market).setText((wallet-price).toString())
                    Toast.makeText(v.context,"Куплено!", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(v.context,"Не хватает баллов", Toast.LENGTH_SHORT).show()
                }
            }
            onItemClickListener.onItemClick(v,adapterPosition)
        }
    }
}
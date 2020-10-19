package com.example.testframe.ui.market

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testframe.MainActivity
import com.example.testframe.R
import com.example.testframe.ui.market_list

class MarketFragment() : Fragment() {
  private lateinit var sharedPreferences: SharedPreferences
  private lateinit var recyclerView: RecyclerView
  private lateinit var layoutManager: RecyclerView.LayoutManager
  private lateinit var adapter: MarketAdapter
  private lateinit var points: TextView
  private lateinit var root: View

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    root = inflater.inflate(R.layout.fragment_market, container, false)
    sharedPreferences = (requireActivity() as MainActivity).sharedPreferences
    initViews()
    loadData()
    return root
  }

  private fun initViews() {
    recyclerView = root.findViewById(R.id.market_recycler_view)
    points = root.findViewById(R.id.text_market)
    layoutManager = LinearLayoutManager(context)
    recyclerView.layoutManager = layoutManager
    recyclerView.itemAnimator = DefaultItemAnimator()
    recyclerView.isNestedScrollingEnabled = false
  }

  private fun loadData(){
    adapter = MarketAdapter(market_list, requireContext())
    recyclerView.adapter = adapter
    adapter.notifyDataSetChanged()
    initListener()
    val wallet:Long = sharedPreferences.getLong("value",0)
    points.text = wallet.toString()

  }

  private fun initListener(){
    adapter.onItemClickListener = object: MarketAdapter.OnItemClickListener{
      override fun onItemClick(view: View, position:Int, buttonId:Int) {
        if(view.id == buttonId){
          val price = market_list[position].value
          val editor = sharedPreferences.edit()
          val wallet:Long = sharedPreferences.getLong("value",0)
          if(price <= wallet){
            editor.putLong("value", wallet-price)
            editor.apply()
            points.text = (wallet-price).toString()
            Toast.makeText(view.context,"Куплено!", Toast.LENGTH_SHORT).show()
          }
          else{
            Toast.makeText(view.context,"Не хватает баллов", Toast.LENGTH_SHORT).show()
          }
        }
      }
    }
  }
}
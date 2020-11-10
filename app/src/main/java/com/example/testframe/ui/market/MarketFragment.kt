package com.example.testframe.ui.market

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testframe.MainActivity
import com.example.testframe.R
import com.example.testframe.databinding.FragmentMarketBinding
import com.example.testframe.ui.market_list

class MarketFragment() : Fragment() , MarketAdapter.OnItemClickListener{
  private lateinit var sharedPreferences: SharedPreferences
  private lateinit var recyclerView: RecyclerView
  private lateinit var layoutManager: RecyclerView.LayoutManager
  private lateinit var points: TextView
  private lateinit var binding: FragmentMarketBinding
  private val viewModel: MarketViewModel by viewModels()
  private var adapter = MarketAdapter(listOf(), this)

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(inflater,R.layout.fragment_market, container, false)
    sharedPreferences = (requireActivity() as MainActivity).sharedPreferences
    binding.viewModel = viewModel
    binding.executePendingBindings()

    binding.marketRecyclerView.layoutManager = LinearLayoutManager(context)
    binding.marketRecyclerView.itemAnimator =DefaultItemAnimator()
    binding.marketRecyclerView.isNestedScrollingEnabled = false


    binding.marketRecyclerView.adapter = adapter
    initListener()
    loadData()
    viewModel.marketItems.observe(viewLifecycleOwner,{
      it?.let { adapter.replaceData(it) }
    })
    viewModel.loadMarketItems()
    return binding.root
  }


  private fun loadData(){
    val wallet:Long = sharedPreferences.getLong("value",0)
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

  override fun onItemClick(view: View, position: Int, buttonId: Int) {
    TODO("Not yet implemented")
  }
}
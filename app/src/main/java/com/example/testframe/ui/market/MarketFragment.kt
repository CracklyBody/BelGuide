package com.example.testframe.ui.market

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testframe.R
import com.example.testframe.ui.market_list

class MarketFragment(val value:Long) : Fragment() {
  private lateinit var recyclerView: RecyclerView
  private lateinit var layoutManager: RecyclerView.LayoutManager
  private lateinit var adapter: MarketAdapter
  private lateinit var root: View

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    root = inflater.inflate(R.layout.fragment_market, container, false)
    val textView: TextView = root.findViewById(R.id.text_market)

    recyclerView = root.findViewById(R.id.market_recycler_view)
    layoutManager = LinearLayoutManager(context)
    recyclerView.layoutManager = layoutManager
    recyclerView.itemAnimator = DefaultItemAnimator()
    recyclerView.isNestedScrollingEnabled = false
    loadData()

    return root
  }

  private fun loadData(){
    adapter = MarketAdapter(market_list, requireContext())
    recyclerView.adapter = adapter
    adapter.notifyDataSetChanged()
    initListener()
  }

  private fun initListener(){
    adapter.onItemClickListener = object: MarketAdapter.OnItemClickListener{
      override fun onItemClick(view: View, position: Int) {
        //Toast.makeText(context,"Была нажата кнопка $position", Toast.LENGTH_SHORT).show()
      }
    }
  }
}
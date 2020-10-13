package com.example.testframe.ui.entertainment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.testframe.R
import com.example.testframe.ui.entertainment.place.Place
import com.example.testframe.ui.entertainment.place.PlaceAdapter

class PlaceFragment(val articles: List<Place>): Fragment(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter:PlaceAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.place_details, container, false)
        swipeRefreshLayout = root.findViewById(R.id.swipe_refresh_cinema)
        swipeRefreshLayout.setOnRefreshListener{this}
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent)
        recyclerView = root.findViewById(R.id.cinema_recycler_view)
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.isNestedScrollingEnabled = false
        loadData("")

        return root
    }

    private fun onLoadingSwipeRefresh(keyword: String) {
        swipeRefreshLayout.post { loadData(keyword) }
    }

    private fun loadData(keyword: String){
        adapter  = PlaceAdapter(articles, requireContext())
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
        initListener()
    }

    private fun initListener(){
        adapter.onItemClickListener = object: PlaceAdapter.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {

            }
        }
    }
}
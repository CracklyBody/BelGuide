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

    private var recyclerView: RecyclerView? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter:PlaceAdapter? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    lateinit var root: View
    private lateinit var newsViewModel: PlaceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        newsViewModel =
            ViewModelProviders.of(this).get(PlaceViewModel::class.java)
        root = inflater.inflate(R.layout.place_details, container, false)
        swipeRefreshLayout = root.findViewById(R.id.swipe_refresh_cinema)
        swipeRefreshLayout!!.setOnRefreshListener{this}
        swipeRefreshLayout!!.setColorSchemeResources(R.color.colorAccent)
        recyclerView = root.findViewById(R.id.cinema_recycler_view)
        layoutManager = LinearLayoutManager(context)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.isNestedScrollingEnabled = false
        LoadData("")

        return root
    }

    private fun onLoadingSwipeRefresh(keyword: String) {
        swipeRefreshLayout!!.post { LoadData(keyword) }
    }

    private fun LoadData(keyword: String){
        adapter = PlaceAdapter(articles, context!!)
        recyclerView!!.adapter = adapter
        adapter!!.notifyDataSetChanged()
        initListener()


    }

    private fun initListener(){
        adapter?.onItemClickListener = object: PlaceAdapter.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {

            }
        }
    }
}
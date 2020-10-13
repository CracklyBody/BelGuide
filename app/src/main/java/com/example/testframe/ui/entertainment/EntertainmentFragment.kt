package com.example.testframe.ui.entertainment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.testframe.R
import com.example.testframe.ui.*
import com.example.testframe.ui.entertainment.content.ScreenUtils
import com.example.testframe.ui.entertainment.content.SliderAdapter
import com.example.testframe.ui.entertainment.content.SliderLayoutManager

class EntertainmentFragment() : Fragment() {

  private lateinit var entertainmentViewModel: EntertainmentViewModel
  private lateinit var rvHorizontalPicker: RecyclerView
  private lateinit var root: View

  private val data = listOf<String>("Кинотеатр","Парк","ТЦ","Театр","Музей").map { it } as ArrayList<String>

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    entertainmentViewModel =
    ViewModelProviders.of(this).get(EntertainmentViewModel::class.java)
    root = inflater.inflate(R.layout.fragment_entertainment, container, false)
    //val textView: TextView = root.findViewById(R.id.text_dashboard)
//    entertainmentViewModel.text.observe(viewLifecycleOwner, Observer {
//      textView.text = it
//    })
    setTvSelectedItem()
    setHorizontalPicker()
    requireFragmentManager().beginTransaction().replace(
      R.id.nav_entertainment_frag,
      PlaceFragment(cinema_list)
    ).addToBackStack(null)
      .commit()
    return root
  }

  private fun setTvSelectedItem() {
    //tvSelectedItem = root.findViewById(R.id.tv_selected_item)
  }

  private fun setHorizontalPicker() {
    rvHorizontalPicker = root.findViewById(R.id.rv_horizontal_picker)
    // Задаем отступ чтобы текст был по середине
    val padding: Int = ScreenUtils.getScreenWidth(root.context)/2 //- ScreenUtils.dpToPx(this, 40)
    rvHorizontalPicker.setPadding(padding, 0, padding, 0)
    rvHorizontalPicker

    // Setting layout manager
    rvHorizontalPicker.layoutManager = SliderLayoutManager(root.context).apply {
      callback = object : SliderLayoutManager.OnItemSelectedListener {
        override fun onItemSelected(layoutPosition: Int) {
          //tvSelectedItem.setText(data[layoutPosition])
          when(layoutPosition){
            0->{
              fragmentManager!!.beginTransaction().replace(
                R.id.nav_entertainment_frag,
                PlaceFragment(cinema_list)
              ).addToBackStack(null)
                .commit()
            }
            1->{
              fragmentManager!!.beginTransaction().replace(
                R.id.nav_entertainment_frag,
                PlaceFragment(park_list)
              ).addToBackStack(null)
                .commit()
            }
            2->{
              fragmentManager!!.beginTransaction().replace(
                R.id.nav_entertainment_frag,
                PlaceFragment(shop_center_list)
              ).addToBackStack(null)
                .commit()
            }
            3->{
              fragmentManager!!.beginTransaction().replace(
                R.id.nav_entertainment_frag,
                PlaceFragment(theatre_list)
              ).addToBackStack(null)
                .commit()
            }
            4->{
              fragmentManager!!.beginTransaction().replace(
                R.id.nav_entertainment_frag,
                PlaceFragment(museum_list)
              ).addToBackStack(null)
                .commit()
            }
          }
        }
      }
    }
    // Setting Adapter
    rvHorizontalPicker.adapter = SliderAdapter().apply {
      setData(data)
      callback = object : SliderAdapter.Callback {
        override fun onItemClicked(view: View) {
          //rvHorizontalPicker.smoothScrollToPosition(rvHorizontalPicker.getChildLayoutPosition(view))
        }
      }
    }
    (rvHorizontalPicker.layoutManager as SliderLayoutManager).firstorder()
  }
}
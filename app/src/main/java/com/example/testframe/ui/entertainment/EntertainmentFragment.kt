package com.example.testframe.ui.entertainment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.Utils
import com.example.newsapp.Utils.getScreenWidth
import com.example.testframe.R
import com.example.testframe.ui.*
import com.example.testframe.ui.entertainment.content.SliderAdapter
import com.example.testframe.ui.entertainment.content.SliderLayoutManager
import java.util.*


class EntertainmentFragment() : Fragment() {
  private lateinit var rvHorizontalPicker: RecyclerView
  private lateinit var root: View

  private val data = listOf<String>("Кинотеатр", "Парк", "ТЦ", "Театр", "Музей").map { it } as ArrayList<String>

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    root = inflater.inflate(R.layout.fragment_entertainment, container, false)
    setHorizontalPicker()
    return root
  }

  private fun setHorizontalPicker() {
    rvHorizontalPicker = root.findViewById(R.id.rv_horizontal_picker)
    // Задаем отступ чтобы текст был по середине
    val padding: Int = getScreenWidth(root.context)/2 - Utils.dpToPx(root.context, 40)
    rvHorizontalPicker.setPadding(padding, 0, padding, 0)


    rvHorizontalPicker.layoutManager = SliderLayoutManager(root.context).apply {
      callback = object : SliderLayoutManager.OnItemSelectedListener {
        override fun onItemSelected(layoutPosition: Int) {
          currPos = layoutPosition
          when (layoutPosition) {
              0 -> {
                activity?.let {
                  it.supportFragmentManager.beginTransaction().replace(
                    R.id.nav_entertainment_frag,
                    PlaceFragment(cinema_list)
                  ).addToBackStack(null)
                      .commit()
                }
              }
              1 -> {
                activity?.let {
                  it.supportFragmentManager.beginTransaction().replace(
                    R.id.nav_entertainment_frag,
                    PlaceFragment(park_list)
                  ).addToBackStack(null)
                    .commit()
                }
              }
              2 -> {
                activity?.let {
                  it.supportFragmentManager.beginTransaction().replace(
                    R.id.nav_entertainment_frag,
                    PlaceFragment(shop_center_list)
                  ).addToBackStack(null)
                    .commit()
                }
              }
              3 -> {
                activity?.let {
                  it.supportFragmentManager.beginTransaction().replace(
                    R.id.nav_entertainment_frag,
                    PlaceFragment(theatre_list)
                  ).addToBackStack(null)
                    .commit()
                }
              }
              4 -> {
                activity?.let {
                  it.supportFragmentManager.beginTransaction().replace(
                    R.id.nav_entertainment_frag,
                    PlaceFragment(museum_list)
                  ).addToBackStack(null)
                    .commit()
                  currPos+=1
                }
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
          rvHorizontalPicker.layoutManager!!.smoothScrollToPosition(
            rvHorizontalPicker,
            RecyclerView.State(),
            rvHorizontalPicker.getChildLayoutPosition(view)
          )
        }
      }
    }

  }

}
package com.example.testframe.ui.news

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.newsapp.Adapter
import com.example.newsapp.Utils
import com.example.newsapp.api.ApiClient
import com.example.newsapp.api.ApiInterface
import com.example.newsapp.models.Article
import com.example.newsapp.models.News
import com.example.testframe.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class NewsFragment : Fragment() {

  private lateinit var newsViewModel: NewsViewModel


  val API_KEY = "55329e4f9ebc4c5e86473d8bd9166d47"
  private var recyclerView: RecyclerView? = null
  private var layoutManager: RecyclerView.LayoutManager? = null
  private var articles: List<Article> = ArrayList<Article>()
  private var adapter: Adapter? = null
  private var topHeadline: TextView? = null
  private var swipeRefreshLayout: SwipeRefreshLayout? = null
  private var errorLayout: RelativeLayout? = null
  private var errorImage: ImageView? = null
  private var errorTitle: TextView? = null
  private var errorMessage:TextView? = null
  private var btnRetry: Button? = null
  private var root:View? = null
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    newsViewModel =
      ViewModelProviders.of(this).get(NewsViewModel::class.java)
    val root_t = inflater.inflate(R.layout.fragment_news, container, false)
    //activity?.setContentView(R.layout.fragment_news)
    newsViewModel.text.observe(viewLifecycleOwner, Observer {
    })
    swipeRefreshLayout = root_t.findViewById(R.id.swipe_refresh_layout)
    swipeRefreshLayout!!.setOnRefreshListener{this}
    swipeRefreshLayout!!.setColorSchemeResources(R.color.colorAccent)

    topHeadline = root_t.findViewById(R.id.topheadelines)
    recyclerView = root_t.findViewById(R.id.recyclerView)
    layoutManager = LinearLayoutManager(context)
    recyclerView!!.layoutManager = layoutManager
    recyclerView!!.itemAnimator = DefaultItemAnimator()
    recyclerView!!.isNestedScrollingEnabled = false

    onLoadingSwipeRefresh("")
    errorLayout = root_t.findViewById(R.id.errorLayout)
    errorImage = root_t.findViewById(R.id.errorImage)
    errorTitle = root_t.findViewById(R.id.errorTitle)
    errorMessage = root_t.findViewById(R.id.errorMessage)
    btnRetry = root_t.findViewById(R.id.btnRetry)
    return root_t
  }

  fun LoadJson(keyword: String){
    errorLayout!!.visibility = View.GONE
    swipeRefreshLayout!!.isRefreshing = true
    val apiInterface: ApiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)

    val country = Utils.getCountry()
    val language = Utils.getLanguage()

    val call: Call<News>
    call = apiInterface.getNewsSearch("Белгород", language, "publishedAt", this.API_KEY)

    call.enqueue(object : Callback<News> {
      override fun onResponse(call: Call<News>, response: Response<News>) {
        if (response.isSuccessful && response.body()!!.article != null) {

          articles = response.body()!!.article
          adapter = Adapter(articles, context!!)
          recyclerView!!.adapter = adapter
          adapter!!.notifyDataSetChanged()

          initListener()

          topHeadline!!.visibility = View.VISIBLE
          swipeRefreshLayout!!.isRefreshing = false
        } else {
          topHeadline!!.visibility = View.INVISIBLE
          swipeRefreshLayout!!.isRefreshing = false

          val errorCode: String
          errorCode = when (response.code()) {
            404 -> "404 not found"
            500 -> "500 server broken"
            else -> "unknown error"
          }

          showErrorMessage(
            R.drawable.no_result,
            "No Result",
            """
                            Please Try Again!
                            $errorCode
                            """.trimIndent()
          )
          Toast.makeText(context, "No Result", Toast.LENGTH_SHORT).show()
        }
      }

      override fun onFailure(call: Call<News>, t: Throwable) {
        topHeadline!!.visibility = View.INVISIBLE
        swipeRefreshLayout!!.isRefreshing = false
        showErrorMessage(
          R.drawable.oops,
          "Oops..",
          """
                        Network failure, Please Try Again
                        $t
                        """.trimIndent()
        )
      }
    })
  }

  private fun initListener(){
    adapter?.onItemClickListener = object: Adapter.OnItemClickListener{
      override fun onItemClick(view: View, position: Int) {
        val imageView: ImageView = view.findViewById(R.id.img)
        var article: Article = articles.get(position)

        val i:Intent = Intent(Intent.ACTION_VIEW)
        i.setData(Uri.parse(article.url))
        startActivity(i)

      }
    }
  }


  private fun onLoadingSwipeRefresh(keyword: String) {
    swipeRefreshLayout!!.post { LoadJson(keyword) }
  }

  private fun showErrorMessage(imageView: Int, title: String, message: String) {
    if (errorLayout!!.visibility == View.GONE) {
      errorLayout!!.visibility = View.VISIBLE
    }
    errorImage!!.setImageResource(imageView)
    errorTitle!!.text = title
    errorMessage!!.text = message
    btnRetry!!.setOnClickListener { onLoadingSwipeRefresh("") }
  }

  //override fun onRefresh() = LoadJson("")


}
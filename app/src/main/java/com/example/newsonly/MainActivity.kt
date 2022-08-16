package com.example.newsonly

import android.app.DownloadManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.bumptech.glide.request.Request
import org.json.JSONObject
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NewsItemClicked {

    private lateinit var mAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview.layoutManager=LinearLayoutManager(this)
        fetchdata()
        mAdapter= NewsListAdapter(this)
        recyclerview.adapter=mAdapter
    }
    private fun fetchdata(){
        val url="https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=3fadc840cb4943eea2ecc1633f89e440"
        val jsonObjectRequest = JsonObjectRequest(
            com.android.volley.Request.Method.GET,
            url,
            null,
            Response.Listener{
               val newsJsonArray = it.getJSONArray("articles")
                val newsArray = ArrayList<News>()
                for (i in 0 until newsJsonArray.length())
                {
                  val newsJsonObject=newsJsonArray.getJSONObject(i)
                    val news=News(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage")
                    )
                    newsArray.add(news)
                }
                mAdapter.updateNews(newsArray)
            },
            Response.ErrorListener {

            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClicked(item: News) {

        val builder = CustomTabsIntent.Builder()
       val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url));

    }
}

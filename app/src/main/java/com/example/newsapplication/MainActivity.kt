package com.example.newsapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.domain.dto.NewsDto
import com.example.newsapplication.service.NewsAdapter
import com.example.newsapplication.service.NewsService
import com.example.newsapplication.service.NewsServiceImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {

    lateinit var searchBar: EditText
    lateinit var searchButton: Button
    lateinit var recyclerView: RecyclerView
    lateinit var newsAdapter: NewsAdapter
    lateinit var newsService : NewsService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        newsService = NewsServiceImpl(resources.getString(R.string.api_key))
        searchBar = findViewById(R.id.search_bar)
        searchButton = findViewById(R.id.bSubmit)
        recyclerView = findViewById(R.id.my_recycler_view)

        newsAdapter = NewsAdapter(this, emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = newsAdapter
        updateNews(newsService.getNews())

        searchButton.setOnClickListener {
            val keyWords = searchBar.text.toString()
            searchBar.setText("")
            updateNews(newsService.getNews(keyWords))
        }
    }

    private fun updateNews(responseCall: Call<NewsDto>) {
        Log.d("MainActivity", "getting news")
        responseCall.enqueue(object : Callback<NewsDto> {

            override fun onFailure(call: Call<NewsDto>, t: Throwable) {
                Log.e("MainActivity", "Error while fetching news. Request: ${call.request()}\nException: ${t}")
            }

            override fun onResponse(call: Call<NewsDto>, response: Response<NewsDto>) {
                Log.d("MainActivity", "Successfully get response ${response}")
                newsAdapter.updateNews(response.body()?.results ?: emptyList())
            }
        })
    }
}


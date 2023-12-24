package com.example.newsapplication.service

import com.example.newsapplication.domain.dto.NewsDto
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Call
import retrofit2.Retrofit


@OptIn(ExperimentalSerializationApi::class)
class NewsServiceImpl(val apiKey: String) : NewsService {

    val retrofit: Retrofit
    val newsClient: NewsClient
    val baseUrl = "https://newsdata.io/"
    val countries = "ru,us"
    private val contentType = "application/json".toMediaType()

    init {
        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
            explicitNulls = false
        }
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
        newsClient = retrofit.create(NewsClient::class.java)
    }

    override fun getNews(keyWord: String) : Call<NewsDto> {
        if (keyWord.isBlank()) {
            return getNews()
        }
        return newsClient.getNewsWithKeywords(apiKey, keyWord, countries)
    }

    override fun getNews(): Call<NewsDto> =
        newsClient.getNews(apiKey, countries)

}
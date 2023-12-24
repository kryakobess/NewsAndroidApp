package com.example.newsapplication.service

import com.example.newsapplication.domain.dto.NewsDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsClient {

    @GET("api/1/news")
    fun getNewsWithKeywords (
        @Query("apikey") key: String,
        @Query("qInTitle") searchPhrase: String,
        @Query("country") country: String
    ) : Call<NewsDto>

    @GET("api/1/news")
    fun getNews (
        @Query("apikey") key: String,
        @Query("country") country: String
    ) : Call<NewsDto>
}
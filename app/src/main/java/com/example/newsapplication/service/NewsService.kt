package com.example.newsapplication.service

import com.example.newsapplication.domain.dto.NewsDto
import retrofit2.Call

interface NewsService {
    fun getNews(keyWord: String) : Call<NewsDto>
    fun getNews() : Call<NewsDto>
}
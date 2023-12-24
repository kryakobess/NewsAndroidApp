package com.example.newsapplication.domain.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsDto (
    val results: List<NewsInfo>
) {
    @Serializable
    @Parcelize
    data class NewsInfo (
        @SerialName("title")
        var title: String? = "",
        @SerialName("link")
        var link: String? = "",
        @SerialName("description")
        var description: String? = "",
        @SerialName("content")
        var content: String? = "",
        @SerialName("image_url")
        val imageUrl: String?,
        @SerialName("category")
        var categories: List<String>? = emptyList(),
        @SerialName("country")
        var countries: List<String>? = emptyList(),
        @SerialName("creator")
        var creators: List<String>? = emptyList()
    ) : Parcelable
}

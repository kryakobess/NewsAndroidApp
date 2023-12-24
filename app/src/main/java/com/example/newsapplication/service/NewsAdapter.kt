package com.example.newsapplication.service

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newsapplication.PageActivity
import com.example.newsapplication.R
import com.example.newsapplication.domain.dto.NewsDto
import com.squareup.picasso.Picasso

class NewsAdapter(
    var context: Context,
    var news: List<NewsDto.NewsInfo>
) : RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    private val backgroundColors : List<String> = listOf("#EB9263","#B4EB63","#639BEB","#EB63A4")

    override fun getItemCount(): Int = news.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return NewsHolder(view)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        Log.d("NewsAdapter", "position: ${position}")
        holder.title.text = news[position].title
        holder.description.text = news[position].description
        Picasso.get().load(news[position].imageUrl).into(holder.image)
        holder.itemView.setBackgroundColor(getBackgroundColor(position))
        holder.itemView.setOnClickListener {
            val openPageIntent = Intent(context, PageActivity::class.java)
            openPageIntent.putExtra("newsPage", news[position])
            context.startActivity(openPageIntent)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public fun updateNews(news: List<NewsDto.NewsInfo>) {
        this.news = news
        notifyDataSetChanged()
    }

    private fun getBackgroundColor(position: Int) : Int =
        Color.parseColor(backgroundColors[position % backgroundColors.size])

    class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var description: TextView
        var image: ImageView

        init {
            title = itemView.findViewById(R.id.title)
            description = itemView.findViewById(R.id.description)
            image = itemView.findViewById(R.id.image)
        }
    }
}
package com.example.newsapplication

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import coil.load
import com.example.newsapplication.domain.dto.NewsDto
import com.squareup.picasso.Picasso

class PageActivity : ComponentActivity() {

    lateinit var title: TextView
    lateinit var image: ImageView
    lateinit var content: TextView
    lateinit var tags: TextView
    lateinit var creator: TextView
    lateinit var country: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page)
        title = findViewById(R.id.page_title)
        image = findViewById(R.id.page_image)
        content = findViewById(R.id.page_content)
        tags = findViewById(R.id.page_tags)
        creator = findViewById(R.id.page_creator)
        country = findViewById(R.id.page_country)

        val newsInfo = intent.getParcelableExtra("newsPage", NewsDto.NewsInfo::class.java)
        newsInfo?.let {
            setTextOrMakeInvisible(title, it.title)
            setTextOrMakeInvisible(content, it.content)
            setTextOrMakeInvisible(creator, it.creators?.joinToString(", ") ?: "")
            setTextOrMakeInvisible(tags, it.categories?.joinToString(", ") ?: "")
            setTextOrMakeInvisible(country, it.countries?.joinToString(", ") ?: "")
            Picasso.get().load(it.imageUrl).into(image)
        }
    }

    private fun setTextOrMakeInvisible(view: TextView, text: String?) {
        if (text.isNullOrBlank()) {
            view.visibility = TextView.INVISIBLE
        } else {
            view.text = text
        }
    }
}
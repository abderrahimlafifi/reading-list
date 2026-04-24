package com.raydogs.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raydogs.app.R
import com.raydogs.app.model.Post

class PostAdapter(
    private val onPostClick: (Post) -> Unit
) : ListAdapter<Post, PostAdapter.PostViewHolder>(DIFF) {

    class PostViewHolder(view: View, onClick: (Post) -> Unit) : RecyclerView.ViewHolder(view) {
        private val thumbnail: ImageView = view.findViewById(R.id.post_thumbnail)
        private val title: TextView     = view.findViewById(R.id.post_title)
        private val excerpt: TextView   = view.findViewById(R.id.post_excerpt)
        private val date: TextView      = view.findViewById(R.id.post_date)
        private var post: Post? = null

        init { view.setOnClickListener { post?.let(onClick) } }

        fun bind(p: Post) {
            post = p
            title.text   = HtmlCompat.fromHtml(p.title.rendered,   HtmlCompat.FROM_HTML_MODE_COMPACT)
            excerpt.text = HtmlCompat.fromHtml(p.excerpt.rendered, HtmlCompat.FROM_HTML_MODE_COMPACT)
                .toString().trim()
            date.text = p.date.take(10)

            val imageUrl = p.embedded?.featuredMedia
                ?.firstOrNull { it.sourceUrl.isNotBlank() }
                ?.let { m -> m.mediaDetails?.sizes?.get("medium")?.sourceUrl ?: m.sourceUrl }

            if (!imageUrl.isNullOrBlank()) {
                thumbnail.visibility = View.VISIBLE
                Glide.with(thumbnail.context).load(imageUrl).centerCrop().into(thumbnail)
            } else {
                thumbnail.visibility = View.GONE
                Glide.with(thumbnail.context).clear(thumbnail)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false),
            onPostClick
        )

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) = holder.bind(getItem(position))

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(a: Post, b: Post) = a.id == b.id
            override fun areContentsTheSame(a: Post, b: Post) = a == b
        }
    }
}

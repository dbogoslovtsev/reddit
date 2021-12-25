package com.example.reddit.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.example.domain.entity.DataWrapper
import com.example.domain.entity.Post
import com.example.reddit.R
import com.example.reddit.glide.GlideApp
import com.example.reddit.presentation.utils.DateUtils
import kotlinx.android.synthetic.main.item_post.view.*

class PostAdapter(val onItemClicked: (url: String) -> Unit,
                  private val onLastPostReached: (url: String?) -> Unit) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    var posts: MutableList<DataWrapper<Post>> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false))
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentPost = posts[position]
        if (position == posts.size - 1) onLastPostReached.invoke(currentPost.data?.id)
        holder.bindPost(posts[position])
    }

    fun addPosts(newPosts: List<DataWrapper<Post>>) {
        posts.addAll(newPosts)
        notifyItemRangeInserted(posts.size - newPosts.size, newPosts.size)
    }

    inner class PostViewHolder(layout: View) : RecyclerView.ViewHolder(layout) {

        fun bindPost(post: DataWrapper<Post>) {
            post.data?.apply {
                itemView.title.text = title
                itemView.subreddit.text = subredditPrefixed
                itemView.userName.text = author
                itemView.timestamp.text = DateUtils.getTimeLeftReadable(created_utc)
                itemView.rating.text = score
                itemView.comments.text = num_comments

                if (url.contains(".gifv")) url = url.replace(".gifv", ".gif")
                GlideApp.with(itemView).apply {
                    if (url.contains("gif")) {
                        asGif()
                    }
                    load(url)
                            .diskCacheStrategy(DiskCacheStrategy.DATA)
                            .into(DrawableImageViewTarget(itemView.image))
                }

                itemView.title.setOnClickListener { onItemClicked.invoke(url) }
                itemView.image.setOnClickListener { onItemClicked.invoke(url) }
            }
        }


    }

}

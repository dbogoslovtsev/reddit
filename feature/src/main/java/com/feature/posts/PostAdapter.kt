package com.feature.posts

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.core.glide.GlideApp
import com.core.utils.DateUtils
import com.domain.entity.DataWrapper
import com.domain.entity.Post
import com.reddit.feature.databinding.ItemPostBinding

class PostAdapter(
    val onItemClicked: (url: String) -> Unit,
    private val onLastPostReached: (url: String?) -> Unit
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private var posts: MutableList<DataWrapper<Post>> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(ItemPostBinding.inflate(LayoutInflater.from(parent.context)))
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

    inner class PostViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("CheckResult")
        fun bindPost(post: DataWrapper<Post>) {
            post.data?.apply {
                binding.title.text = title
                binding.subreddit.text = subredditPrefixed
                binding.userName.text = author
                binding.timestamp.text = DateUtils.getTimeLeftReadable(created_utc)
                binding.rating.text = score
                binding.comments.text = num_comments

                if (url.contains(".gifv")) url = url.replace(".gifv", ".gif")
                GlideApp.with(binding.root.context).apply {
                    if (url.contains("gif")) {
                        asGif()
                    }
                    load(url)
                        .diskCacheStrategy(DiskCacheStrategy.DATA)
                        .into(DrawableImageViewTarget(binding.image))
                }

                binding.title.setOnClickListener { onItemClicked.invoke(url) }
                binding.image.setOnClickListener { onItemClicked.invoke(url) }
            }
        }
    }

}

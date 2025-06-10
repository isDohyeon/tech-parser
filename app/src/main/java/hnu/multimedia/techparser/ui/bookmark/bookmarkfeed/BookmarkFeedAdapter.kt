package hnu.multimedia.techparser.ui.bookmark.bookmarkfeed

import hnu.multimedia.techparser.ui.feed.WebViewActivity

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import hnu.multimedia.techparser.databinding.ItemPostBinding
import hnu.multimedia.techparser.rss.model.RssFeedModel
import hnu.multimedia.techparser.util.FirebaseRef
import hnu.multimedia.techparser.util.Utils

class BookmarkFeedAdapter(
    private val feeds: List<RssFeedModel>
) : RecyclerView.Adapter<BookmarkFeedAdapter.ViewHolder>(){

    class ViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemPostBinding.inflate(inflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return feeds.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textViewPostTitle.text = feeds[position].title
        holder.binding.textViewBlogName.text = feeds[position].blogName
        Glide.with(holder.binding.root.context)
            .load(feeds[position].logoUrl)
            .into(holder.binding.imageViewBlogLogo)

        onShortClick(holder, position)

        holder.binding.textViewPubDate.text = Utils.formatPubDate(feeds[position].pubDate)
    }

    private fun onShortClick(
        holder: ViewHolder,
        position: Int
    ) {
        holder.binding.root.setOnClickListener {
            val intent = Intent(holder.binding.root.context, WebViewActivity::class.java)
            intent.putExtra("originalURL", feeds[position].link)
            holder.binding.root.context.startActivity(intent, null)
        }
    }
}
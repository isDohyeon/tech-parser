package hnu.multimedia.techparser.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hnu.multimedia.techparser.databinding.ItemPostBinding
import hnu.multimedia.techparser.rss.model.ParsedFeedModel

class FeedAdapter(
    private val feeds: List<ParsedFeedModel>
) : RecyclerView.Adapter<FeedAdapter.ViewHolder>(){

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
            .load(feeds[position].logoURL)
            .into(holder.binding.imageViewBlogLogo)

        holder.binding.root.setOnClickListener {
            val intent = Intent(holder.binding.root.context, WebViewActivity::class.java)
            intent.putExtra("originalURL", feeds[position].originalURL)
            holder.binding.root.context.startActivity(intent, null)
        }
    }
}
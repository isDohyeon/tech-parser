package hnu.multimedia.techparser.ui.feed

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hnu.multimedia.techparser.databinding.ItemPostBinding
import hnu.multimedia.techparser.rss.model.RssFeedModel
import java.text.SimpleDateFormat
import java.util.Locale

class FeedAdapter(
    private val feeds: List<RssFeedModel>
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
            .load(feeds[position].logoUrl)
            .into(holder.binding.imageViewBlogLogo)

        onShortClick(holder, position)

        onLongClick()

        holder.binding.textViewPubDate.text = formatPubDate(feeds[position].pubDate)
    }

    private fun onLongClick() {


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

    private fun formatPubDate(rawDate: String?): String {
        if (rawDate == null) return ""

        val inputFormats = listOf(
            SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH),
            SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH)
        )
        val outputFormat = SimpleDateFormat("yyyy년 M월 d일", Locale.KOREAN)

        for (format in inputFormats) {
            try {
                val date = format.parse(rawDate)
                if (date != null) return outputFormat.format(date)
            } catch (_: Exception) {}
        }

        return rawDate
    }
}
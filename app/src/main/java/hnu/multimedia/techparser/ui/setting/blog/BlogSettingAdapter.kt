package hnu.multimedia.techparser.ui.setting.blog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hnu.multimedia.techparser.databinding.ItemNotificationBlogBinding
import hnu.multimedia.techparser.rss.RssRepository
import hnu.multimedia.techparser.util.FirebaseRef

class BlogSettingAdapter(
    private val blogs: List<String>,
    private val settings: Map<String, Boolean>
) : RecyclerView.Adapter<BlogSettingAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemNotificationBlogBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNotificationBlogBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() : Int {
        return blogs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val blogName = blogs[position]
        holder.binding.textViewBlogNameSetting.text = blogName
        holder.binding.switchBlogSetting.isChecked = settings[blogName] ?: true
        val matchedBlog = RssRepository.blogs.find { it.name == blogName }
        Glide.with(holder.binding.root.context)
            .load(matchedBlog?.logoUrl)
            .into(holder.binding.imageViewBlogLogoSetting)

        holder.binding.switchBlogSetting.setOnCheckedChangeListener { _, isChecked ->
            FirebaseRef.notificationBlogRef().child(blogName).setValue(isChecked)
        }
    }
}
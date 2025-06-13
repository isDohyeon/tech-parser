package hnu.multimedia.techparser.ui.subscribe

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import hnu.multimedia.techparser.databinding.ItemBlogBinding
import hnu.multimedia.techparser.ui.feed.WebViewActivity
import hnu.multimedia.techparser.ui.subscribe.model.BlogModel
import hnu.multimedia.techparser.util.FirebaseRef

class SubscribeAdapter(
    private var blogs: MutableList<BlogModel>,
    private val isSubscribe: Boolean
) : RecyclerView.Adapter<SubscribeAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemBlogBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemBlogBinding.inflate(inflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return blogs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val blogName = blogs[position].name

        holder.binding.textViewBlogName2.text = blogName
        Glide.with(holder.binding.root.context)
            .load(blogs[position].logoUrl)
            .into(holder.binding.imageViewBlogLogo2)
        if (isSubscribe) {
            holder.binding.imageViewCheck.visibility = View.VISIBLE
            holder.binding.imageViewUncheck.visibility = View.GONE
        } else {
            holder.binding.imageViewCheck.visibility = View.GONE
            holder.binding.imageViewUncheck.visibility = View.VISIBLE
        }

        holder.binding.imageViewCheck.setOnClickListener {
            FirebaseRef.subscribeRef().child(blogName).removeValue()
            Toast.makeText(holder.binding.root.context, "$blogName 블로그를 구독 해지하였습니다.", Toast.LENGTH_SHORT).show()
        }
        holder.binding.imageViewUncheck.setOnClickListener {
            FirebaseRef.subscribeRef().child(blogName).setValue(true)
            Toast.makeText(holder.binding.root.context, "$blogName 블로그를 구독하였습니다.", Toast.LENGTH_SHORT).show()
        }

        holder.binding.root.setOnClickListener {
            val intent = Intent(holder.binding.root.context, WebViewActivity::class.java)
            intent.putExtra("originalURL", blogs[position].url)
            holder.binding.root.context.startActivity(intent, null)
        }
    }
}

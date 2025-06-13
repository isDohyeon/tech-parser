package hnu.multimedia.techparser.ui.bookmark.bookmarkfeed

import hnu.multimedia.techparser.ui.feed.WebViewActivity

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import hnu.multimedia.techparser.databinding.ItemPostBinding
import hnu.multimedia.techparser.rss.model.RssFeedModel
import hnu.multimedia.techparser.util.FirebaseRef
import hnu.multimedia.techparser.util.Utils

class BookmarkFeedAdapter(
    private val feeds: MutableList<RssFeedModel>,
    private val folderName: String
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

        holder.binding.root.setOnLongClickListener {
            val title = feeds[position].title
            AlertDialog.Builder(holder.binding.root.context)
                .setTitle("북마크 피드 삭제")
                .setMessage("북마크한 피드 \"$title\"을(를) 삭제하시겠습니까?")
                .setPositiveButton("예") { _, _ ->
                    deleteBookmarkFeed(position)
                    Toast.makeText(holder.binding.root.context, "피드가 삭제되었습니다.", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("아니오", null)
                .show()
            true
        }
    }

    private fun deleteBookmarkFeed(position: Int) {
        val folderRef = FirebaseRef.bookmarksRef().child(folderName)
        Log.d("BookmarkFeedAdapter", "folderName: $folderName")
        folderRef.get().addOnSuccessListener { snapshot ->
            for (child in snapshot.children) {
                val value = child.getValue(Long::class.java)?.toInt()
                if (feeds[position].id == value) {
                    folderRef.child(child.key!!).removeValue()
                    break
                }
            }
        }
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
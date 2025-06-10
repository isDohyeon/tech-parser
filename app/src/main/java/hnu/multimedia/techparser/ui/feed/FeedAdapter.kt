package hnu.multimedia.techparser.ui.feed

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
        onLongClick(holder, position)

        holder.binding.textViewPubDate.text = Utils.formatPubDate(feeds[position].pubDate)
    }

    private fun onLongClick(holder: ViewHolder, position: Int) {
        holder.binding.root.setOnLongClickListener {
            val context = holder.binding.root.context

            FirebaseRef.bookmarksRef().get().addOnSuccessListener { snapshot ->
                val folderArray = getFolderArray(snapshot)
                createAlertDialog(context, folderArray, position)
            }
            true
        }
    }

    private fun createAlertDialog(
        context: Context,
        folderArray: Array<String>,
        position: Int
    ) {
        var selectedIndex = -1
        AlertDialog.Builder(context)
            .setTitle("북마크할 폴더를 선택하세요")
            .setSingleChoiceItems(folderArray, selectedIndex) { _, which ->
                selectedIndex = which
            }
            .setPositiveButton("확인") { dialog, _ ->
                if (selectedIndex >= 0) {
                    bookmarkFeedInFolder(folderArray, selectedIndex, position, context)
                }
                dialog.dismiss()
            }
            .setNegativeButton("취소") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun bookmarkFeedInFolder(
        folderArray: Array<String>,
        selectedIndex: Int,
        position: Int,
        context: Context
    ) {
        val selectedFolder = folderArray[selectedIndex]
        val selectedPostId = feeds[position].id
        Utils.findSavedFolder(selectedFolder) { folderName ->
            if (folderName.isNotEmpty()) {
                FirebaseRef.bookmarkFolderRef(folderName)
                    .push()
                    .setValue(selectedPostId)

                Toast.makeText(
                    context,
                    "'${selectedFolder}' 폴더에 추가됨",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(context, "폴더를 찾지 못했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getFolderArray(snapshot: DataSnapshot): Array<String> {
        val folderNames = mutableListOf<String>()
        for (child in snapshot.children) {
            val savedFolder = child.key.toString()
            val folderName = Utils.removeTimeStamp(savedFolder)
            folderNames.add(folderName)
        }
        return folderNames.toTypedArray()
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
package hnu.multimedia.techparser.ui.bookmark

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import hnu.multimedia.techparser.databinding.ItemFolderBinding
import hnu.multimedia.techparser.ui.bookmark.bookmarkfeed.BookmarkFeedActivity
import hnu.multimedia.techparser.util.FirebaseRef
import hnu.multimedia.techparser.util.Utils

class BookmarkAdapter(
    private var bookmarkFolders: MutableList<String>
) : RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemFolderBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemFolderBinding.inflate(inflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return bookmarkFolders.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val folderName = bookmarkFolders[position]
        holder.binding.textViewFolderName.text = folderName
        holder.binding.root.setOnLongClickListener {
            deleteFolder(holder, folderName)
        }
        holder.binding.root.setOnClickListener {
            val intent = Intent(holder.binding.root.context, BookmarkFeedActivity::class.java)
            intent.putExtra("folderName", folderName)
            holder.binding.root.context.startActivity(intent)
        }
    }

    private fun deleteFolder(
        holder: ViewHolder,
        folderName: String
    ): Boolean {
        AlertDialog.Builder(holder.binding.root.context)
            .setTitle("폴더 삭제")
            .setMessage("폴더 \"$folderName\"을(를) 삭제하시겠습니까?")
            .setPositiveButton("예") { _, _ ->
                deleteBookmarkFolder(folderName)
                Toast.makeText(holder.binding.root.context, "$\"folderName\"가 삭제되었습니다.", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("아니오", null)
            .show()
        return true
    }

    private fun deleteBookmarkFolder(removeFolderName: String) {
        val ref = FirebaseRef.bookmarksRef()
        ref.get().addOnSuccessListener { snapshot ->
            for (child in snapshot.children) {
                val savedFolderName = child.key.toString()
                val bookmarkFolderName = Utils.removeTimeStamp(savedFolderName)
                if (bookmarkFolderName == removeFolderName) {
                    ref.child(savedFolderName).removeValue()
                    break

                }
            }
        }
    }
}

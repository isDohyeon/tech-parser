package hnu.multimedia.techparser.ui.bookmark

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hnu.multimedia.techparser.databinding.ItemFolderBinding

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
        for (bookmarkFolder in bookmarkFolders) {
            Log.d("bookmarkFolder", "bookmarkFolders: $bookmarkFolders")
        }
        holder.binding.textViewFolderName.text = bookmarkFolders[position]
    }
}
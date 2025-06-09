package hnu.multimedia.techparser.ui.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hnu.multimedia.techparser.databinding.ItemFolderBinding

class BookmarkAdapter(
    private val bookmarkFolders: List<String>
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
        holder.binding.textViewFolderName.text = bookmarkFolders[position]
    }
}
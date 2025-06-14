package hnu.multimedia.techparser.ui.setting.keyword

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import hnu.multimedia.techparser.databinding.ItemKeywordBinding
import hnu.multimedia.techparser.databinding.ItemNotificationBlogBinding
import hnu.multimedia.techparser.ui.setting.blog.BlogSettingAdapter.ViewHolder
import hnu.multimedia.techparser.util.FirebaseRef
import kotlinx.coroutines.tasks.await

class KeywordSettingAdapter(
    private val keywords: List<String>
) : RecyclerView.Adapter<KeywordSettingAdapter.ViewHolder>(){

    class ViewHolder(val binding: ItemKeywordBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemKeywordBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return keywords.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val keyword = keywords[position]
        holder.binding.textViewKeyword.text = keyword

        holder.binding.imageViewDeleteKeyword.setOnClickListener {
            AlertDialog.Builder(holder.binding.root.context)
                .setTitle("키워드 삭제")
                .setMessage("키워드 \"$keyword\"을(를) 삭제하시겠습니까?")
                .setPositiveButton("예") {_, _ ->
                    removeKeyword(keyword, holder)
                    Toast.makeText(
                        holder.binding.root.context, "$keyword 키워드가 삭제되었습니다.", Toast.LENGTH_SHORT)
                        .show()
                }
                .setNegativeButton("아니오", null)
                .show()
        }
    }

    private fun removeKeyword(
        keyword: String,
        holder: ViewHolder
    ) {
        val keywordRef = FirebaseRef.keywordRef()
        keywordRef.get().addOnSuccessListener { snapshot ->
            for (child in snapshot.children) {
                val value = child.getValue(String::class.java)
                value?.let {
                    if (value == keyword) {
                        keywordRef.child(child.key.toString()).removeValue()

                    }
                }
            }
        }
    }
}
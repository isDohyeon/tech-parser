package hnu.multimedia.techparser.ui.bookmark

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import hnu.multimedia.techparser.databinding.FragmentBookmarkBinding
import hnu.multimedia.techparser.util.FirebaseRef
import hnu.multimedia.techparser.util.Utils

class BookmarkFragment : Fragment() {

    private val binding by lazy { FragmentBookmarkBinding.inflate(layoutInflater) }
    private val bookmarkFolders = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        getBookmarkFolders()
        binding.folderRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.folderRecyclerView.adapter = BookmarkAdapter(bookmarkFolders)

        binding.buttonAddFolder.setOnClickListener {
            showAddBookmarkDialog()
        }

        return binding.root
    }

    private fun showAddBookmarkDialog() {
        val editText = EditText(requireContext()).apply {
            hint = " 폴더 이름을 입력하세요"
        }

        AlertDialog.Builder(requireContext())
            .setTitle("북마크 폴더 추가")
            .setView(editText)
            .setPositiveButton("추가") { _, _ ->
                val folderName = editText.text.toString().trim()
                if (folderName.isNotEmpty()) {
                    addBookmarkFolder(folderName)
                }
            }
            .setNegativeButton("취소", null)
            .show()
    }

    private fun addBookmarkFolder(folderName: String) {
        val timeStamp = System.currentTimeMillis().toString()
        val saveFolderName = "${timeStamp}_${folderName}"
        FirebaseRef.bookmarksRef().child(saveFolderName).setValue(true)
    }

    private fun getBookmarkFolders() {
        val ref = FirebaseRef.bookmarksRef()
        val postListener = object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                bookmarkFolders.clear()
                for (bookmarkFolderName in snapshot.children) {
                    val savedFolderName = bookmarkFolderName.key.toString()
                    val folderName = Utils.removeTimeStamp(savedFolderName)
                    bookmarkFolders.add(folderName)
                }
                binding.folderRecyclerView.adapter?.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {}
        }
        ref.addValueEventListener(postListener)
    }
}
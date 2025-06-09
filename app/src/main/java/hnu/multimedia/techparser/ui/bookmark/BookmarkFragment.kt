package hnu.multimedia.techparser.ui.bookmark

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.snapshots
import hnu.multimedia.techparser.databinding.FragmentBookmarkBinding
import hnu.multimedia.techparser.util.FirebaseRef

class BookmarkFragment : Fragment() {

    private val binding by lazy { FragmentBookmarkBinding.inflate(layoutInflater) }
    private val bookmarkFolders = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        getUserBookmarkFolders()
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = BookmarkAdapter(bookmarkFolders)

        return binding.root
    }

    private fun getUserBookmarkFolders() {
        val ref = FirebaseRef.userBookmarksRef()
        val postListener = object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                bookmarkFolders.clear()
                for (bookmarkFolderName in snapshot.children) {
                    bookmarkFolders.add(bookmarkFolderName.key.toString())
                }
                binding.recyclerView.adapter?.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {}
        }
        ref.addValueEventListener(postListener)
    }
}
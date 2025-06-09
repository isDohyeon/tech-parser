package hnu.multimedia.techparser.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import hnu.multimedia.techparser.databinding.FragmentBookmarkBinding

class BookmarkFragment : Fragment() {

    private val binding by lazy { FragmentBookmarkBinding.inflate(layoutInflater) }
    private var bookmarkFolders = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        bookmarkFolders = getUserBookmarkFolders()
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = BookmarkAdapter(bookmarkFolders)

        return binding.root
    }

    private fun getUserBookmarkFolders(): MutableList<String> {
        return mutableListOf("AI", "백엔드", "프론트엔드")
    }
}
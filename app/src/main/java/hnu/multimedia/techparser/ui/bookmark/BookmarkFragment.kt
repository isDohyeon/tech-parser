package hnu.multimedia.techparser.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hnu.multimedia.techparser.databinding.FragmentBookmarkBinding

class BookmarkFragment : Fragment() {

    private val binding by lazy { FragmentBookmarkBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }
}
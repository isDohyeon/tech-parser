package hnu.multimedia.techparser.ui.feed

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import hnu.multimedia.techparser.TechParserApp
import hnu.multimedia.techparser.databinding.FragmentFeedBinding
import hnu.multimedia.techparser.rss.model.RssFeedModel
import hnu.multimedia.techparser.util.FirebaseRef

class FeedFragment : Fragment() {

    private val binding by lazy { FragmentFeedBinding.inflate(layoutInflater) }
    private var feeds = listOf<RssFeedModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        feeds = TechParserApp.feeds
        binding.folderRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        binding.folderRecyclerView.adapter = FeedAdapter(feeds)

        return binding.root
    }
}
package hnu.multimedia.techparser.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import hnu.multimedia.techparser.databinding.FragmentFeedBinding
import hnu.multimedia.techparser.rss.RssParser
import kotlinx.coroutines.launch

class FeedFragment : Fragment() {

    private val binding by lazy { FragmentFeedBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        lifecycleScope.launch {
            val feeds = RssParser.fetchRssFeeds()
            binding.recyclerView.adapter = FeedAdapter(feeds)
        }

        return binding.root
    }
}
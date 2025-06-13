package hnu.multimedia.techparser.ui.feed

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import hnu.multimedia.techparser.databinding.FragmentFeedBinding
import hnu.multimedia.techparser.rss.RssRepository
import hnu.multimedia.techparser.rss.model.RssFeedModel
import hnu.multimedia.techparser.util.FirebaseRef

class FeedFragment : Fragment() {

    private val binding by lazy { FragmentFeedBinding.inflate(layoutInflater) }
    companion object {
        var filteredFeeds = mutableListOf<RssFeedModel>()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        observeFeeds()
        binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        binding.recyclerView.adapter = FeedAdapter(filteredFeeds, lifecycleScope)

        return binding.root
    }

    private fun observeFeeds() {
        val ref = FirebaseRef.subscribeRef()
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val subscribeBlogs = snapshot.children.map { it.key.toString() }.toSet()
                updateFeeds(subscribeBlogs)
            }
            override fun onCancelled(error: DatabaseError) {}
        }
        ref.addValueEventListener(postListener)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateFeeds(subscribeBlogs: Set<String>) {
        val allFeeds = RssRepository.feeds
        filteredFeeds.clear()
        filteredFeeds.addAll(allFeeds.filter { it.blogName in subscribeBlogs })
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }
}
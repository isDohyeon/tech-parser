package hnu.multimedia.techparser.ui.feed

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import hnu.multimedia.techparser.databinding.FragmentFeedBinding
import hnu.multimedia.techparser.rss.RssRepository
import hnu.multimedia.techparser.rss.model.RssFeedModel
import hnu.multimedia.techparser.ui.feed.notification.NotificationLogActivity
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

        binding.imageViewSearch.setOnClickListener {
            if (binding.editTextSearch.visibility == View.GONE) {
                binding.editTextSearch.visibility = View.VISIBLE
                binding.textViewTitle.visibility = View.GONE
            } else {
                binding.editTextSearch.visibility = View.GONE
                binding.textViewTitle.visibility = View.VISIBLE
                binding.editTextSearch.text.clear()
                observeFeeds()
            }
        }

        binding.editTextSearch.addTextChangedListener { editable ->
            val query = editable.toString().trim()
            filterFeeds(query)
        }

        binding.imageViewNotification.setOnClickListener {
            val intent = Intent(binding.root.context, NotificationLogActivity::class.java)
            startActivity(intent)
        }

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

    @SuppressLint("NotifyDataSetChanged")
    private fun filterFeeds(query: String) {
        val searchResults = filteredFeeds.filter {
            it.title.contains(query, ignoreCase = true)
        }

        filteredFeeds.clear()
        filteredFeeds.addAll(searchResults)
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }
}
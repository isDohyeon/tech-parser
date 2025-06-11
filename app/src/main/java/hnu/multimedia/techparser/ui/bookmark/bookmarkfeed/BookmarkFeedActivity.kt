package hnu.multimedia.techparser.ui.bookmark.bookmarkfeed

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import hnu.multimedia.techparser.R
import hnu.multimedia.techparser.TechParserApp
import hnu.multimedia.techparser.databinding.ActivityBookmarkFeedBinding
import hnu.multimedia.techparser.rss.model.RssFeedModel
import hnu.multimedia.techparser.ui.feed.FeedFragment
import hnu.multimedia.techparser.util.FirebaseRef
import hnu.multimedia.techparser.util.Utils
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class BookmarkFeedActivity : AppCompatActivity() {

    private val binding by lazy { ActivityBookmarkFeedBinding.inflate(layoutInflater) }
    private val feeds = mutableListOf<RssFeedModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val folderName = intent.getStringExtra("folderName").toString()
        binding.textViewTitle.text = folderName
        binding.folderRecyclerView.layoutManager =
            LinearLayoutManager(this@BookmarkFeedActivity)
        binding.folderRecyclerView.adapter = BookmarkFeedAdapter(feeds, "")

        Utils.findSavedFolder(folderName) { savedFolder ->
            val bookmarkFolderRef = FirebaseRef.bookmarkFolderRef(savedFolder)
            val postListener = object : ValueEventListener {
                @SuppressLint("NotifyDataSetChanged")
                override fun onDataChange(snapshot: DataSnapshot) {
                    val ids = mutableListOf<Int>()
                    for (child in snapshot.children) {
                        val longValue = child.getValue(Long::class.java)
                        val value = longValue?.toInt()
                        value?.let {
                            ids.add(it)
                        }
                    }
                    feeds.clear()
                    for (id in ids) {
                        val item = FeedFragment.feeds[id]
                        feeds.add(item)
                    }
                    binding.folderRecyclerView.adapter = BookmarkFeedAdapter(feeds, savedFolder)
                }
                override fun onCancelled(error: DatabaseError) {}
            }
            bookmarkFolderRef.addValueEventListener(postListener)
        }
    }
}
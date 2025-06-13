package hnu.multimedia.techparser.rss

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import hnu.multimedia.techparser.rss.model.RssFeedModel
import hnu.multimedia.techparser.ui.subscribe.model.BlogModel
import hnu.multimedia.techparser.util.FirebaseRef
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

object RssRepository {
    private const val TAG = "RssRepository"

    var feeds: List<RssFeedModel> = emptyList()
        private set
    var blogs: List<BlogModel> = emptyList()
        private set


    suspend fun fetchFeeds() {
        try {
            Log.d(TAG, "fetchFeeds() - Firebase 데이터 요청 시작")
            val snapshot = FirebaseRef.feeds.get().await()
            feeds = snapshot.children.mapNotNull {
                val feed = it.getValue(RssFeedModel::class.java)
                Log.d(TAG, "feed 로드됨: $feed")
                feed
            }
            Log.d(TAG, "fetchFeeds() - 총 ${feeds.size}개의 피드 로드됨")
        } catch (e: Exception) {
            Log.e(TAG, "fetchFeeds() 중 오류 발생: ${e.message}", e)
        }
    }

    suspend fun fetchBlogs() {
        try {
            Log.d(TAG, "fetchBlogs() - Firebase 데이터 요청 시작")
            val snapshot = FirebaseRef.blogs.get().await()
            blogs = snapshot.children.mapNotNull {
                val blog = it.getValue(BlogModel::class.java)
                Log.d(TAG, "blog 로드됨: $blog")
                blog
            }
            Log.d(TAG, "fetchBlogs() - 총 ${blogs.size}개의 블로그 로드됨")
        } catch (e: Exception) {
            Log.e(TAG, "fetchBlogs() 중 오류 발생: ${e.message}", e)
        }
    }
}
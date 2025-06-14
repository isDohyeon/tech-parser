package hnu.multimedia.techparser.rss

import hnu.multimedia.techparser.rss.model.RssFeedModel
import hnu.multimedia.techparser.ui.subscribe.model.BlogModel
import hnu.multimedia.techparser.util.FirebaseRef
import kotlinx.coroutines.tasks.await

object RssRepository {

    var feeds: List<RssFeedModel> = emptyList()
        private set
    var blogs: List<BlogModel> = emptyList()
        private set


    suspend fun fetchFeeds() {
        val snapshot = FirebaseRef.feeds.get().await()
        feeds = snapshot.children.mapNotNull {
            it.getValue(RssFeedModel::class.java)
        }
    }

    suspend fun fetchBlogs() {
        val snapshot = FirebaseRef.blogs.get().await()
        blogs = snapshot.children.mapNotNull {
            it.getValue(BlogModel::class.java)
        }
    }
}
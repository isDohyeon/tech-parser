package hnu.multimedia.techparser.rss

import com.google.firebase.database.FirebaseDatabase
import hnu.multimedia.techparser.rss.model.RssFeedModel
import hnu.multimedia.techparser.rss.model.RssItem
import hnu.multimedia.techparser.rss.model.transform
import hnu.multimedia.techparser.rss.network.RssRetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

object RssParser {

    private val rssApiService = RssRetrofitClient.createRssService()

    private val dateFormats = listOf(
        SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH),
        SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH)
    )

    private fun parseDate(dateStr: String?): Date? {
        if (dateStr == null) return null
        for (format in dateFormats) {
            try {
                return format.parse(dateStr)
            } catch (_: Exception) {}
        }
        return null
    }

    private suspend fun fetchRssFeed(rssUrl: String): List<RssItem> = withContext(Dispatchers.IO) {
        try {
            val rssFeed = rssApiService.getRssFeed(rssUrl)
            rssFeed.channel.items ?: emptyList()
        } catch (_: Exception) {
            emptyList()
        }
    }

    suspend fun fetchRssFeeds(): List<RssFeedModel> = withContext(Dispatchers.IO) {
        val feeds = mutableListOf<RssFeedModel>()

        try {
            val snapshot = FirebaseDatabase.getInstance()
                .getReference("blogs")
                .get()
                .await()

            for (blogSnap in snapshot.children) {
                val blogName = blogSnap.child("name").getValue(String::class.java) ?: continue
                val rssUrl = blogSnap.child("rssUrl").getValue(String::class.java) ?: continue
                val logoUrl = blogSnap.child("logoUrl").getValue(String::class.java) ?: ""

                val items = fetchRssFeed(rssUrl)
                val feedModels = items.transform(blogName, logoUrl)

                feeds.addAll(feedModels)
            }

        } catch (_: Exception) { }

        return@withContext feeds.sortedByDescending { parseDate(it.pubDate) }
    }
}
package hnu.multimedia.techparser.rss

import hnu.multimedia.techparser.rss.model.RssFeedModel
import hnu.multimedia.techparser.rss.model.RssItem
import hnu.multimedia.techparser.rss.model.transform
import hnu.multimedia.techparser.rss.network.RssRetrofitClient
import hnu.multimedia.techparser.util.FirebaseRef
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

object RssParser {

    private val rssApiService = RssRetrofitClient.createRssService()

    suspend fun parseRssFeeds(): List<RssFeedModel> = withContext(Dispatchers.IO) {
        val feeds = mutableListOf<RssFeedModel>()

        val snapshot = FirebaseRef.blogs.get().await()
        val deferredList = snapshot.children.map { blogSnap ->
            async {
                val blogName = blogSnap.child("name").getValue(String::class.java) ?: return@async emptyList<RssFeedModel>()
                val rssUrl = blogSnap.child("rssUrl").getValue(String::class.java) ?: return@async emptyList<RssFeedModel>()
                val logoUrl = blogSnap.child("logoUrl").getValue(String::class.java) ?: ""

                val items = getRssItems(rssUrl)
                items.transform(blogName, logoUrl)
            }
        }

        val results = deferredList.awaitAll()
        results.forEach { feeds.addAll(it) }

        val sortedFeeds = feeds.sortedByDescending { parseRssDate(it.pubDate) }

        return@withContext sortedFeeds.mapIndexed { index, feedModel ->
            feedModel.copy(id = index)
        }
    }

    private suspend fun getRssItems(rssUrl: String): List<RssItem> = withContext(Dispatchers.IO) {
        val rssFeed = rssApiService.getRssFeed(rssUrl)
        rssFeed.channel.items ?: emptyList()
    }

    private fun parseRssDate(dateStr: String?): Date? {
        val dateFormats = listOf(
            SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH),
            SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH)
        )
        for (format in dateFormats) {
            return dateStr?.let { format.parse(it) }
        }
        return null
    }
}
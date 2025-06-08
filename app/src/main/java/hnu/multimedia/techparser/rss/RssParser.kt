package hnu.multimedia.techparser.rss

import android.util.Log
import hnu.multimedia.techparser.rss.model.RssFeedModel
import hnu.multimedia.techparser.rss.model.transform
import hnu.multimedia.techparser.rss.network.RssRetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object RssParser {

    private val rssApiService = RssRetrofitClient.createRssService()

    suspend fun fetchRssFeed(rssUrl: String): List<RssFeedModel> = withContext(Dispatchers.IO) {
        try {
            Log.d("RssParser", "토스 RSS 피드 요청 시작")
            val rssFeed = rssApiService.getRssFeed(rssUrl)

            val feedModels = rssFeed.channel.items?.transform() ?: emptyList()

            feedModels.forEachIndexed { index, feed ->
                Log.d("RssParser", "=== 아이템 ${index + 1} ===")
                Log.d("RssParser", "Title: ${feed.title}")
                Log.d("RssParser", "Link: ${feed.link}")
                Log.d("RssParser", "Publish Date: ${feed.pubDate}")
            }

            feedModels

        } catch (e: Exception) {
            Log.e("RssParser", "RSS 피드 파싱 실패", e)
            emptyList()
        }
    }
}

package hnu.multimedia.techparser

import android.app.Application
import hnu.multimedia.techparser.rss.RssParser
import hnu.multimedia.techparser.rss.RssRepository
import hnu.multimedia.techparser.util.FirebaseRef
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class TechParserApp : Application() {

    override fun onCreate() {
        super.onCreate()
        CoroutineScope(SupervisorJob() + Dispatchers.Main).launch {
            RssRepository.fetchBlogs()
            val rssFeeds = RssParser.parseRssFeeds()
            FirebaseRef.feeds.setValue(true)
            for (rssFeed in rssFeeds) {
                val key = rssFeed.id.toString()
                FirebaseRef.feeds.child(key).setValue(rssFeed)
            }
            RssRepository.fetchFeeds()
        }
    }
}
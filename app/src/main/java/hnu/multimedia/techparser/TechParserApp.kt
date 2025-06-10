package hnu.multimedia.techparser

import android.app.Application
import hnu.multimedia.techparser.rss.RssParser
import hnu.multimedia.techparser.rss.model.RssFeedModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class TechParserApp : Application() {

    companion object {
        lateinit var feeds: List<RssFeedModel>
        val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    }

    override fun onCreate() {
        super.onCreate()
        applicationScope.launch {
            feeds = RssParser.fetchRssFeeds()
        }
    }
}
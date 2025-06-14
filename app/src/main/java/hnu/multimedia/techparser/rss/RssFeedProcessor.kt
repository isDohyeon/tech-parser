package hnu.multimedia.techparser.rss

import android.app.NotificationManager
import android.content.Context
import hnu.multimedia.techparser.rss.model.RssFeedModel
import hnu.multimedia.techparser.util.FirebaseRef
import hnu.multimedia.techparser.util.NotificationUtil
import hnu.multimedia.techparser.util.Utils
import kotlinx.coroutines.tasks.await

object RssFeedProcessor {

    suspend fun process(context: Context) {
        val parsed = RssParser.parseRssFeeds()
        val existing = getExistingFeeds()
        val newFeeds = findNewFeeds(parsed, existing)

        if (newFeeds.isNotEmpty()) {
            saveNewFeeds(newFeeds)
            notifyNewFeeds(context, newFeeds)
        }
    }

    private suspend fun getExistingFeeds(): List<RssFeedModel> {
        val snapshot = FirebaseRef.feeds.get().await()
        return snapshot.children.mapNotNull {
            it.getValue(RssFeedModel::class.java)
        }
    }

    private fun findNewFeeds(
        parsedFeeds: List<RssFeedModel>,
        existingFeeds: List<RssFeedModel>
    ): List<RssFeedModel> {
        val existingTitles = existingFeeds.map { it.title }.toSet()
        return parsedFeeds.filter { it.title !in existingTitles }
    }

    private suspend fun saveNewFeeds(newFeeds: List<RssFeedModel>) {
        val ref = FirebaseRef.feeds
        newFeeds.forEachIndexed { index, feed ->
            ref.child(index.toString()).setValue(feed).await()
        }
    }

    suspend fun notifyNewFeeds(context: Context, newFeeds: List<RssFeedModel>) {
        val subscribedSnapshot = FirebaseRef.subscribeRef().get().await()
        val notificationSnapshot = FirebaseRef.notificationRef().get().await()

        val isNotificationOn = notificationSnapshot.getValue(Boolean::class.java) ?: true
        if (!isNotificationOn) return

        val subscribedBlogs = subscribedSnapshot.children.map { it.key.toString() }.toSet()

        newFeeds.filter { it.blogName in subscribedBlogs }.forEach { feed ->
            NotificationUtil.createNotification(
                context = context,
                title = feed.blogName,
                message = feed.title,
                url = feed.link,
                importance = Utils.calculateImportance(feed.blogName, feed.title)
            )
        }
    }
}

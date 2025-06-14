package hnu.multimedia.techparser.util

import com.google.firebase.database.DataSnapshot
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Locale

object Utils {

    fun removeTimeStamp(savedFolderName: String): String {
        return savedFolderName.substringAfter("_")
    }

    suspend fun findSavedFolder(folderName: String): String {
        val snapshot = FirebaseRef.bookmarksRef().get().await()
        for (child in snapshot.children) {
            val savedFolderName = child.key.toString()
            val removedTimeStamp = removeTimeStamp(savedFolderName)
            if (removedTimeStamp == folderName) {
                return savedFolderName
            }
        }
        return ""
    }

    fun formatPubDate(rawDate: String?): String {
        if (rawDate == null) return ""

        val inputFormats = listOf(
            SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH),
            SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH)
        )
        val outputFormat = SimpleDateFormat("yyyy년 M월 d일", Locale.KOREAN)

        for (format in inputFormats) {
            val date = format.parse(rawDate)
            if (date != null) {
                return outputFormat.format(date)
            }
        }

        return rawDate
    }

    suspend fun calculateImportance(blogName: String, feedTitle: String): Int {
        var totalImportance = 0
        val blogNameSnap = FirebaseRef.notificationBlogRef().get().await()
        val keyWordSnap = FirebaseRef.keywordRef().get().await()

        totalImportance = addBlogImportance(blogNameSnap, blogName, totalImportance)

        totalImportance = addKeywordImportance(keyWordSnap, feedTitle, totalImportance)

        val finalImportance = if (totalImportance > 4) 4 else totalImportance

        return finalImportance
    }

    private fun addKeywordImportance(
        keyWordSnap: DataSnapshot,
        feedTitle: String,
        totalImportance: Int
    ): Int {
        var totalImportance1 = totalImportance
        val keywordList = keyWordSnap.children.mapNotNull { it.getValue(String::class.java) }
        val hasKeyword = keywordList.any { keyword ->
            feedTitle.contains(keyword, ignoreCase = true)
        }
        if (hasKeyword) {
            totalImportance1 += 2
        }
        return totalImportance1
    }

    private fun addBlogImportance(
        blogNameSnap: DataSnapshot,
        blogName: String,
        totalImportance: Int
    ): Int {
        var totalImportance1 = totalImportance
        val isEnabled = blogNameSnap.child(blogName).getValue(Boolean::class.java) ?: true
        if (isEnabled) {
            totalImportance1 += 2
        }
        return totalImportance1
    }
}
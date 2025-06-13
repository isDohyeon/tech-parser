package hnu.multimedia.techparser.util

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
            try {
                val date = format.parse(rawDate)
                if (date != null) return outputFormat.format(date)
            } catch (_: Exception) {}
        }

        return rawDate
    }
}
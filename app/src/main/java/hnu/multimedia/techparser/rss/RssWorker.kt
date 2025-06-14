package hnu.multimedia.techparser.rss

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class RssWorker(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        RssFeedProcessor.process(applicationContext)
        return Result.success()
    }
}
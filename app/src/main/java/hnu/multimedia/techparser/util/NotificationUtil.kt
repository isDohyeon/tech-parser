package hnu.multimedia.techparser.util

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import hnu.multimedia.techparser.R

class NotificationUtil {

    companion object {
        private const val CHANNEL_ID = "rss_update_channel"
        private const val CHANNEL_NAME = "새로운 피드 알림"
        private const val CHANNEL_DESC = "새로운 피드가 갱신되면 알림을 보내줍니다."

        fun createNotification(
            context: Context,
            title: String,
            message: String,
            url: String,
            importance: Int
        ) {
            val notificationManager =
                context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val builder: NotificationCompat.Builder

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val mChannel = NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    importance
                )
                mChannel.description = CHANNEL_DESC
                notificationManager.createNotificationChannel(mChannel)
                builder = NotificationCompat.Builder(context, CHANNEL_ID)
            } else {
                builder = NotificationCompat.Builder(context, CHANNEL_ID)
            }

            val fixedUrl = if (!url.startsWith("http://") && !url.startsWith("https://")) {
                "https://$url"
            } else {
                url
            }

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(fixedUrl))

            val pendingIntent = PendingIntent.getActivity(
                context,
                System.currentTimeMillis().toInt(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            builder.setSmallIcon(R.drawable.techparser_logo)
            builder.setPriority(importance - 2)
            builder.setDefaults(NotificationCompat.DEFAULT_ALL)
            builder.setContentTitle("중요도 ${importance}의 새로운 피드")
            builder.setContentText("$title - $message")
            builder.setContentIntent(pendingIntent)
            builder.setAutoCancel(true)

            notificationManager.notify(System.currentTimeMillis().toInt(), builder.build())
            saveNotificationLog(context, title, message, url)
        }

        private fun saveNotificationLog(
            context: Context,
            title: String,
            message: String,
            url: String
        ) {
            val prefs = context.getSharedPreferences("notification_logs", Context.MODE_PRIVATE)
            val logs = prefs.getStringSet("logs", mutableSetOf())?.toMutableSet() ?: mutableSetOf()
            logs.add("$title#$message#$url")
            prefs.edit().putStringSet("logs", logs).apply()
        }

        fun requestPermissions(activity: AppCompatActivity) {
            val isTiramisuOrHigher = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
            val permission = Manifest.permission.POST_NOTIFICATIONS

            var hasPermission =
                if (isTiramisuOrHigher) {
                    ContextCompat.checkSelfPermission(
                        activity,
                        permission
                    ) == PackageManager.PERMISSION_GRANTED
                } else {
                    true
                }
            val launcher =
                activity.registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                    hasPermission = it
                }

            if (!hasPermission) {
                launcher.launch(permission)
            }
        }
    }
}
package hnu.multimedia.techparser.ui.feed.notification

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import hnu.multimedia.techparser.databinding.ActivityNotificationLogBinding

class NotificationLogActivity : AppCompatActivity() {

    private val binding by lazy { ActivityNotificationLogBinding.inflate(layoutInflater) }
    private val logs = mutableListOf<Triple<String, String, String>>() // title, message, url

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadLogs()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = NotificationLogAdapter(logs) { url ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

    private fun loadLogs() {
        val prefs = getSharedPreferences("notification_logs", MODE_PRIVATE)
        val savedLogs = prefs.getStringSet("logs", emptySet()) ?: return
        logs.clear()
        logs.addAll(savedLogs.map {
            val parts = it.split("#")
            Triple(
                parts.getOrNull(0) ?: "제목 없음",
                parts.getOrNull(1) ?: "내용 없음",
                parts.getOrNull(2) ?: ""
            )
        })
    }
}
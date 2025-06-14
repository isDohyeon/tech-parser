package hnu.multimedia.techparser.ui.feed.notification

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import hnu.multimedia.techparser.databinding.ActivityNotificationLogBinding

class NotificationLogActivity : AppCompatActivity() {

    private val binding by lazy { ActivityNotificationLogBinding.inflate(layoutInflater) }
    private val logs = mutableListOf<Triple<String, String, String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadLogs()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = NotificationLogAdapter(logs)

        binding.imageViewRemoveLog.setOnClickListener {
            clearNotificationLogs()
        }
    }

    private fun loadLogs() {
        val prefs = getSharedPreferences("notification_logs", MODE_PRIVATE)
        val savedLogs = prefs.getStringSet("logs", emptySet()) ?: return
        logs.clear()
        logs.addAll(savedLogs.map {
            val parts = it.split("#")
            Triple(
                parts.getOrNull(0) ?: "",
                parts.getOrNull(1) ?: "",
                parts.getOrNull(2) ?: ""
            )
        })
    }

    private fun clearNotificationLogs() {
        val prefs = getSharedPreferences("notification_logs", Context.MODE_PRIVATE)
        prefs.edit().remove("logs").apply()
        logs.clear()
        binding.recyclerView.adapter = NotificationLogAdapter(logs)
    }
}
package hnu.multimedia.techparser.ui.setting.blog

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import hnu.multimedia.techparser.databinding.ActivityBlogSettingBinding
import hnu.multimedia.techparser.util.FirebaseRef
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class BlogSettingActivity : AppCompatActivity() {

    private val binding by lazy { ActivityBlogSettingBinding.inflate(layoutInflater) }
    private val blogs = mutableListOf<String>()
    private val blogSettings = mutableMapOf<String, Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = BlogSettingAdapter(blogs, blogSettings)

        fetchBlogSettings()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun fetchBlogSettings() {
        CoroutineScope(Dispatchers.Main).launch {
            val subscribeSnap = FirebaseRef.subscribeRef().get().await()
            val notifySnap = FirebaseRef.notificationBlogRef().get().await()

            blogs.clear()
            blogSettings.clear()

            val notifyMap = notifySnap.children.associate {
                it.key.toString() to (it.getValue(Boolean::class.java) ?: true)
            }

            for (child in subscribeSnap.children) {
                val blogName = child.key.toString()
                blogs.add(blogName)
                blogSettings[blogName] = notifyMap[blogName] ?: true
            }

            binding.recyclerView.adapter?.notifyDataSetChanged()
        }
    }
}
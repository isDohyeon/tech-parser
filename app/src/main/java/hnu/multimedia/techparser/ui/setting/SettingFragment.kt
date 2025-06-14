package hnu.multimedia.techparser.ui.setting

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hnu.multimedia.techparser.databinding.FragmentSettingBinding
import hnu.multimedia.techparser.rss.RssFeedProcessor
import hnu.multimedia.techparser.rss.model.RssFeedModel
import hnu.multimedia.techparser.ui.setting.blog.BlogSettingActivity
import hnu.multimedia.techparser.ui.setting.keyword.KeywordSettingActivity
import hnu.multimedia.techparser.util.FirebaseRef
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingFragment : Fragment() {

    private val binding by lazy { FragmentSettingBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fetchNotificationSetting()
        binding.buttonNotificationTest.setOnClickListener {
            testNotification()
        }

        binding.switchNotification.setOnCheckedChangeListener { _, isChecked ->
            updateNotificationSetting(isChecked)
        }

        binding.imageViewBlogSetting.setOnClickListener {
            val intent = Intent(binding.root.context, BlogSettingActivity::class.java)
            startActivity(intent)
        }

        binding.imageViewKeywordSetting.setOnClickListener {
            val intent = Intent(binding.root.context, KeywordSettingActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    private fun fetchNotificationSetting() {
        val ref = FirebaseRef.notificationRef()
        ref.get().addOnSuccessListener { snapshot ->
            val isNotificationOn = snapshot.getValue(Boolean::class.java) ?: true
            binding.switchNotification.isChecked = isNotificationOn
        }
    }

    private fun updateNotificationSetting(isChecked: Boolean) {
        FirebaseRef.notificationRef().setValue(isChecked)
    }

    private fun testNotification() {
        CoroutineScope(Dispatchers.Main).launch {
            val testFeed = RssFeedModel(
                id = System.currentTimeMillis().toInt(),
                blogName = "Toss Tech",
                title = "테스트 피드 제목 : ${System.currentTimeMillis()} Java",
                link = "https://www.hannam.ac.kr/kor/main/",
                logoUrl = "",
                pubDate = "Sat, 14 Jun 2025 18:00:00 GMT"
            )

            RssFeedProcessor.notifyNewFeeds(requireContext(), listOf(testFeed))
        }
    }
}
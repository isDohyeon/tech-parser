package hnu.multimedia.techparser.ui.setting

import android.app.NotificationManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import hnu.multimedia.techparser.databinding.FragmentSettingBinding
import hnu.multimedia.techparser.rss.RssFeedProcessor
import hnu.multimedia.techparser.rss.RssParser
import hnu.multimedia.techparser.rss.RssWorker
import hnu.multimedia.techparser.rss.model.RssFeedModel
import hnu.multimedia.techparser.util.FirebaseRef
import hnu.multimedia.techparser.util.NotificationUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SettingFragment : Fragment() {

    private val binding by lazy { FragmentSettingBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.buttonNotificationTest.setOnClickListener {
            testNotification()
        }
        return binding.root
    }

    private fun testNotification() {
        CoroutineScope(Dispatchers.Main).launch {
            val testFeed = RssFeedModel(
                id = System.currentTimeMillis().toInt(),
                blogName = "Toss Tech",
                title = "테스트 피드의 제목 : ${System.currentTimeMillis()}",
                link = "https://www.hannam.ac.kr/kor/main/",
                logoUrl = "",
                pubDate = "Sat, 14 Jun 2025 18:00:00 GMT"
            )

            RssFeedProcessor.notifyNewFeeds(requireContext(), listOf(testFeed))
        }
    }
}
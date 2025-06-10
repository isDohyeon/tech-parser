package hnu.multimedia.techparser.auth

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import hnu.multimedia.techparser.databinding.ActivitySplashBinding
import hnu.multimedia.techparser.rss.RssParser
import hnu.multimedia.techparser.util.FirebaseRef
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySplashBinding.inflate(layoutInflater) }
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth = Firebase.auth
        fetchRssFeeds()
    }

    private fun fetchRssFeeds() {
        lifecycleScope.launch {
            val rssFeeds = RssParser.fetchRssFeeds()
            for (rssFeed in rssFeeds) {
                val key = rssFeed.id.toString()
                FirebaseRef.feeds.child(key).setValue(rssFeed)
            }
            startLoginActivity()
        }
    }

    private fun startLoginActivity() {
        val intent = Intent(this@SplashActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
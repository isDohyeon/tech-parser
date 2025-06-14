package hnu.multimedia.techparser

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import hnu.multimedia.techparser.databinding.ActivityMainBinding
import hnu.multimedia.techparser.rss.RssWorker
import hnu.multimedia.techparser.util.FirebaseRef
import hnu.multimedia.techparser.util.NotificationUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        FirebaseRef.initCUid()
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)
        NotificationUtil.requestPermissions(this)

        registerRssWorker()
    }

    private fun registerRssWorker() {
        CoroutineScope(SupervisorJob() + Dispatchers.Main).launch {
            val request = PeriodicWorkRequestBuilder<RssWorker>(1, TimeUnit.HOURS).build()
            WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
                "RssCheckWorker",
                ExistingPeriodicWorkPolicy.KEEP,
                request
            )
        }
    }
}
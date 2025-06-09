package hnu.multimedia.techparser

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import hnu.multimedia.techparser.databinding.ActivityMainBinding
import hnu.multimedia.techparser.util.FirebaseRef

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        FirebaseRef.initCUid()
        Toast.makeText(binding.root.context, "RSS 피드를 불러오는 중입니다..", Toast.LENGTH_LONG).show()
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)
    }
}
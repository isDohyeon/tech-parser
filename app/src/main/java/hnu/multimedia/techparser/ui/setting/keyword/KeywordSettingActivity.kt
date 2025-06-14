package hnu.multimedia.techparser.ui.setting.keyword

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import hnu.multimedia.techparser.R
import hnu.multimedia.techparser.databinding.ActivityKeywordSettingBinding

class KeywordSettingActivity : AppCompatActivity() {

    private val binding by lazy {ActivityKeywordSettingBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
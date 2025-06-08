package hnu.multimedia.techparser.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hnu.multimedia.techparser.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private val binding by lazy { ActivityWebViewBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val url = intent.getStringExtra("originalURL")
        binding.webView.loadUrl(url.toString())
    }
}
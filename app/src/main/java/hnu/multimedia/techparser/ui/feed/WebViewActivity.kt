package hnu.multimedia.techparser.ui.feed

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import hnu.multimedia.techparser.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private val binding by lazy { ActivityWebViewBinding.inflate(layoutInflater) }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val url = intent.getStringExtra("originalURL") ?: return
        val replaceFirst = url.replaceFirst("https://", "http://")

        with(binding.webView) {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true

            webViewClient = object : WebViewClient() {
                @SuppressLint("WebViewClientOnReceivedSslError")
                override fun onReceivedSslError(
                    view: WebView?,
                    handler: android.webkit.SslErrorHandler?,
                    error: android.net.http.SslError?
                ) {
                    handler?.proceed()
                }
            }
            loadUrl(replaceFirst)
        }
    }
}
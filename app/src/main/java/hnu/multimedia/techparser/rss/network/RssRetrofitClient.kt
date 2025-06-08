package hnu.multimedia.techparser.rss.network

import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object RssRetrofitClient {

    private val tikXml = TikXml.Builder()
        .exceptionOnUnreadXml(false)
        .build()

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private fun createRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(
                TikXmlConverterFactory.create(tikXml)
            )
            .build()
    }

    fun createRssService(baseUrl: String = "https://toss.tech/"): RssApiService {
        return createRetrofit(baseUrl).create(RssApiService::class.java)
    }
}
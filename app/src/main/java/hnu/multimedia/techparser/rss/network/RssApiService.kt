package hnu.multimedia.techparser.rss.network

import hnu.multimedia.techparser.rss.model.RssFeed
import retrofit2.http.GET
import retrofit2.http.Url

interface RssApiService {

    @GET
    suspend fun getRssFeed(@Url url: String): RssFeed
}
package hnu.multimedia.techparser.rss.model

data class RssFeedModel(
    val title: String = "",
    val link: String = "",
    val pubDate: String = "",
    var logoUrl: String = "",
    var blogName: String = ""
)

fun List<RssItem>.transform(blogName: String, logoUrl: String): List<RssFeedModel> {
    return this.map {
        RssFeedModel(
            title = it.title ?: "",
            link = it.link ?: "",
            pubDate = it.pubDate ?: "",
            logoUrl = logoUrl,
            blogName = blogName
        )
    }
}

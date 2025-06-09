package hnu.multimedia.techparser.rss.model

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "item")
data class RssItem(
    @PropertyElement(name = "title")
    val title: String? = null,
    @PropertyElement(name = "link")
    val link: String? = null,
    @PropertyElement(name = "pubDate")
    val pubDate: String? = null
)

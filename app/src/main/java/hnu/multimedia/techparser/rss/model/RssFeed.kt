package hnu.multimedia.techparser.rss.model

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "rss")
data class RssFeed(
    @Element(name = "channel")
    val channel: RssChannel
)

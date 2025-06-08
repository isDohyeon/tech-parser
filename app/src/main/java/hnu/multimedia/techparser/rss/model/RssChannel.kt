package hnu.multimedia.techparser.rss.model

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "channel")
data class RssChannel(
    @Element(name = "item")
    val items: List<RssItem>? = null
)

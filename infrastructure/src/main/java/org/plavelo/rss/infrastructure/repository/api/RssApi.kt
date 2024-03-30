package org.plavelo.rss.infrastructure.repository.api

import android.content.Context
import com.prof.rssparser.Parser
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale
import org.plavelo.rss.domain.Article
import org.plavelo.rss.domain.ArticleId
import org.plavelo.rss.domain.Channel
import org.plavelo.rss.domain.ChannelId
import org.plavelo.rss.domain.Feed

class RssApi(context: Context) {
    private val parser = Parser.Builder()
        .context(context)
        .build()

    suspend fun fetch(feed: Feed): Pair<Channel, List<Article>> {
        val c = parser.getChannel(feed.url)
        val dateFormatter = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US)
        val channel = Channel(
            ChannelId(0),
            c.title,
            c.link,
            c.description,
        )
        val articles = c.articles.map { article ->
            val dateString = article.pubDate
            val date = if (dateString != null) {
                try {
                    dateFormatter.parse(dateString)
                } catch (e :ParseException) {
                    null
                }
            } else {
                null
            }
            Article(
                ArticleId(0, ChannelId(0)),
                article.title,
                article.link,
                article.description,
                article.image,
                article.content,
                date,
            )
        }
        return Pair(channel, articles)
    }
}

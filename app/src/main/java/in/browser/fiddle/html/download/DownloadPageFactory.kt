package `in`.browser.fiddle.html.download

import `in`.browser.fiddle.R
import `in`.browser.fiddle.constant.FILE
import `in`.browser.fiddle.database.downloads.DownloadEntry
import `in`.browser.fiddle.database.downloads.DownloadsRepository
import `in`.browser.fiddle.html.HtmlPageFactory
import `in`.browser.fiddle.html.ListPageReader
import `in`.browser.fiddle.preference.UserPreferences
import android.app.Application
import io.reactivex.Single
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.io.File
import java.io.FileWriter
import javax.inject.Inject

/**
 * The factory for the downloads page.
 */
class DownloadPageFactory @Inject constructor(
    private val application: Application,
    private val userPreferences: UserPreferences,
    private val manager: DownloadsRepository,
    private val listPageReader: ListPageReader
) : HtmlPageFactory {

    override fun buildPage(): Single<String> = manager
        .getAllDownloads()
        .map { list ->
            Jsoup.parse(listPageReader.provideHtml()).apply {
                title(application.getString(R.string.action_downloads))
                body().also { body ->
                    val repeatableElement = body.getElementById("repeated").also(Element::remove)

                    body.getElementById("content").also { content ->
                        list.forEach {
                            content.appendChild(repeatableElement.clone().apply {
                                getElementsByTag("a").first().attr("href", createFileUrl(it.title))
                                getElementById("title").text(createFileTitle(it))
                                getElementById("url").text(it.url)
                            })
                        }
                    }
                }
            }.outerHtml()
        }
        .map { content -> Pair(createDownloadsPageFile(), content) }
        .doOnSuccess { (page, content) ->
            FileWriter(page, false).use { it.write(content) }
        }
        .map { (page, _) -> "$FILE$page" }


    private fun createDownloadsPageFile(): File = File(application.filesDir, FILENAME)

    private fun createFileUrl(fileName: String): String = "$FILE${userPreferences.downloadDirectory}/$fileName"

    private fun createFileTitle(downloadItem: DownloadEntry): String {
        val contentSize = if (downloadItem.contentSize.isNotBlank()) {
            "[${downloadItem.contentSize}]"
        } else {
            ""
        }

        return "${downloadItem.title} $contentSize"
    }

    companion object {

        const val FILENAME = "downloads.html"

    }

}

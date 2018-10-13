package `in`.browser.fiddle.html.homepage

import `in`.browser.fiddle.R
import `in`.browser.fiddle.constant.FILE
import `in`.browser.fiddle.constant.UTF8
import `in`.browser.fiddle.html.HtmlPageFactory
import `in`.browser.fiddle.search.SearchEngineProvider
import android.app.Application
import io.reactivex.Single
import org.jsoup.Jsoup
import java.io.File
import java.io.FileWriter
import javax.inject.Inject

/**
 * A factory for the home page.
 */
class HomePageFactory @Inject constructor(
    private val application: Application,
    private val searchEngineProvider: SearchEngineProvider,
    private val homePageReader: HomePageReader
) : HtmlPageFactory {

    private val title = application.getString(R.string.home)

    override fun buildPage(): Single<String> = Single
        .just(searchEngineProvider.provideSearchEngine())
        .map { (iconUrl, queryUrl, _) ->
            Jsoup.parse(homePageReader.provideHtml()).apply {
                title(title)
                outputSettings().charset(UTF8)
                body().getElementById("image_url").attr("src", iconUrl)
                getElementsByTag("script").first()?.let {
                    val newJavaScript = it.html()
                        .replace("\${BASE_URL}", queryUrl)
                        .replace("&", "\\u0026")
                    it.html(newJavaScript)
                }
            }.outerHtml()
        }
        .map { content -> Pair(createHomePage(), content) }
        .doOnSuccess { (page, content) ->
            FileWriter(page, false).use {
                it.write(content)
            }
        }
        .map { (page, _) -> "$FILE$page" }

    /**
     * Create the home page file.
     */
    fun createHomePage() = File(application.filesDir, FILENAME)

    companion object {

        const val FILENAME = "homepage.html"

    }

}

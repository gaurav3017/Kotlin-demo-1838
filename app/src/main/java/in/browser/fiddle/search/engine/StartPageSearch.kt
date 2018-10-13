package `in`.browser.fiddle.search.engine

import `in`.browser.fiddle.R

/**
 * The StartPage search engine.
 */
class StartPageSearch : BaseSearchEngine(
        "file:///android_asset/startpage.png",
        "https://startpage.com/do/search?language=english&query=",
        R.string.search_engine_startpage
)

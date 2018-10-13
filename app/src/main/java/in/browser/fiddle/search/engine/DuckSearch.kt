package `in`.browser.fiddle.search.engine

import `in`.browser.fiddle.R

/**
 * The DuckDuckGo search engine.
 *
 * See https://duckduckgo.com/assets/logo_homepage.normal.v101.png for the icon.
 */
class DuckSearch : BaseSearchEngine(
        "file:///android_asset/duckduckgo.png",
        "https://duckduckgo.com/?t=fiddle&q=",
        R.string.search_engine_duckduckgo
)

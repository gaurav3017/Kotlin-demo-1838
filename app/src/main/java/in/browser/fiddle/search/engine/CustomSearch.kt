package `in`.browser.fiddle.search.engine

import `in`.browser.fiddle.R

/**
 * A custom search engine.
 */
class CustomSearch(queryUrl: String) : BaseSearchEngine(
        "file:///android_asset/fiddle.png",
        queryUrl,
        R.string.search_engine_custom
)

package `in`.browser.fiddle.di

import `in`.browser.fiddle.adblock.whitelist.SessionWhitelistModel
import `in`.browser.fiddle.adblock.whitelist.WhitelistModel
import `in`.browser.fiddle.database.bookmark.BookmarkDatabase
import `in`.browser.fiddle.database.bookmark.BookmarkRepository
import `in`.browser.fiddle.database.downloads.DownloadsDatabase
import `in`.browser.fiddle.database.downloads.DownloadsRepository
import `in`.browser.fiddle.database.history.HistoryDatabase
import `in`.browser.fiddle.database.history.HistoryRepository
import `in`.browser.fiddle.database.whitelist.AdBlockWhitelistDatabase
import `in`.browser.fiddle.database.whitelist.AdBlockWhitelistRepository
import `in`.browser.fiddle.ssl.SessionSslWarningPreferences
import `in`.browser.fiddle.ssl.SslWarningPreferences
import dagger.Binds
import dagger.Module

/**
 * Dependency injection module used to bind implementations to interfaces.
 */
@Module
abstract class AppBindsModule {

    @Binds
    abstract fun provideBookmarkModel(bookmarkDatabase: BookmarkDatabase): BookmarkRepository

    @Binds
    abstract fun provideDownloadsModel(downloadsDatabase: DownloadsDatabase): DownloadsRepository

    @Binds
    abstract fun providesHistoryModel(historyDatabase: HistoryDatabase): HistoryRepository

    @Binds
    abstract fun providesAdBlockWhitelistModel(adBlockWhitelistDatabase: AdBlockWhitelistDatabase): AdBlockWhitelistRepository

    @Binds
    abstract fun providesWhitelistModel(sessionWhitelistModel: SessionWhitelistModel): WhitelistModel

    @Binds
    abstract fun providesSslWarningPreferences(sessionSslWarningPreferences: SessionSslWarningPreferences): SslWarningPreferences

}

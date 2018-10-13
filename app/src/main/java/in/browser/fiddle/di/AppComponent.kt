package `in`.browser.fiddle.di

import `in`.browser.fiddle.BrowserApp
import `in`.browser.fiddle.adblock.AssetsAdBlocker
import `in`.browser.fiddle.adblock.NoOpAdBlocker
import `in`.browser.fiddle.browser.SearchBoxModel
import `in`.browser.fiddle.browser.activity.BrowserActivity
import `in`.browser.fiddle.browser.activity.ThemableBrowserActivity
import `in`.browser.fiddle.browser.fragment.BookmarksFragment
import `in`.browser.fiddle.browser.fragment.TabsFragment
import `in`.browser.fiddle.dialog.LightningDialogBuilder
import `in`.browser.fiddle.download.DownloadHandler
import `in`.browser.fiddle.download.LightningDownloadListener
import `in`.browser.fiddle.reading.activity.ReadingActivity
import `in`.browser.fiddle.search.SuggestionsAdapter
import `in`.browser.fiddle.settings.activity.ThemableSettingsActivity
import `in`.browser.fiddle.settings.fragment.*
import `in`.browser.fiddle.utils.ProxyUtils
import `in`.browser.fiddle.view.LightningChromeClient
import `in`.browser.fiddle.view.LightningView
import `in`.browser.fiddle.view.LightningWebClient
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (AppBindsModule::class)])
interface AppComponent {

    fun inject(activity: BrowserActivity)

    fun inject(fragment: BookmarksFragment)

    fun inject(fragment: BookmarkSettingsFragment)

    fun inject(builder: LightningDialogBuilder)

    fun inject(fragment: TabsFragment)

    fun inject(lightningView: LightningView)

    fun inject(activity: ThemableBrowserActivity)

    fun inject(advancedSettingsFragment: AdvancedSettingsFragment)

    fun inject(app: BrowserApp)

    fun inject(proxyUtils: ProxyUtils)

    fun inject(activity: ReadingActivity)

    fun inject(webClient: LightningWebClient)

    fun inject(activity: ThemableSettingsActivity)

    fun inject(listener: LightningDownloadListener)

    fun inject(fragment: PrivacySettingsFragment)

    fun inject(fragment: DebugSettingsFragment)

    fun inject(suggestionsAdapter: SuggestionsAdapter)

    fun inject(chromeClient: LightningChromeClient)

    fun inject(downloadHandler: DownloadHandler)

    fun inject(searchBoxModel: SearchBoxModel)

    fun inject(generalSettingsFragment: GeneralSettingsFragment)

    fun inject(displaySettingsFragment: DisplaySettingsFragment)

    fun provideAssetsAdBlocker(): AssetsAdBlocker

    fun provideNoOpAdBlocker(): NoOpAdBlocker

}

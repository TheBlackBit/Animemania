package com.theblackbit.animemania.android.home

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.theblackbit.animemania.android.core.testing.KoinTestRule
import com.theblackbit.animemania.android.core.testing.customviewaction.WaitFor
import com.theblackbit.animemania.android.core.testing.customviewmatcher.WithTextAtPositionOnChildRecyclerView
import com.theblackbit.animemania.android.data.di.internal.collectionDaoModule
import com.theblackbit.animemania.android.data.di.internal.collectionRoomRepositoryModule
import com.theblackbit.animemania.android.data.di.internal.roomDbModule
import com.theblackbit.animemania.android.data.di.pagingsource.animePagingSourceFactoryModule
import com.theblackbit.animemania.android.data.di.pagingsource.mangaPagingSourceFactoryModule
import com.theblackbit.animemania.android.domain.di.collectAnimeUseCaseModule
import com.theblackbit.animemania.android.domain.di.collectMangaUseCaseModule
import com.theblackbit.animemania.android.feature.home.R
import com.theblackbit.animemania.android.home.di.animeRemoteRepositoryMock
import com.theblackbit.animemania.android.home.di.homeViewModelModule
import com.theblackbit.animemania.android.home.di.mangaRemoteRepositoryMock
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import com.theblackbit.animemania.android.core.resources.R as resources

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule(
        modules = listOf(
            roomDbModule,
            collectionDaoModule,
            collectionRoomRepositoryModule,
            animePagingSourceFactoryModule,
            mangaPagingSourceFactoryModule,
            animeRemoteRepositoryMock,
            mangaRemoteRepositoryMock,
            collectAnimeUseCaseModule,
            collectMangaUseCaseModule,
            homeViewModelModule,
        ),
    )

    @Test
    fun headerIsDisplayed() {
        launchFragmentInContainer<HomeFragment>(
            themeResId = resources.style.Theme_Animemania,
        )
        onView(withText(resources.string.icon))
            .check(matches(isDisplayed()))
        onView(withText(resources.string.app_name))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testAnimeTabInHomeFragment() {
        launchFragmentInContainer<HomeFragment>(
            themeResId = resources.style.Theme_Animemania,
        )
        testTabWithViewPager("Anime", "One Piece")
    }

    @Test
    fun testMangaTabInHomeFragment() {
        launchFragmentInContainer<HomeFragment>(
            themeResId = resources.style.Theme_Animemania,
        )
        testTabWithViewPager("Manga", "Martial Peak")
    }

    private fun testTabWithViewPager(tabText: String, contentText: String) {
        clickToTab(tabText)
        waitUntilDataFinishedLoad()
        testFirstElement(contentText)
    }

    private fun clickToTab(tabText: String) {
        onView(withText(tabText))
            .perform(click())
    }

    private fun waitUntilDataFinishedLoad() {
        onView(ViewMatchers.isRoot()).perform(WaitFor(4000L))
    }

    private fun testFirstElement(contentText: String) {
        onView(withId(R.id.rv_data)).check(
            matches(
                WithTextAtPositionOnChildRecyclerView(
                    positionOfChildRecyclerView = 0,
                    childRecyclerViewId = R.id.rv_data_container,
                    positionOfViewInChildRecyclerView = 0,
                    textViewId = R.id.tv_collection_name,
                    text = contentText,
                ),
            ),
        )
    }
}

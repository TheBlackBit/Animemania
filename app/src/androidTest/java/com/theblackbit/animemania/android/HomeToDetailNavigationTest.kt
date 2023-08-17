package com.theblackbit.animemania.android

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.theblackbit.animemania.android.core.testing.KoinTestRule
import com.theblackbit.animemania.android.core.testing.customviewaction.ClickItemOnChildRecyclerViewItem
import com.theblackbit.animemania.android.core.testing.customviewaction.ScrollToPositionChildRecyclerView
import com.theblackbit.animemania.android.core.testing.customviewaction.WaitFor
import com.theblackbit.animemania.android.data.di.internal.collectionDaoModule
import com.theblackbit.animemania.android.data.di.internal.collectionRoomRepositoryModule
import com.theblackbit.animemania.android.data.di.internal.roomDbModule
import com.theblackbit.animemania.android.data.di.pagingsource.animePagingSourceFactoryModule
import com.theblackbit.animemania.android.data.di.pagingsource.mangaPagingSourceFactoryModule
import com.theblackbit.animemania.android.detail.di.chapterTabViewModelModule
import com.theblackbit.animemania.android.detail.di.characterTabViewModelModule
import com.theblackbit.animemania.android.di.animeRemoteRepositoryMock
import com.theblackbit.animemania.android.di.mangaRemoteRepositoryMock
import com.theblackbit.animemania.android.domain.di.collectAnimeUseCaseModule
import com.theblackbit.animemania.android.domain.di.collectMangaUseCaseModule
import com.theblackbit.animemania.android.feature.detail.R
import com.theblackbit.animemania.android.home.adapter.DataViewHolder
import com.theblackbit.animemania.android.home.di.homeViewModelModule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import com.theblackbit.animemania.android.feature.home.R as homeR

@LargeTest
@RunWith(AndroidJUnit4::class)
class HomeToDetailNavigationTest : KoinTest {

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
            chapterTabViewModelModule,
            characterTabViewModelModule,
        ),
    )

    @Test
    fun testNavigationAnimeToDetail() {
        ActivityScenario.launch(MainActivity::class.java)

        testTrendingAnimeDetailNavigation()

        testMostAnticipatedAnimeDetailNavigation()

        testTopRatedAnimeDetailNavigation()

        testPopularAnimeDetailNavigation()
    }

    @Test
    fun testNavigationMangaToDetail() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withText("Manga"))
            .perform(click())

        testTrendingMangaDetailNavigation()

        testMostAnticipatedMangaDetailNavigation()

        testTopRatedMangaDetailNavigation()

        testPopularMangaDetailNavigation()
    }

    @Test
    fun backNavigationFromDetailToHome() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(homeR.id.rv_data))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    0,
                ),
            )
            .perform(
                ScrollToPositionChildRecyclerView(
                    parentRecyclerViewId = homeR.id.rv_data,
                    positionOfChildRecyclerView = 0,
                    positionOfViewInChildRecyclerView = 0,
                    childRecyclerViewId = homeR.id.rv_data_container,
                ),
            )
            .perform(
                ClickItemOnChildRecyclerViewItem<DataViewHolder>(
                    parentRecyclerViewId = homeR.id.rv_data,
                    positionOfChildRecyclerView = 0,
                    positionOfViewInChildRecyclerView = 0,
                    childRecyclerViewId = homeR.id.rv_data_container,
                ),
            )

        onView(ViewMatchers.isRoot()).perform(WaitFor(200L))

        onView(withId(R.id.backIcon))
            .perform(click())

        onView(ViewMatchers.isRoot()).perform(WaitFor(200L))

        onView(withText("Animemania"))
            .check(matches(isDisplayed()))
    }

    private fun testTrendingAnimeDetailNavigation() {
        validateDataPassBetweenFragments(
            parentIndex = 0,
            name = "One Piece",
            status = "current",
            averageRating = "83.69%",
            startDate = "1999-10-20",
            endDate = "",
        )
        pressBack()
        onView(ViewMatchers.isRoot()).perform(WaitFor(500L))
    }

    private fun testMostAnticipatedAnimeDetailNavigation() {
        validateDataPassBetweenFragments(
            parentIndex = 1,
            name = "SPY×FAMILY Season 2",
            status = "upcoming",
            averageRating = "",
            startDate = "2023-10-31",
            endDate = "",
        )
        pressBack()
        onView(ViewMatchers.isRoot()).perform(WaitFor(500L))
    }

    private fun testTopRatedAnimeDetailNavigation() {
        validateDataPassBetweenFragments(
            2,
            name = "Kimetsu no Yaiba: Yuukaku-hen",
            status = "finished",
            averageRating = "87.43%",
            startDate = "2021-12-05",
            endDate = "2022-02-13",
        )
        pressBack()
        onView(ViewMatchers.isRoot()).perform(WaitFor(500L))
    }

    private fun testPopularAnimeDetailNavigation() {
        validateDataPassBetweenFragments(
            3,
            name = "Attack on Titan",
            status = "finished",
            averageRating = "84.92%",
            startDate = "2013-04-07",
            endDate = "2013-09-29",
        )
        pressBack()
        onView(ViewMatchers.isRoot()).perform(WaitFor(500L))
    }

    private fun testTrendingMangaDetailNavigation() {
        validateDataPassBetweenFragments(
            0,
            name = "Martial Peak",
            status = "current",
            averageRating = "80.04%",
            startDate = "2017-06-01",
            endDate = "",
        )
        pressBack()
        onView(ViewMatchers.isRoot()).perform(WaitFor(500L))
    }

    private fun testMostAnticipatedMangaDetailNavigation() {
        validateDataPassBetweenFragments(
            1,
            name = "Mushoku Tensei: Dasoku-hen",
            status = "current",
            averageRating = "",
            startDate = "2023-06-23",
            endDate = "",
        )
        pressBack()
        onView(ViewMatchers.isRoot()).perform(WaitFor(500L))
    }

    private fun testTopRatedMangaDetailNavigation() {
        validateDataPassBetweenFragments(
            2,
            name = "Boku no Hero Academia",
            status = "current",
            averageRating = "85.01%",
            startDate = "2014-07-07",
            endDate = "",
        )
        pressBack()
        onView(ViewMatchers.isRoot()).perform(WaitFor(500L))
    }

    private fun testPopularMangaDetailNavigation() {
        validateDataPassBetweenFragments(
            3,
            name = "Boku no Hero Academia",
            status = "current",
            averageRating = "85.01%",
            startDate = "2014-07-07",
            endDate = "",
        )
        pressBack()
        onView(ViewMatchers.isRoot()).perform(WaitFor(500L))
    }

    private fun validateDataPassBetweenFragments(
        parentIndex: Int,
        name: String,
        status: String,
        averageRating: String,
        startDate: String,
        endDate: String,
    ) {
        onView(withId(homeR.id.rv_data))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    parentIndex,
                ),
            )
            .perform(
                ScrollToPositionChildRecyclerView(
                    parentRecyclerViewId = homeR.id.rv_data,
                    positionOfChildRecyclerView = parentIndex,
                    positionOfViewInChildRecyclerView = 0,
                    childRecyclerViewId = homeR.id.rv_data_container,
                ),
            )
            .perform(
                ClickItemOnChildRecyclerViewItem<DataViewHolder>(
                    parentRecyclerViewId = homeR.id.rv_data,
                    positionOfChildRecyclerView = parentIndex,
                    positionOfViewInChildRecyclerView = 0,
                    childRecyclerViewId = homeR.id.rv_data_container,
                ),
            )

        onView(ViewMatchers.isRoot()).perform(WaitFor(1000L))

        onView(withId(R.id.tv_broadcast_state))
            .check(matches(withText(status)))

        onView(withId(R.id.tv_title_name))
            .check(matches(withText(name)))

        onView(withId(R.id.tv_rating))
            .check(matches(withText(averageRating)))

        onView(withId(R.id.tv_emit))
            .check(matches(withText(startDate)))

        onView(withId(R.id.tv_end_date))
            .check(matches(withText(endDate)))
    }
}

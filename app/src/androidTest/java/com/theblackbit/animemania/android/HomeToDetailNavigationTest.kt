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
import com.theblackbit.animemania.android.core.testing.data.anime.mostWantedAnimeData
import com.theblackbit.animemania.android.core.testing.data.anime.popularAnimeData
import com.theblackbit.animemania.android.core.testing.data.anime.topRatedAnimeData
import com.theblackbit.animemania.android.core.testing.data.anime.trendingAnimeData
import com.theblackbit.animemania.android.core.testing.data.collectionCategories
import com.theblackbit.animemania.android.core.testing.data.manga.mostWantedMangaData
import com.theblackbit.animemania.android.core.testing.data.manga.popularMangaData
import com.theblackbit.animemania.android.core.testing.data.manga.topRatedMangaData
import com.theblackbit.animemania.android.core.testing.data.manga.trendingMangaData
import com.theblackbit.animemania.android.core.testing.di.collectAnimeCategoriesUseCaseModuleTest
import com.theblackbit.animemania.android.core.testing.di.collectAnimeDataUseCaseTestModule
import com.theblackbit.animemania.android.core.testing.di.collectChaptersUseCaseTestModule
import com.theblackbit.animemania.android.core.testing.di.collectCharactersUseCaseTestModule
import com.theblackbit.animemania.android.core.testing.di.collectMangaCategoriesUseCaseModuleTest
import com.theblackbit.animemania.android.core.testing.di.collectMangaDataUseCaseTestModule
import com.theblackbit.animemania.android.detail.di.chapterTabViewModelModule
import com.theblackbit.animemania.android.detail.di.characterTabViewModelModule
import com.theblackbit.animemania.android.feature.detail.R
import com.theblackbit.animemania.android.home.adapter.DataViewHolder
import com.theblackbit.animemania.android.home.di.homeViewModelModule
import com.theblackbit.animemania.android.model.Collection
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
            collectAnimeDataUseCaseTestModule,
            collectAnimeCategoriesUseCaseModuleTest,
            collectMangaDataUseCaseTestModule,
            collectMangaCategoriesUseCaseModuleTest,
            collectChaptersUseCaseTestModule,
            collectCharactersUseCaseTestModule,
            homeViewModelModule,
            chapterTabViewModelModule,
            characterTabViewModelModule,
        ),
    )

    @Test
    fun testNavigationAnimeEachItemToDetail() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(ViewMatchers.isRoot()).perform(WaitFor(5000L))

        collectionCategories.forEachIndexed { parentIndex, _ ->
            when (parentIndex) {
                0 -> testTrendingAnimeDetailNavigation(parentIndex)
                1 -> testMostWantedAnimeDetailNavigation(parentIndex)
                2 -> testTopRatedAnimeDetailNavigation(parentIndex)
                3 -> testPopularAnimeDetailNavigation(parentIndex)
            }
        }
    }

    @Test
    fun testNavigationMangaEachItemToDetail() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withText("Manga"))
            .perform(click())
        onView(ViewMatchers.isRoot()).perform(WaitFor(5000L))

        collectionCategories.forEachIndexed { parentIndex, _ ->
            when (parentIndex) {
                0 -> testTrendingMangaDetailNavigation(parentIndex)
                1 -> testMostWantedMangaDetailNavigation(parentIndex)
                2 -> testTopRatedMangaDetailNavigation(parentIndex)
                3 -> testPopularMangaDetailNavigation(parentIndex)
            }
        }
    }

    @Test
    fun backNavigationFromDetailToHome() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(ViewMatchers.isRoot()).perform(WaitFor(5000L))
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

    private fun testTrendingAnimeDetailNavigation(parentIndex: Int) {
        trendingAnimeData.forEachIndexed { childIndex, collection ->
            validateDataPassBetweenFragments(parentIndex, childIndex, collection)
            pressBack()
            onView(ViewMatchers.isRoot()).perform(WaitFor(500L))
        }
    }

    private fun testMostWantedAnimeDetailNavigation(parentIndex: Int) {
        mostWantedAnimeData.forEachIndexed { childIndex, collection ->
            validateDataPassBetweenFragments(parentIndex, childIndex, collection)
            pressBack()
            onView(ViewMatchers.isRoot()).perform(WaitFor(500L))
        }
    }

    private fun testTopRatedAnimeDetailNavigation(parentIndex: Int) {
        topRatedAnimeData.forEachIndexed { childIndex, collection ->
            validateDataPassBetweenFragments(parentIndex, childIndex, collection)
            pressBack()
            onView(ViewMatchers.isRoot()).perform(WaitFor(500L))
        }
    }

    private fun testPopularAnimeDetailNavigation(parentIndex: Int) {
        popularAnimeData.forEachIndexed { childIndex, collection ->
            validateDataPassBetweenFragments(parentIndex, childIndex, collection)
            pressBack()
            onView(ViewMatchers.isRoot()).perform(WaitFor(500L))
        }
    }

    private fun testTrendingMangaDetailNavigation(parentIndex: Int) {
        trendingMangaData.forEachIndexed { childIndex, collection ->
            validateDataPassBetweenFragments(parentIndex, childIndex, collection)
            pressBack()
            onView(ViewMatchers.isRoot()).perform(WaitFor(500L))
        }
    }

    private fun testMostWantedMangaDetailNavigation(parentIndex: Int) {
        mostWantedMangaData.forEachIndexed { childIndex, collection ->
            validateDataPassBetweenFragments(parentIndex, childIndex, collection)
            pressBack()
            onView(ViewMatchers.isRoot()).perform(WaitFor(500L))
        }
    }

    private fun testTopRatedMangaDetailNavigation(parentIndex: Int) {
        topRatedMangaData.forEachIndexed { childIndex, collection ->
            validateDataPassBetweenFragments(parentIndex, childIndex, collection)
            pressBack()
            onView(ViewMatchers.isRoot()).perform(WaitFor(500L))
        }
    }

    private fun testPopularMangaDetailNavigation(parentIndex: Int) {
        popularMangaData.forEachIndexed { childIndex, collection ->
            validateDataPassBetweenFragments(parentIndex, childIndex, collection)
            pressBack()
            onView(ViewMatchers.isRoot()).perform(WaitFor(500L))
        }
    }

    private fun validateDataPassBetweenFragments(
        parentIndex: Int,
        childIndex: Int,
        collection: Collection,
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
                    positionOfViewInChildRecyclerView = childIndex,
                    childRecyclerViewId = homeR.id.rv_data_container,
                ),
            )
            .perform(
                ClickItemOnChildRecyclerViewItem<DataViewHolder>(
                    parentRecyclerViewId = homeR.id.rv_data,
                    positionOfChildRecyclerView = parentIndex,
                    positionOfViewInChildRecyclerView = childIndex,
                    childRecyclerViewId = homeR.id.rv_data_container,
                ),
            )

        onView(ViewMatchers.isRoot()).perform(WaitFor(1000L))

        onView(withId(R.id.tv_broadcast_state))
            .check(matches(withText(collection.status.name)))

        onView(withId(R.id.tv_title_name))
            .check(matches(withText(collection.name)))

        onView(withId(R.id.tv_rating))
            .check(matches(withText(collection.averageRating)))

        onView(withId(R.id.tv_emit))
            .check(matches(withText(collection.startDate)))

        onView(withId(R.id.tv_end_date))
            .check(matches(withText(collection.endDate)))
    }
}

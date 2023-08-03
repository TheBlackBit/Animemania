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
import com.theblackbit.animemania.android.core.testing.data.anime.trendingAnimeData
import com.theblackbit.animemania.android.core.testing.data.manga.trendingMangaData
import com.theblackbit.animemania.android.core.testing.di.collectAnimeCategoriesUseCaseModuleTest
import com.theblackbit.animemania.android.core.testing.di.collectAnimeDataUseCaseTestModule
import com.theblackbit.animemania.android.core.testing.di.collectMangaCategoriesUseCaseModuleTest
import com.theblackbit.animemania.android.core.testing.di.collectMangaDataUseCaseTestModule
import com.theblackbit.animemania.android.feature.home.R
import com.theblackbit.animemania.android.home.di.homeViewModelModule
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
            collectAnimeDataUseCaseTestModule,
            collectAnimeCategoriesUseCaseModuleTest,
            collectMangaDataUseCaseTestModule,
            collectMangaCategoriesUseCaseModuleTest,
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
        testTabWithViewPager("Anime", trendingAnimeData[0].name)
    }

    @Test
    fun testMangaTabInHomeFragment() {
        launchFragmentInContainer<HomeFragment>(
            themeResId = resources.style.Theme_Animemania,
        )
        testTabWithViewPager("Manga", trendingMangaData[0].name)
    }

    private fun testTabWithViewPager(tabText: String, contentText: String) {
        onView(withText(tabText))
            .perform(click())
        onView(ViewMatchers.isRoot()).perform(WaitFor(6000L))
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

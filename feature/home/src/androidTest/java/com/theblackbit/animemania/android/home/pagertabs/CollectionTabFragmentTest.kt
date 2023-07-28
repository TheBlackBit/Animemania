package com.theblackbit.animemania.android.home.pagertabs

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.theblackbit.animemania.android.core.testing.KoinTestRule
import com.theblackbit.animemania.android.core.testing.customviewaction.ScrollToPositionChildRecyclerView
import com.theblackbit.animemania.android.core.testing.customviewaction.WaitFor
import com.theblackbit.animemania.android.core.testing.customviewmatcher.HasEllipsizeEndAtPositionOnChildRecyclerView
import com.theblackbit.animemania.android.core.testing.customviewmatcher.ImageIsCropAtPositionOnChildRecyclerView
import com.theblackbit.animemania.android.core.testing.customviewmatcher.ImageIsLoadedAtPositionOnChildRecyclerView
import com.theblackbit.animemania.android.core.testing.customviewmatcher.MaxLinesTextViewAtPositionOnChildRecyclerView
import com.theblackbit.animemania.android.core.testing.customviewmatcher.WithTextAtPositionOnChildRecyclerView
import com.theblackbit.animemania.android.core.testing.data.CATEGORY_MOST_WANTED_ID
import com.theblackbit.animemania.android.core.testing.data.CATEGORY_TOP_RATED_ID
import com.theblackbit.animemania.android.core.testing.data.CATEGORY_TRENDING_ID
import com.theblackbit.animemania.android.core.testing.data.anime.mostWantedAnimeData
import com.theblackbit.animemania.android.core.testing.data.anime.popularAnimeData
import com.theblackbit.animemania.android.core.testing.data.anime.topRatedAnimeData
import com.theblackbit.animemania.android.core.testing.data.anime.trendingAnimeData
import com.theblackbit.animemania.android.core.testing.data.collectionCategories
import com.theblackbit.animemania.android.core.testing.data.manga.mostWantedMangaData
import com.theblackbit.animemania.android.core.testing.data.manga.popularMangaData
import com.theblackbit.animemania.android.core.testing.data.manga.topRatedMangaData
import com.theblackbit.animemania.android.core.testing.data.manga.trendingMangaData
import com.theblackbit.animemania.android.feature.home.R
import com.theblackbit.animemania.android.home.di.collectAnimeCategoriesUseCaseModuleTest
import com.theblackbit.animemania.android.home.di.collectAnimeDataUseCaseTestModule
import com.theblackbit.animemania.android.home.di.collectMangaCategoriesUseCaseModuleTest
import com.theblackbit.animemania.android.home.di.collectMangaDataUseCaseTestModule
import com.theblackbit.animemania.android.home.di.homeViewModelModule
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import com.theblackbit.animemania.android.core.resources.R as resources

@RunWith(AndroidJUnit4::class)
class CollectionTabFragmentTest : KoinTest {

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
    fun testAnimeFetchedWithCategories() {
        launchFragmentInContainer<AnimeTabFragment>(
            themeResId = resources.style.Theme_Animemania,
        )

        testProgressBarIsVisible()

        testRecyclerViewParentIsGone()

        waitUntilDataIsFetched()

        testProgressBarIsGone()

        collectionCategories.forEachIndexed { categoryIndex, category ->
            testCategoryNameDisplayedCorrectly(
                positionOfParentRV = categoryIndex,
                categoryName = category.categoryName,
            )

            when (category.categoryId) {
                CATEGORY_TRENDING_ID -> testAnimeTrendingCollection(categoryIndex)
                CATEGORY_MOST_WANTED_ID -> testAnimeMostWantedCollection(categoryIndex)
                CATEGORY_TOP_RATED_ID -> testAnimeTopRatedCollection(categoryIndex)
                else -> testAnimePopularCollection(categoryIndex)
            }
        }
    }

    @Test
    fun testMangaFetchedWithCategories() {
        launchFragmentInContainer<MangaTabFragment>(
            themeResId = resources.style.Theme_Animemania,
        )

        testProgressBarIsVisible()

        testRecyclerViewParentIsGone()

        waitUntilDataIsFetched()

        testProgressBarIsGone()

        collectionCategories.forEachIndexed { categoryIndex, category ->
            testCategoryNameDisplayedCorrectly(
                positionOfParentRV = categoryIndex,
                categoryName = category.categoryName,
            )

            when (category.categoryId) {
                CATEGORY_TRENDING_ID -> testMangaTrendingCollection(categoryIndex)
                CATEGORY_MOST_WANTED_ID -> testMangaMostWantedCollection(categoryIndex)
                CATEGORY_TOP_RATED_ID -> testMangaTopRatedCollection(categoryIndex)
                else -> testMangaPopularCollection(categoryIndex)
            }
        }
    }

    private fun testProgressBarIsVisible() {
        onView(withId(R.id.pb_collection))
            .check(matches(isDisplayed()))
    }

    private fun testRecyclerViewParentIsGone() {
        onView(withId(R.id.rv_data))
            .check(matches(not(isDisplayed())))
    }

    private fun waitUntilDataIsFetched() {
        onView(isRoot()).perform(WaitFor(6000L))
    }

    private fun testProgressBarIsGone() {
        onView(withId(R.id.pb_collection))
            .check(matches(not(isDisplayed())))
    }

    private fun testCategoryNameDisplayedCorrectly(positionOfParentRV: Int, categoryName: String) {
        onView(withId(R.id.rv_data))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    positionOfParentRV,
                ),
            )

        onView(withText(categoryName))
            .check(matches(isDisplayed()))
    }

    private fun testAnimeTrendingCollection(categoryIndex: Int) {
        trendingAnimeData.forEachIndexed { collectionIndex, collection ->
            testViewsInsideChildRecyclerView(
                positionOfParentRV = categoryIndex,
                positionOfChildRV = collectionIndex,
                title = collection.name,
            )
        }
    }

    private fun testAnimeMostWantedCollection(categoryIndex: Int) {
        mostWantedAnimeData.forEachIndexed { collectionIndex, collection ->
            testViewsInsideChildRecyclerView(
                positionOfParentRV = categoryIndex,
                positionOfChildRV = collectionIndex,
                title = collection.name,
            )
        }
    }

    private fun testAnimeTopRatedCollection(categoryIndex: Int) {
        topRatedAnimeData.forEachIndexed { collectionIndex, collection ->
            testViewsInsideChildRecyclerView(
                positionOfParentRV = categoryIndex,
                positionOfChildRV = collectionIndex,
                title = collection.name,
            )
        }
    }

    private fun testAnimePopularCollection(categoryIndex: Int) {
        popularAnimeData.forEachIndexed { collectionIndex, collection ->
            testViewsInsideChildRecyclerView(
                positionOfParentRV = categoryIndex,
                positionOfChildRV = collectionIndex,
                title = collection.name,
            )
        }
    }

    private fun testMangaTrendingCollection(categoryIndex: Int) {
        trendingMangaData.forEachIndexed { collectionIndex, collection ->
            testViewsInsideChildRecyclerView(
                positionOfParentRV = categoryIndex,
                positionOfChildRV = collectionIndex,
                title = collection.name,
            )
        }
    }

    private fun testMangaMostWantedCollection(categoryIndex: Int) {
        mostWantedMangaData.forEachIndexed { collectionIndex, collection ->
            testViewsInsideChildRecyclerView(
                positionOfParentRV = categoryIndex,
                positionOfChildRV = collectionIndex,
                title = collection.name,
            )
        }
    }

    private fun testMangaTopRatedCollection(categoryIndex: Int) {
        topRatedMangaData.forEachIndexed { collectionIndex, collection ->
            testViewsInsideChildRecyclerView(
                positionOfParentRV = categoryIndex,
                positionOfChildRV = collectionIndex,
                title = collection.name,
            )
        }
    }

    private fun testMangaPopularCollection(categoryIndex: Int) {
        popularMangaData.forEachIndexed { collectionIndex, collection ->
            testViewsInsideChildRecyclerView(
                positionOfParentRV = categoryIndex,
                positionOfChildRV = collectionIndex,
                title = collection.name,
            )
        }
    }

    private fun testViewsInsideChildRecyclerView(
        positionOfParentRV: Int,
        positionOfChildRV: Int,
        title: String,
    ) {
        onView(withId(R.id.rv_data))
            .perform(
                ScrollToPositionChildRecyclerView(
                    parentRecyclerViewId = R.id.rv_data,
                    positionOfChildRecyclerView = positionOfParentRV,
                    positionOfViewInChildRecyclerView = positionOfChildRV,
                    childRecyclerViewId = R.id.rv_data_container,
                ),
            )
            .check(
                matches(
                    WithTextAtPositionOnChildRecyclerView(
                        positionOfChildRecyclerView = positionOfParentRV,
                        childRecyclerViewId = R.id.rv_data_container,
                        positionOfViewInChildRecyclerView = positionOfChildRV,
                        textViewId = R.id.tv_collection_name,
                        text = title,
                    ),
                ),
            )
            .check(
                matches(
                    HasEllipsizeEndAtPositionOnChildRecyclerView(
                        positionOfChildRecyclerView = positionOfParentRV,
                        childRecyclerViewId = R.id.rv_data_container,
                        positionOfViewInChildRecyclerView = positionOfChildRV,
                        textViewId = R.id.tv_collection_name,
                    ),
                ),
            )
            .check(
                matches(
                    MaxLinesTextViewAtPositionOnChildRecyclerView(
                        positionOfChildRecyclerView = positionOfParentRV,
                        childRecyclerViewId = R.id.rv_data_container,
                        positionOfViewInChildRecyclerView = positionOfChildRV,
                        textViewId = R.id.tv_collection_name,
                        maxLines = 2,
                    ),
                ),
            )
            .check(
                matches(
                    ImageIsCropAtPositionOnChildRecyclerView(
                        positionOfChildRecyclerView = positionOfParentRV,
                        childRecyclerViewId = R.id.rv_data_container,
                        positionOfViewInChildRecyclerView = positionOfChildRV,
                        imageViewId = R.id.iv_poster,
                    ),
                ),
            )
            .check(
                matches(
                    ImageIsLoadedAtPositionOnChildRecyclerView(
                        positionOfChildRecyclerView = positionOfParentRV,
                        childRecyclerViewId = R.id.rv_data_container,
                        positionOfViewInChildRecyclerView = positionOfChildRV,
                        imageViewId = R.id.iv_poster,
                    ),
                ),
            )
    }
}

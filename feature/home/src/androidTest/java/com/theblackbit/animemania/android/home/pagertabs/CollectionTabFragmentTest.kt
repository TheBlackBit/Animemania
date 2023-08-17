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
import androidx.test.filters.LargeTest
import com.theblackbit.animemania.android.core.testing.KoinTestRule
import com.theblackbit.animemania.android.core.testing.customviewaction.ScrollToPositionChildRecyclerView
import com.theblackbit.animemania.android.core.testing.customviewaction.WaitFor
import com.theblackbit.animemania.android.core.testing.customviewmatcher.ChildRecyclerViewItemCountAssertion
import com.theblackbit.animemania.android.core.testing.customviewmatcher.HasEllipsizeEndAtPositionOnChildRecyclerView
import com.theblackbit.animemania.android.core.testing.customviewmatcher.MaxLinesTextViewAtPositionOnChildRecyclerView
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
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import com.theblackbit.animemania.android.core.resources.R as resources

@LargeTest
@RunWith(AndroidJUnit4::class)
class CollectionTabFragmentTest : KoinTest {

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

    /**
     * Tests the Anime tab
     * @see animeRemoteRepositoryMock this koin module contains the class that
     * returns the mocked data provided by a json from assets from the module Testing
     */
    @Test
    fun testAnimeFetchedWithCategories() {
        launchFragmentInContainer<AnimeTabFragment>(
            themeResId = resources.style.Theme_Animemania,
        )

        testProgressBarIsVisible()

        testRecyclerViewParentIsGone()

        waitUntilDataIsFetched()

        testProgressBarIsGone()

        testTrendingAnime()

        testMostAnticipatedAnime()

        testTopRatedAnime()

        testPopularAnime()
    }

    /**
     * Tests the Manga tab
     * @see mangaRemoteRepositoryMock this koin module contains the class that
     * returns the mocked data provided by a json from assets from the module Testing
     */
    @Test
    fun testMangaFetchedWithCategories() {
        launchFragmentInContainer<MangaTabFragment>(
            themeResId = resources.style.Theme_Animemania,
        )

        testProgressBarIsVisible()

        testRecyclerViewParentIsGone()

        waitUntilDataIsFetched()

        testProgressBarIsGone()

        testTrendingManga()

        testMostAnticipatedManga()

        testTopRatedManga()

        testPopularManga()
    }

    private fun testTrendingAnime() {
        scrollToChildRecyclerView(0)

        testSizeOfChildRecyclerViewRecyclerView(positionOfChildRecyclerView = 0, expectedCount = 20)

        testCategoryNameDisplayedCorrectly("Trending for this week")

        testFirstAndLastItemCollection(
            positionOfChildRecyclerView = 0,
            firstItemTitle = "One Piece",
            lastItemTitle = "Black Clover",
        )
    }

    private fun testMostAnticipatedAnime() {
        scrollToChildRecyclerView(1)

        testSizeOfChildRecyclerViewRecyclerView(expectedCount = 20, positionOfChildRecyclerView = 1)

        testCategoryNameDisplayedCorrectly("Most anticipated")

        testFirstAndLastItemCollection(
            positionOfChildRecyclerView = 1,
            firstItemTitle = "SPY×FAMILY Season 2",
            lastItemTitle = "Saint Seiya: Knights of the Zodiac Season 3",
        )

        testSizeOfChildRecyclerViewRecyclerView(expectedCount = 22, positionOfChildRecyclerView = 1)
    }

    private fun testTopRatedAnime() {
        scrollToChildRecyclerView(2)

        testSizeOfChildRecyclerViewRecyclerView(expectedCount = 20, positionOfChildRecyclerView = 2)

        testCategoryNameDisplayedCorrectly("Top rated")

        testFirstAndLastItemCollection(
            positionOfChildRecyclerView = 2,
            firstItemTitle = "Kimetsu no Yaiba: Yuukaku-hen",
            lastItemTitle = "Boku no Hero Academia 3",
        )

        testSizeOfChildRecyclerViewRecyclerView(expectedCount = 40, positionOfChildRecyclerView = 2)
    }

    private fun testPopularAnime() {
        scrollToChildRecyclerView(3)

        testSizeOfChildRecyclerViewRecyclerView(expectedCount = 20, positionOfChildRecyclerView = 3)

        testCategoryNameDisplayedCorrectly("Most popular")

        testFirstAndLastItemCollection(
            positionOfChildRecyclerView = 3,
            firstItemTitle = "Attack on Titan",
            lastItemTitle = "Nanatsu no Taizai: Imashime no Fukkatsu",
        )

        testSizeOfChildRecyclerViewRecyclerView(expectedCount = 40, positionOfChildRecyclerView = 3)
    }

    private fun testTrendingManga() {
        scrollToChildRecyclerView(0)

        testSizeOfChildRecyclerViewRecyclerView(positionOfChildRecyclerView = 0, expectedCount = 20)

        testCategoryNameDisplayedCorrectly("Trending for this week")

        testFirstAndLastItemCollection(
            positionOfChildRecyclerView = 0,
            firstItemTitle = "Martial Peak",
            lastItemTitle = "Komi-san wa, Comyushou desu.",
        )
    }

    private fun testMostAnticipatedManga() {
        scrollToChildRecyclerView(1)

        testSizeOfChildRecyclerViewRecyclerView(expectedCount = 20, positionOfChildRecyclerView = 1)

        testCategoryNameDisplayedCorrectly("Most anticipated")

        testFirstAndLastItemCollection(
            positionOfChildRecyclerView = 1,
            firstItemTitle = "Mushoku Tensei: Dasoku-hen",
            lastItemTitle = "Naneun Yeowangida",
        )

        testSizeOfChildRecyclerViewRecyclerView(expectedCount = 33, positionOfChildRecyclerView = 1)
    }

    private fun testTopRatedManga() {
        scrollToChildRecyclerView(2)

        testSizeOfChildRecyclerViewRecyclerView(expectedCount = 20, positionOfChildRecyclerView = 2)

        testCategoryNameDisplayedCorrectly("Top rated")

        testFirstAndLastItemCollection(
            positionOfChildRecyclerView = 2,
            firstItemTitle = "Boku no Hero Academia",
            lastItemTitle = "Jibaku Shounen Hanako-kun",
        )

        testSizeOfChildRecyclerViewRecyclerView(expectedCount = 40, positionOfChildRecyclerView = 2)
    }

    private fun testPopularManga() {
        scrollToChildRecyclerView(3)
        testSizeOfChildRecyclerViewRecyclerView(expectedCount = 20, positionOfChildRecyclerView = 3)

        testCategoryNameDisplayedCorrectly("Most popular")

        testFirstAndLastItemCollection(
            positionOfChildRecyclerView = 3,
            firstItemTitle = "Boku no Hero Academia",
            lastItemTitle = "Solo Leveling",
        )

        testSizeOfChildRecyclerViewRecyclerView(expectedCount = 40, positionOfChildRecyclerView = 3)
    }

    private fun testSizeOfChildRecyclerViewRecyclerView(
        positionOfChildRecyclerView: Int,
        expectedCount: Int,

    ) {
        onView(withId(R.id.rv_data))
            .check(
                matches(
                    ChildRecyclerViewItemCountAssertion(
                        positionOfChildRecyclerView = positionOfChildRecyclerView,
                        childRecyclerViewId = R.id.rv_data_container,
                        expectedCount,
                    ),
                ),
            )
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
        onView(isRoot()).perform(WaitFor(4000L))
    }

    private fun testProgressBarIsGone() {
        onView(withId(R.id.pb_collection))
            .check(matches(not(isDisplayed())))
    }

    private fun scrollToChildRecyclerView(position: Int) {
        onView(withId(R.id.rv_data))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    position,
                ),
            )
    }

    private fun testCategoryNameDisplayedCorrectly(categoryName: String) {
        onView(withText(categoryName))
            .check(matches(isDisplayed()))
    }

    private fun testFirstAndLastItemCollection(
        positionOfChildRecyclerView: Int,
        firstPosition: Int = 0,
        lastPosition: Int = 19,
        firstItemTitle: String,
        lastItemTitle: String,
    ) {
        testViewsInsideChildRecyclerView(
            positionOfParentRV = positionOfChildRecyclerView,
            positionOfChildRV = firstPosition,
            title = firstItemTitle,
        )

        testViewsInsideChildRecyclerView(
            positionOfParentRV = positionOfChildRecyclerView,
            positionOfChildRV = lastPosition,
            title = lastItemTitle,
        )
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
    }
}

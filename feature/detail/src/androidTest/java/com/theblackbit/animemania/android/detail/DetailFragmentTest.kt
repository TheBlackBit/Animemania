package com.theblackbit.animemania.android.detail

import android.os.Bundle
import android.text.TextUtils
import android.widget.ImageView
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.theblackbit.animemania.android.common.BundleKeys.COLLECTION_ID
import com.theblackbit.animemania.android.common.BundleKeys.COLLECTION_TYPE
import com.theblackbit.animemania.android.common.BundleKeys.COVER_IMAGE
import com.theblackbit.animemania.android.common.BundleKeys.END_DATE
import com.theblackbit.animemania.android.common.BundleKeys.GENRES
import com.theblackbit.animemania.android.common.BundleKeys.POSTER_IMAGE
import com.theblackbit.animemania.android.common.BundleKeys.RATING
import com.theblackbit.animemania.android.common.BundleKeys.START_DATE
import com.theblackbit.animemania.android.common.BundleKeys.STATE
import com.theblackbit.animemania.android.common.BundleKeys.SYNOPSIS
import com.theblackbit.animemania.android.common.BundleKeys.TITLE
import com.theblackbit.animemania.android.core.resources.R
import com.theblackbit.animemania.android.core.testing.KoinTestRule
import com.theblackbit.animemania.android.core.testing.customviewaction.WaitFor
import com.theblackbit.animemania.android.core.testing.customviewmatcher.HasEllipsizeAtPosition
import com.theblackbit.animemania.android.core.testing.customviewmatcher.ImageIsLoadedMatcher
import com.theblackbit.animemania.android.core.testing.customviewmatcher.MaxLinesTextView
import com.theblackbit.animemania.android.core.testing.customviewmatcher.RecyclerViewItemCountAssertion
import com.theblackbit.animemania.android.core.testing.customviewmatcher.WithImageScaleType
import com.theblackbit.animemania.android.core.testing.customviewmatcher.WithTextAtPosition
import com.theblackbit.animemania.android.data.di.internal.chapterDaoModule
import com.theblackbit.animemania.android.data.di.internal.chapterRoomRepositoryModule
import com.theblackbit.animemania.android.data.di.internal.characterDaoModule
import com.theblackbit.animemania.android.data.di.internal.characterRoomRepositoryModule
import com.theblackbit.animemania.android.data.di.internal.roomDbModule
import com.theblackbit.animemania.android.data.di.pagingsource.chapterPagingSourceFactoryModule
import com.theblackbit.animemania.android.data.di.pagingsource.characterFactoryPagingSourceModule
import com.theblackbit.animemania.android.data.pagingsource.chapter.ChapterPagingSourceFactory
import com.theblackbit.animemania.android.detail.di.animeEpisodesRepositoryModuleMock
import com.theblackbit.animemania.android.detail.di.chapterTabViewModelModule
import com.theblackbit.animemania.android.detail.di.characterTabViewModelModule
import com.theblackbit.animemania.android.detail.di.charactersRepositoryModuleMock
import com.theblackbit.animemania.android.detail.di.mangaChaptersRepositoryModuleMock
import com.theblackbit.animemania.android.detail.mockrepository.VALID_COLLECTION_ANIME_ID
import com.theblackbit.animemania.android.detail.mockrepository.VALID_COLLECTION_ID_MANGA
import com.theblackbit.animemania.android.detail.pagertabs.chapter.ChapterViewHolder
import com.theblackbit.animemania.android.domain.di.collectChaptersUseCaseModule
import com.theblackbit.animemania.android.domain.di.collectCharactersUseCaseModule
import com.theblackbit.animemania.android.model.Collection
import com.theblackbit.animemania.android.model.CollectionType
import com.theblackbit.animemania.android.model.Genre
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import com.theblackbit.animemania.android.feature.detail.R as detailRes

@LargeTest
@RunWith(AndroidJUnit4::class)
class DetailFragmentTest : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule(
        modules = listOf(
            roomDbModule,
            chapterDaoModule,
            characterDaoModule,
            chapterRoomRepositoryModule,
            characterRoomRepositoryModule,
            animeEpisodesRepositoryModuleMock,
            mangaChaptersRepositoryModuleMock,
            charactersRepositoryModuleMock,
            characterFactoryPagingSourceModule,
            chapterPagingSourceFactoryModule,
            collectChaptersUseCaseModule,
            collectCharactersUseCaseModule,
            chapterTabViewModelModule,
            characterTabViewModelModule
        )
    )

    @Test
    fun testDetailAnimeMainDataIsCorrectlyDisplayed() {
        launchFragment(collectionAnimeTest)
        testMainData(collectionAnimeTest)
    }

    @Test
    fun testMangaDetailAnimeMainDataIsCorrectlyDisplayed() {
        launchFragment(collectionMangaTest)
        testMainData(collectionMangaTest)
    }

    @Test
    fun testOverviewAnimeTabDataIsCorrectlyDisplayed() {
        launchFragment(collectionAnimeTest)
        testOverview(collectionAnimeTest)
    }

    @Test
    fun testOverviewMangaTabDataIsCorrectlyDisplayed() {
        launchFragment(collectionMangaTest)
        testOverview(collectionMangaTest)
    }

    @Test
    fun testAnimeEpisodesTab() {
        launchFragment(collectionAnimeTest)
        testChapters(
            firstChapterTitle = "I'm Luffy! The Man Who's Gonna Be King of the Pirates!"
        )
    }

    @Test
    fun testMangaChaptersTab() {
        launchFragment(collectionMangaTest)
        testChapters(
            firstChapterTitle = ""
        )
    }

    @Test
    fun testAnimeChaptersTabNoData() {
        launchFragment(collectionAnimeTest.copy(collectionId = "1"))
        onView(withText(R.string.chapters))
            .perform(click())
        testNoDataTab()
    }

    @Test
    fun testMangaChaptersTabNoData() {
        launchFragment(collectionMangaTest.copy(collectionId = "1"))
        onView(withText(R.string.chapters))
            .perform(click())
        testNoDataTab()
    }

    @Test
    fun testAnimeCharactersTab() {
        launchFragment(collectionAnimeTest)
        testCharacter("Nami", 17)
    }

    @Test
    fun testMangaCharactersTab() {
        launchFragment(collectionMangaTest)
        testCharacter("Isidro", 10)
    }

    @Test
    fun testAnimeCharactersTabNoData() {
        launchFragment(collectionAnimeTest.copy(collectionId = "1"))
        onView(withText(R.string.characters))
            .perform(click())
        testNoDataTab()
    }

    @Test
    fun testMangaCharactersTabNoData() {
        launchFragment(collectionMangaTest.copy(collectionId = "1"))
        onView(withText(R.string.characters))
            .perform(click())
        testNoDataTab()
    }

    private fun launchFragment(collection: Collection) {
        launchFragmentInContainer<DetailFragbment>(
            themeResId = R.style.Theme_Animemania,
            fragmentArgs = Bundle()
                .apply {
                    putString(COLLECTION_ID, collection.collectionId)
                    putString(COVER_IMAGE, collection.bigPosterImageUrl)
                    putString(POSTER_IMAGE, collection.miniPosterImageUrl)
                    putString(TITLE, collection.name)
                    putString(RATING, collection.averageRating)
                    putString(STATE, collection.status)
                    putString(START_DATE, collection.startDate)
                    putString(END_DATE, collection.endDate)
                    putString(COLLECTION_TYPE, collection.collectionType.name)
                    putString(
                        GENRES,
                        collection.genre.joinToString(separator = " \u25CF ") { it.name }
                    )
                    putString(SYNOPSIS, collection.synopsis)
                }
        )

        onView(ViewMatchers.isRoot()).perform(WaitFor(2000L))
    }

    private fun testMainData(collection: Collection) {
        onView(withId(detailRes.id.backIcon))
            .check(matches(isDisplayed()))

        onView(withId(detailRes.id.coverCollapse))
            .check(matches(WithImageScaleType(ImageView.ScaleType.CENTER_CROP)))
            .check(matches(ImageIsLoadedMatcher()))

        onView(withId(detailRes.id.imageCover))
            .check(matches(WithImageScaleType(ImageView.ScaleType.CENTER_CROP)))
            .check(matches(ImageIsLoadedMatcher()))

        onView(withId(detailRes.id.tv_title_name))
            .check(matches(withText(collection.name)))

        onView(withId(detailRes.id.tv_rating))
            .check(matches(withText(collection.averageRating)))
    }

    private fun testOverview(collection: Collection) {
        val endDateViewVisibilityMatcher =
            if (collection.endDate.isEmpty()) ViewMatchers.Visibility.GONE else ViewMatchers.Visibility.VISIBLE

        onView(withId(detailRes.id.iv_icon_broadcast))
            .check(matches(isDisplayed()))

        onView(withId(detailRes.id.tv_broadcast_title))
            .check(matches(withText(R.string.emitted_state)))
            .check(matches(isDisplayed()))

        onView(withId(detailRes.id.tv_broadcast_state))
            .check(matches(withText(collection.status)))

        onView(withId(detailRes.id.iv_calendar_start))
            .check(matches(isDisplayed()))

        onView(withId(detailRes.id.tv_emit_title))
            .check(matches(withText(R.string.emitted)))
            .check(matches(isDisplayed()))

        onView(withId(detailRes.id.tv_emit))
            .check(matches(withText(collection.startDate)))

        onView(withId(detailRes.id.iv_calendar_end))
            .check(matches(withEffectiveVisibility(endDateViewVisibilityMatcher)))

        onView(withId(detailRes.id.tv_end_title))
            .check(matches(withText(R.string.finished)))
            .check(matches(withEffectiveVisibility(endDateViewVisibilityMatcher)))

        onView(withId(detailRes.id.tv_end_date))
            .check(matches(withText(collection.endDate)))

        onView(withId(detailRes.id.tv_synopsys_title))
            .check(matches(withText(R.string.synopsis)))
            .check(matches(isDisplayed()))

        onView(withId(detailRes.id.synopsisText))
            .check(matches(withText(collection.synopsis)))
            .check(matches(isDisplayed()))

        onView(withId(detailRes.id.tv_no_synopsis))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }

    private fun testChapters(firstChapterTitle: String) {
        onView(withText(R.string.chapters))
            .perform(click())

        onView(withId(detailRes.id.pb_chapter))
            .check(matches(isDisplayed()))

        onView(ViewMatchers.isRoot()).perform(WaitFor(3000L))

        onView(withId(detailRes.id.rv_chapters))
            .check(
                matches(
                    RecyclerViewItemCountAssertion(
                        10
                    )
                )
            )

        testChapterViewData(chapterTitle = firstChapterTitle, position = 0)

        onView(withId(detailRes.id.rv_chapters))
            .perform(scrollToPosition<ChapterViewHolder>(ChapterPagingSourceFactory.CHAPTER_PAGE_LIMIT - 1))
        onView(ViewMatchers.isRoot()).perform(WaitFor(2000L))

        onView(withId(detailRes.id.rv_chapters))
            .check(
                matches(
                    RecyclerViewItemCountAssertion(
                        20
                    )
                )
            )
    }

    private fun testChapterViewData(chapterNumber: Int = 1, chapterTitle: String, position: Int) {
        val textValid =
            if (chapterTitle.isEmpty()) "$chapterNumber" else "$chapterNumber. $chapterTitle"
        onView(withId(detailRes.id.rv_chapters))
            .check(
                matches(
                    WithTextAtPosition(
                        text = textValid,
                        position = position,
                        textViewId = detailRes.id.tv_chapter_title
                    )
                )
            )
            .check(
                matches(
                    MaxLinesTextView(
                        position = position,
                        maxLines = 1,
                        textViewId = detailRes.id.tv_chapter_title
                    )
                )
            )
            .check(
                matches(
                    HasEllipsizeAtPosition(
                        position = position,
                        ellipsize = TextUtils.TruncateAt.END,
                        textViewId = detailRes.id.tv_chapter_title
                    )
                )
            )
    }

    private fun testNoDataTab() {
        onView(ViewMatchers.isRoot()).perform(WaitFor(1100L))

        onView(withText(R.string.no_info_yet))
            .check(matches(isDisplayed()))
    }

    private fun testCharacter(
        firstCharacterName: String,
        expectedCountFirstPage: Int
    ) {
        onView(withText(R.string.characters))
            .perform(click())

        onView(withId(detailRes.id.pb_character))
            .check(matches(isDisplayed()))

        onView(ViewMatchers.isRoot()).perform(WaitFor(3000L))

        onView(withId(detailRes.id.rv_characters))
            .check(
                matches(
                    RecyclerViewItemCountAssertion(
                        expectedCountFirstPage
                    )
                )
            )

        testCharacterViewData(name = firstCharacterName)

        onView(withId(detailRes.id.rv_characters))
            .perform(scrollToPosition<ChapterViewHolder>(expectedCountFirstPage - 1))
        onView(ViewMatchers.isRoot()).perform(WaitFor(1000L))
    }

    private fun testCharacterViewData(name: String, position: Int = 0) {
        onView(withId(detailRes.id.rv_characters))
            .check(
                matches(
                    WithTextAtPosition(
                        text = name,
                        position = position,
                        textViewId = detailRes.id.tv_character_name
                    )
                )
            )
            .check(
                matches(
                    MaxLinesTextView(
                        position = position,
                        maxLines = 1,
                        textViewId = detailRes.id.tv_character_name
                    )
                )
            )
            .check(
                matches(
                    HasEllipsizeAtPosition(
                        position = position,
                        ellipsize = TextUtils.TruncateAt.END,
                        textViewId = detailRes.id.tv_character_name
                    )
                )
            )
    }

    private val collectionAnimeTest = Collection(
        collectionId = VALID_COLLECTION_ANIME_ID,
        name = "One Piece",
        averageRating = "83.69%",
        startDate = "1999-10-20",
        endDate = "",
        genre = listOf(
            Genre("Fantasy"),
            Genre("Comedy"),
            Genre("Action")
        ),
        collectionType = CollectionType.ANIME,
        episodeCount = "29736",
        miniPosterImageUrl = "https://media.kitsu.io/anime/poster_images/12/large.jpg",
        bigPosterImageUrl = "https://media.kitsu.io/anime/12/cover_image/large-3e72f400a87b5241780c5082f0582611.jpeg",
        status = "Current",
        synopsis = "Gol D. Roger was known as the \"Pirate King,\" the strongest and most infamous being to have sailed the Grand Line. The capture and death of Roger by the World Government brought a change throughout the world. His last words before his death revealed the existence of the greatest treasure in the world, One Piece. It was this revelation that brought about the Grand Age of Pirates, men who dreamed of finding One Piece—which promises an unlimited amount of riches and fame—and quite possibly the pinnacle of glory and the title of the Pirate King. Enter Monkey D. Luffy, a 17-year-old boy who defies your standard definition of a pirate. Rather than the popular persona of a wicked, hardened, toothless pirate ransacking villages for fun, Luffy’s reason for being a pirate is one of pure wonder: the thought of an exciting adventure that leads him to intriguing people and ultimately, the promised treasure. Following in the footsteps of his childhood hero, Luffy and his crew travel across the Grand Line, experiencing crazy adventures, unveiling dark mysteries and battling strong enemies, all in order to reach the most coveted of all fortunes—One Piece. [Written by MAL Rewrite]"
    )

    private val collectionMangaTest = Collection(
        collectionId = VALID_COLLECTION_ID_MANGA,
        name = "Berserk",
        averageRating = "84.34%",
        startDate = "1989-08-25",
        endDate = "",
        genre = listOf(
            Genre("Fantasy"),
            Genre("Comedy"),
            Genre("Action")
        ),
        collectionType = CollectionType.MANGA,
        episodeCount = "",
        miniPosterImageUrl = "https://media.kitsu.io/manga/8/poster_image/large-f598ed525107bcb69ebda5bf36c6910d.jpeg",
        bigPosterImageUrl = "https://media.kitsu.io/manga/8/cover_image/large-d976a1baf3051d321608d4ebb7da4220.gif",
        status = "Current",
        synopsis = "His name is Guts, the Black Swordsman, a feared warrior spoken of only in whispers. Bearer of a gigantic sword, an iron hand, and the scars of countless battles and tortures, his flesh is also indelibly marked with The Brand, an unholy symbol that draws the forces of darkness to him and dooms him as their sacrifice. But Guts won't take his fate lying down; he'll cut a crimson swath of carnage through the ranks of the damned—and anyone else foolish enough to oppose him! Accompanied by Puck the Elf, more an annoyance than a companion, Guts relentlessly follows a dark, bloodstained path that leads only to death...or vengeance. (Source: Dark Horse) Notes: - Volumes 1-5 contain the 16 prequel chapters 0A - 0P. - Chapter 83 was omitted from Volume 13 due to the author’s request. - Volume 14 includes “Berserk: The Prototype”. - Due to the author's passing, starting from Chapter 365, the manga is illustrated by Studio Gaga (Miura's assistants) and supervised by Kouji Mori (a close friend of Miura's)."
    )
}

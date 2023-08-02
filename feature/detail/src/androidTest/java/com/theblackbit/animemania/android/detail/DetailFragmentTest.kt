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
import com.theblackbit.animemania.android.core.resources.R
import com.theblackbit.animemania.android.core.testing.KoinTestRule
import com.theblackbit.animemania.android.core.testing.customviewaction.WaitFor
import com.theblackbit.animemania.android.core.testing.customviewmatcher.HasEllipsizeAtPosition
import com.theblackbit.animemania.android.core.testing.customviewmatcher.ImageIsLoadedAtPosition
import com.theblackbit.animemania.android.core.testing.customviewmatcher.ImageIsLoadedMatcher
import com.theblackbit.animemania.android.core.testing.customviewmatcher.MaxLinesTextView
import com.theblackbit.animemania.android.core.testing.customviewmatcher.WithImageScaleType
import com.theblackbit.animemania.android.core.testing.customviewmatcher.WithImageScaleTypeAtPosition
import com.theblackbit.animemania.android.core.testing.customviewmatcher.WithTextAtPosition
import com.theblackbit.animemania.android.core.testing.data.chapter.chaptersData
import com.theblackbit.animemania.android.core.testing.data.character.charactersData
import com.theblackbit.animemania.android.detail.DetailFragment.Companion.COLLECTION_ID
import com.theblackbit.animemania.android.detail.DetailFragment.Companion.COVER_IMAGE
import com.theblackbit.animemania.android.detail.DetailFragment.Companion.POSTER_IMAGE
import com.theblackbit.animemania.android.detail.DetailFragment.Companion.RATING
import com.theblackbit.animemania.android.detail.DetailFragment.Companion.TITLE
import com.theblackbit.animemania.android.detail.di.chapterTabViewModelModule
import com.theblackbit.animemania.android.detail.di.characterTabViewModelModule
import com.theblackbit.animemania.android.detail.di.collectChaptersUseCaseTestModule
import com.theblackbit.animemania.android.detail.di.collectCharactersUseCaseTestModule
import com.theblackbit.animemania.android.detail.pagertabs.OverviewTabFragment.Companion.END_DATE
import com.theblackbit.animemania.android.detail.pagertabs.OverviewTabFragment.Companion.GENRES
import com.theblackbit.animemania.android.detail.pagertabs.OverviewTabFragment.Companion.START_DATE
import com.theblackbit.animemania.android.detail.pagertabs.OverviewTabFragment.Companion.STATE
import com.theblackbit.animemania.android.detail.pagertabs.OverviewTabFragment.Companion.SYNOPSIS
import com.theblackbit.animemania.android.detail.pagertabs.chapter.ChapterViewHolder
import com.theblackbit.animemania.android.model.Collection
import com.theblackbit.animemania.android.model.CollectionType
import com.theblackbit.animemania.android.model.Genre
import com.theblackbit.animemania.android.model.StatusOfEmission
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import com.theblackbit.animemania.android.feature.detail.R as detailRes

@RunWith(AndroidJUnit4::class)
class DetailFragmentTest : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule(
        modules = listOf(
            collectChaptersUseCaseTestModule,
            collectCharactersUseCaseTestModule,
            chapterTabViewModelModule,
            characterTabViewModelModule,
        ),
    )

    private val collectionAnimeTest = Collection(
        collectionId = "44081",
        name = "Demon Slayer: Kimetsu no Yaiba - Entertainment District Arc",
        averageRating = "87.44%",
        startDate = "2021/12/05",
        endDate = "2022/02/13",
        genre = listOf(
            Genre("Fantasy"),
            Genre("Comedy"),
            Genre("Action"),
        ),
        collectionType = CollectionType.ANIME,
        episodeCount = "11",
        miniPosterImageUrl = "https://media.kitsu.io/anime/44081/cover_image/76f0b283fe588a7b0c4782d4c2dc9b99.jpg",
        bigPosterImageUrl = "https://media.kitsu.io/anime/44081/cover_image/large-a5ef1fa23f686e8f2b11f4d33fe38b92.jpeg",
        status = StatusOfEmission.FINISHED,
        synopsis = "After visiting the Rengoku residence, Tanjirou and his comrades volunteer for a mission within the Entertainment District, a place where desires are sold and demons dwell. They journey alongside the flashy Sound Hashira, Tengen Uzui, in search of a monstrous foe terrorizing the town. Sworn to slay creatures of the night, the hunt continues. (Source: Funimation)",
    )

    @Test
    fun testDetailMainDataIsCorrectlyDisplayed() {
        launchFragment()
        onView(withId(detailRes.id.backIcon))
            .check(matches(isDisplayed()))

        onView(withId(detailRes.id.coverCollapse))
            .check(matches(WithImageScaleType(ImageView.ScaleType.CENTER_CROP)))
            .check(matches(ImageIsLoadedMatcher()))

        onView(withId(detailRes.id.imageCover))
            .check(matches(WithImageScaleType(ImageView.ScaleType.CENTER_CROP)))
            .check(matches(ImageIsLoadedMatcher()))

        onView(withId(detailRes.id.tv_title_name))
            .check(matches(withText(collectionAnimeTest.name)))

        onView(withId(detailRes.id.tv_rating))
            .check(matches(withText(collectionAnimeTest.averageRating)))
    }

    @Test
    fun testOverviewTabDataIsCorrectlyDisplayed() {
        launchFragment()

        onView(withId(detailRes.id.iv_icon_broadcast))
            .check(matches(isDisplayed()))

        onView(withId(detailRes.id.tv_broadcast_title))
            .check(matches(withText(R.string.emitted_state)))
            .check(matches(isDisplayed()))

        onView(withId(detailRes.id.tv_broadcast_state))
            .check(matches(withText(collectionAnimeTest.status.name)))

        onView(withId(detailRes.id.iv_calendar_start))
            .check(matches(isDisplayed()))

        onView(withId(detailRes.id.tv_emit_title))
            .check(matches(withText(R.string.emitted)))
            .check(matches(isDisplayed()))

        onView(withId(detailRes.id.tv_emit))
            .check(matches(withText(collectionAnimeTest.startDate)))

        onView(withId(detailRes.id.iv_calendar_end))
            .check(matches(isDisplayed()))

        onView(withId(detailRes.id.tv_end_title))
            .check(matches(withText(R.string.finished)))
            .check(matches(isDisplayed()))

        onView(withId(detailRes.id.tv_end_date))
            .check(matches(withText(collectionAnimeTest.endDate)))

        onView(withId(detailRes.id.tv_synopsys_title))
            .check(matches(withText(R.string.synopsis)))
            .check(matches(isDisplayed()))

        onView(withId(detailRes.id.synopsisText))
            .check(matches(withText(collectionAnimeTest.synopsis)))
            .check(matches(isDisplayed()))

        onView(withId(detailRes.id.tv_no_synopsis))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }

    @Test
    fun testChaptersTab() {
        launchFragment()
        onView(withText(R.string.chapters))
            .perform(click())

        onView(withId(detailRes.id.pb_chapter))

        onView(ViewMatchers.isRoot()).perform(WaitFor(3000L))

        chaptersData.forEachIndexed { index, chapter ->
            onView(withId(detailRes.id.rv_chapters))
                .perform(scrollToPosition<ChapterViewHolder>(index))
            onView(ViewMatchers.isRoot()).perform(WaitFor(1000L))

            onView(withId(detailRes.id.rv_chapters))
                .check(
                    matches(
                        WithTextAtPosition(
                            text = "${chapter.number}. ${chapter.title}",
                            position = index,
                            textViewId = detailRes.id.tv_chapter_title,
                        ),
                    ),
                )
                .check(
                    matches(
                        MaxLinesTextView(
                            position = index,
                            maxLines = 1,
                            textViewId = detailRes.id.tv_chapter_title,
                        ),
                    ),
                )
                .check(
                    matches(
                        HasEllipsizeAtPosition(
                            position = index,
                            ellipsize = TextUtils.TruncateAt.END,
                            textViewId = detailRes.id.tv_chapter_title,
                        ),
                    ),
                )
                .check(
                    matches(
                        WithImageScaleTypeAtPosition(
                            position = index,
                            scaleType = ImageView.ScaleType.CENTER_CROP,
                            imageViewId = detailRes.id.iv_chapter,
                        ),
                    ),
                )
                .check(
                    matches(
                        ImageIsLoadedAtPosition(
                            position = index,
                            imageViewId = detailRes.id.iv_chapter,
                        ),
                    ),
                )
        }
    }

    @Test
    fun testCharactersTab() {
        launchFragment()
        onView(withText(R.string.characters))
            .perform(click())

        onView(withId(detailRes.id.pb_character))

        onView(ViewMatchers.isRoot()).perform(WaitFor(3000L))

        charactersData.forEachIndexed { index, character ->
            onView(withId(detailRes.id.rv_characters))
                .perform(scrollToPosition<ChapterViewHolder>(index))
            onView(ViewMatchers.isRoot()).perform(WaitFor(1000L))

            onView(withId(detailRes.id.rv_characters))
                .check(
                    matches(
                        WithTextAtPosition(
                            text = character.name,
                            position = index,
                            textViewId = detailRes.id.tv_character_name,
                        ),
                    ),
                )
                .check(
                    matches(
                        MaxLinesTextView(
                            position = index,
                            maxLines = 1,
                            textViewId = detailRes.id.tv_character_name,
                        ),
                    ),
                )
                .check(
                    matches(
                        HasEllipsizeAtPosition(
                            position = index,
                            ellipsize = TextUtils.TruncateAt.END,
                            textViewId = detailRes.id.tv_character_name,
                        ),
                    ),
                )
                .check(
                    matches(
                        WithImageScaleTypeAtPosition(
                            position = index,
                            scaleType = ImageView.ScaleType.CENTER_CROP,
                            imageViewId = detailRes.id.iv_character,
                        ),
                    ),
                )
                .check(
                    matches(
                        ImageIsLoadedAtPosition(
                            position = index,
                            imageViewId = detailRes.id.iv_character,
                        ),
                    ),
                )
        }
    }

    private fun launchFragment() {
        launchFragmentInContainer<DetailFragment>(
            themeResId = R.style.Theme_Animemania,
            fragmentArgs = Bundle()
                .apply {
                    putString(COLLECTION_ID, collectionAnimeTest.collectionId)
                    putString(TITLE, collectionAnimeTest.startDate)
                    putString(COVER_IMAGE, collectionAnimeTest.bigPosterImageUrl)
                    putString(POSTER_IMAGE, collectionAnimeTest.miniPosterImageUrl)
                    putString(TITLE, collectionAnimeTest.name)
                    putString(RATING, collectionAnimeTest.averageRating)
                    putString(STATE, collectionAnimeTest.status.name)
                    putString(START_DATE, collectionAnimeTest.startDate)
                    putString(END_DATE, collectionAnimeTest.endDate)
                    putString(
                        GENRES,
                        collectionAnimeTest.genre.joinToString(separator = " \u25CF ") { it.name },
                    )
                    putString(SYNOPSIS, collectionAnimeTest.synopsis)
                },
        )

        onView(ViewMatchers.isRoot()).perform(WaitFor(2000L))
    }
}

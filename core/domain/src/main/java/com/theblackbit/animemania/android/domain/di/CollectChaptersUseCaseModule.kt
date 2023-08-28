package com.theblackbit.animemania.android.domain.di

import androidx.paging.PagingConfig
import com.theblackbit.animemania.android.data.pagingsource.chapter.ChapterPagingSourceFactory
import com.theblackbit.animemania.android.domain.usecase.CollectChaptersUseCase
import com.theblackbit.animemania.android.domain.usecase.CollectChaptersUseCaseImpl
import org.koin.dsl.module

val collectChaptersUseCaseModule = module {
    factory<CollectChaptersUseCase> {
        CollectChaptersUseCaseImpl(
            get(),
            PagingConfig(
                pageSize = ChapterPagingSourceFactory.CHAPTER_PAGE_LIMIT,
                prefetchDistance = ChapterPagingSourceFactory.CHAPTER_PAGE_LIMIT / 2,
                enablePlaceholders = true
            )
        )
    }
}

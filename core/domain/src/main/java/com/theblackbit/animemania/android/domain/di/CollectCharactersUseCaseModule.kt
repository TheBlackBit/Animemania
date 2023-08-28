package com.theblackbit.animemania.android.domain.di

import androidx.paging.PagingConfig
import com.theblackbit.animemania.android.data.pagingsource.character.CharacterPagingSourceFactory
import com.theblackbit.animemania.android.domain.usecase.CollectCharactersUseCase
import com.theblackbit.animemania.android.domain.usecase.CollectCharactersUseCaseImpl
import org.koin.dsl.module

val collectCharactersUseCaseModule = module {
    factory<CollectCharactersUseCase> {
        CollectCharactersUseCaseImpl(
            get(),
            PagingConfig(
                pageSize = CharacterPagingSourceFactory.CHARACTER_PAGE_LIMIT,
                prefetchDistance = CharacterPagingSourceFactory.CHARACTER_PAGE_LIMIT / 2,
                enablePlaceholders = false
            )
        )
    }
}

package com.example.data.repositoryImpl.newsSources

import com.example.domain.contract.repository.newsSourceRepo.NewsSourceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class NewsSourceRepoModule{

    @Binds
    abstract fun provideNewsSourceRepo(
        newsSourcesRepositoryImpl: NewsSourcesRepositoryImpl)
    :NewsSourceRepository
}
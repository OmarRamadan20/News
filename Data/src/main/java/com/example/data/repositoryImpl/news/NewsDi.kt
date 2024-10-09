package com.example.data.repositoryImpl.news

import com.example.domain.contract.repository.newsRepo.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class NewsRepoModule{

    @Binds
    abstract fun proviceNewsRepo(newsRepositoryImpl: NewsRepositoryImpl):NewsRepository

}
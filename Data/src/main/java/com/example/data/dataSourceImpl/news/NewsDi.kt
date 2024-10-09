package com.example.data.dataSourceImpl.news

import com.example.data.contract.datasource.news.NewsOnlineDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class NewsDataSourceModule {

    @Binds
    abstract fun provideOnlineDataSource(
        newsOnlineDataSourceImpl: NewsOnlineDataSourceImpl)
            : NewsOnlineDataSource
}
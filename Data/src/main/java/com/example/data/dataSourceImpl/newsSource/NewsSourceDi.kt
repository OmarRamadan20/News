package com.example.data.dataSourceImpl.newsSource

import androidx.lifecycle.ViewModel
import com.example.data.contract.datasource.news.NewsOnlineDataSource
import com.example.data.contract.datasource.newsSource.NewsSourceOnlineDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class NewsSourceDataSourceModule {

    @Binds
    abstract fun provideNewsSourceDataSource(
        newsSourceDataSourceImpl: NewsSourceDataSourceImpl
    ): NewsSourceOnlineDataSource
}
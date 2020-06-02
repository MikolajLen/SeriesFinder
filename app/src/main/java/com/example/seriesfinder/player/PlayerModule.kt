package com.example.seriesfinder.player

import dagger.Module
import dagger.Provides

@Module
class PlayerModule {

    @Provides
    fun provideSourceProvider(): SourceProvider = MockedSourceProvider()
}

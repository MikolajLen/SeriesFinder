package com.example.seriesfinder.player

import android.net.Uri
import com.example.seriesfinder.BuildConfig
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory

class MockedSourceProvider : SourceProvider {

    override fun provideMediaSource() =
        ProgressiveMediaSource.Factory(DefaultHttpDataSourceFactory("defaultAgent"))
            .createMediaSource(Uri.parse(BuildConfig.MOVIE_URL))!!
}

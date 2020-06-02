package com.example.seriesfinder.player

import com.google.android.exoplayer2.source.MediaSource

interface SourceProvider {

    fun provideMediaSource(): MediaSource
}

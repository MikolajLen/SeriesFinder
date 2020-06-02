package com.example.seriesfinder.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.seriesfinder.R
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.movie_player_layout.*
import javax.inject.Inject

class MoviePlayerFragment : DaggerFragment() {

    private lateinit var player: SimpleExoPlayer

    @Inject
    lateinit var sourceProvider: SourceProvider

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.movie_player_layout, container, false)

    override fun onResume() {
        super.onResume()
        initPlayer()
    }

    override fun onPause() {
        super.onPause()
        player.release()
    }

    private fun initPlayer() {
        player = SimpleExoPlayer.Builder(requireContext())
            .setTrackSelector(DefaultTrackSelector(requireContext()))
            .setLoadControl(DefaultLoadControl())
            .build()
        player_view.setPlayer(player)
        player.prepare(sourceProvider.provideMediaSource(), true, false)
        player.playWhenReady = true
    }
}

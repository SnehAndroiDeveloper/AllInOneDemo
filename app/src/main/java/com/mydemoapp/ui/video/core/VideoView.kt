package com.mydemoapp.ui.video.core

import android.net.Uri
import com.mydemoapp.data.database.repository.gif.Gif
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.mydemoapp.R
import com.mydemoapp.ui.video.VideoActivity
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_video.view.*


class VideoView(activity: VideoActivity, gif: Gif) {
    private var view: View

    private var player: SimpleExoPlayer? = null

    init {
        val layout = FrameLayout(activity)
        layout.layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        view = LayoutInflater.from(activity).inflate(R.layout.activity_video, layout, true)

        initializePlayer(gif)
    }

    private fun initializePlayer(gif: Gif) {
        val bandwidthMeter = DefaultBandwidthMeter()
        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter)
        val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)

        //Initialize the player
        player = ExoPlayerFactory.newSimpleInstance(view.context, trackSelector)
        //Initialize simpleExoPlayerView
        view.exoplayer.player = player

        // Produces DataSource instances through which media data is loaded.
        val dataSourceFactory = DefaultDataSourceFactory(view.context, Util.getUserAgent(view.context, view.context.getString(R.string.app_name)))

        // Produces Extractor instances for parsing the media data.
        val extractorsFactory = DefaultExtractorsFactory()

        // This is the MediaSource representing the media to be played.
        val videoUri = Uri.parse(gif.videoURL)
        val videoSource = ExtractorMediaSource(videoUri,
                dataSourceFactory, extractorsFactory, null, null)

        // Prepare the player with the source.
        player?.prepare(videoSource)
    }

    fun view(): View {
        return view
    }

    fun onPause() {
        if (player != null) {
            player?.release()
            player = null
        }
    }
}
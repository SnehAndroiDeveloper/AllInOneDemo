package com.mydemoapp.ui.video.dagger

import com.mydemoapp.data.database.repository.gif.Gif
import com.mydemoapp.ui.video.VideoActivity
import com.mydemoapp.ui.video.core.VideoView
import dagger.Module
import dagger.Provides


@Module
class VideoModule(var detailsContext: VideoActivity, var gif: Gif) {

    @Provides
    fun provideView(): VideoView {
        return VideoView(detailsContext, gif)
    }
}
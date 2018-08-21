package com.mydemoapp.ui.video.dagger

import com.mydemoapp.ui.video.VideoActivity
import dagger.Component

@Component(modules = [VideoModule::class])
interface VideoComponent {
    fun inject(context: VideoActivity)
}
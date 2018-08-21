package com.mydemoapp.ui.video

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mydemoapp.common.utils.Constants
import com.mydemoapp.data.database.repository.gif.Gif
import com.mydemoapp.ui.video.core.VideoView
import com.mydemoapp.ui.video.dagger.DaggerVideoComponent
import com.mydemoapp.ui.video.dagger.VideoModule
import javax.inject.Inject


class VideoActivity : AppCompatActivity() {
    @Inject
    lateinit var view: VideoView

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val arrGif = intent.extras!!.getParcelableArrayList<Gif>(Constants.INTENT_GIF_ARRAY)

        val position = intent.extras!!.getInt(Constants.INTENT_POSITION)

        DaggerVideoComponent.builder().videoModule(VideoModule(this, arrGif[position])).build().inject(this)

        setContentView(view.view())

    }

    override fun onPause() {
        super.onPause()
        view.onPause()
    }
}
package com.mydemoapp.ui.gif

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.mydemoapp.MyDemoApp
import com.mydemoapp.data.model.GifDataModel
import com.mydemoapp.ui.gif.core.GifPresenter
import com.mydemoapp.ui.gif.core.GifView
import com.mydemoapp.ui.gif.dagger.DaggerGifComponent
import com.mydemoapp.ui.gif.dagger.GifModule
import com.mydemoapp.ui.video.VideoActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var view: GifView
    @Inject
    lateinit var presenter: GifPresenter


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerGifComponent.builder().appComponent(MyDemoApp.getNetComponent()).gifModule(GifModule(this)).build().inject(this)
        setContentView(view.view())
        presenter.onCreate()

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    fun goToVideoActivity(gifDataModel: GifDataModel) {
        val intent = Intent(this, VideoActivity::class.java)
        startActivity(intent)

    }

}

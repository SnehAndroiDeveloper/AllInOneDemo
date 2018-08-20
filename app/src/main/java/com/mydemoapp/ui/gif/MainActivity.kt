package com.mydemoapp.ui.gif

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.mydemoapp.MyDemoApp
import com.mydemoapp.data.database.repository.gif.Gif
import com.mydemoapp.ui.gif.core.GifPresenter
import com.mydemoapp.ui.gif.core.GifView
import com.mydemoapp.ui.gif.dagger.DaggerGifComponent
import com.mydemoapp.ui.gif.dagger.GifModule
import com.mydemoapp.ui.video.VideoActivity
import kotlinx.android.synthetic.main.activity_main.view.*
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

        view.view().ivSearch.setOnClickListener { presenter.searchClick(view.view().tetSearch.text.toString()) }

        presenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    fun goToVideoActivity(position: Int, arrGifDataModel: ArrayList<Gif>) {
//        TODO:
        val intent = Intent(this, VideoActivity::class.java)
        startActivity(intent)
    }

}

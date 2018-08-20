package com.mydemoapp.ui.gif.dagger

import com.mydemoapp.di.component.AppComponent
import com.mydemoapp.ui.gif.MainActivity
import dagger.Component


@GifScope
@Component(dependencies = [AppComponent::class], modules = [GifModule::class])
interface GifComponent {

    fun inject(mainActivity: MainActivity)
}
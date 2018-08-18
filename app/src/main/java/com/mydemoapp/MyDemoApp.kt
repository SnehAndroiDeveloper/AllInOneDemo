package com.mydemoapp

import android.app.Activity
import android.app.Application
import android.content.pm.PackageManager
import android.text.TextUtils
import com.facebook.stetho.Stetho
import com.mydemoapp.common.utils.Utils
import com.mydemoapp.di.component.AppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import java.io.File
import javax.inject.Inject

class MyDemoApp : Application(), HasActivityInjector {
    @Inject
    internal lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)

        setCacheDirectory()
        Stetho.initializeWithDefaults(this)
    }

    private fun setCacheDirectory() {
        Utils.CACHE_DIRECTORY = externalCacheDir!!.toString() + File.separator
        if (TextUtils.isEmpty(Utils.CACHE_DIRECTORY) || Utils.CACHE_DIRECTORY!!.toLowerCase().contains("null".toLowerCase())) {
            try {
                Utils.CACHE_DIRECTORY = packageManager.getPackageInfo(packageName, 0).applicationInfo.dataDir + File.separator
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }

        }
    }

    override fun activityInjector() = activityDispatchingAndroidInjector
}
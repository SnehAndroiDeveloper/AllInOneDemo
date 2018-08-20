package com.mydemoapp

import android.app.Application
import android.content.pm.PackageManager
import android.text.TextUtils
import com.facebook.stetho.Stetho
import com.mydemoapp.common.utils.Utils
import com.mydemoapp.di.component.AppComponent
import com.mydemoapp.di.component.DaggerAppComponent
import com.mydemoapp.di.module.AppContextModule
import java.io.File


class MyDemoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appContextModule(AppContextModule(this)).build()
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

    companion object {
        private lateinit var appComponent: AppComponent

        fun getNetComponent(): AppComponent {
            return appComponent
        }
    }
}
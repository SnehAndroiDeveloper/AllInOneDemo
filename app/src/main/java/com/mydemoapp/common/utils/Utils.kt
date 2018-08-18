package com.mydemoapp.common.utils

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Build
import android.support.v4.content.FileProvider
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.mydemoapp.R
import java.io.File

object Utils {
    var CACHE_DIRECTORY: String? = null
    fun hideSoftKeyBoard(context: Context, view: View) {
        try {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Method used for creating new Photo path
     */
    fun createPhotoFile(mActivity: Activity): File {
        val file = File(CACHE_DIRECTORY)
        if (file.exists() && file.listFiles() != null) {
            for (f in file.listFiles()!!) {
                f.delete()
            }
        }

        return File(CACHE_DIRECTORY + mActivity.getString(R.string.app_name) + System.currentTimeMillis() + ".png")
    }

    /**
     * Method used for creating new Photo path
     */
    fun createPDFFile(mActivity: Activity, floorId: Int): File {
        return File(CACHE_DIRECTORY + mActivity.getString(R.string.app_name) + floorId + ".pdf")
    }

    /**
     * to get file uri as per OS version check for pre Marshmallow uri also
     *
     * @param mContext
     * @param mFile
     * @return
     */
    fun getUri(mContext: Context?, mFile: File): Uri {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (mContext != null) {
                FileProvider.getUriForFile(mContext, mContext.applicationContext.packageName + ".fileprovider", mFile)
            } else {
                Uri.fromFile(mFile)
            }
        } else {
            Uri.fromFile(mFile)
        }
    }
}
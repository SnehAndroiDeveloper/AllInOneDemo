package com.mydemoapp.common.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import com.mydemoapp.R

class PermissionUtil(var onPermissionListener: OnPermissionListener?) {

    fun requestPermissionsFragment(fragment: Fragment, requestedPermissions: Array<String>,
                                   requestCode: Int) {
        var permissionCheck = PackageManager.PERMISSION_GRANTED
        var shouldShowRequestPermissionRationale = false
        for (permission in requestedPermissions) {
            permissionCheck += ContextCompat.checkSelfPermission(fragment.context!!, permission)
            shouldShowRequestPermissionRationale = shouldShowRequestPermissionRationale || fragment.shouldShowRequestPermissionRationale(permission)
        }
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            fragment.requestPermissions(requestedPermissions, requestCode)
        } else {
            if (onPermissionListener != null) {
                onPermissionListener!!.onPermissionsGranted(requestCode)
            }
        }
    }

    fun handleRequestPermissionsResult(requestCode: Int, grantResults: IntArray) {
        var permissionCheck = PackageManager.PERMISSION_GRANTED
        for (permission in grantResults) {
            permissionCheck += permission
        }
        if (grantResults.isNotEmpty() && permissionCheck == PackageManager.PERMISSION_GRANTED) {
            onPermissionListener!!.onPermissionsGranted(requestCode)
        } else {
            onPermissionListener!!.onPermissionDenied(requestCode)
        }
    }

    interface OnPermissionListener {
        fun onPermissionsGranted(requestCode: Int)

        fun onPermissionDenied(requestCode: Int)
    }

    fun requestPermissionsActivity(activity: Activity, requestedPermissions: Array<String>,
                                   requestCode: Int) {
        var permissionCheck = PackageManager.PERMISSION_GRANTED
        var shouldShowRequestPermissionRationale = false
        for (permission in requestedPermissions) {
            permissionCheck += ContextCompat.checkSelfPermission(activity, permission)
            shouldShowRequestPermissionRationale = shouldShowRequestPermissionRationale || ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)
        }
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, requestedPermissions, requestCode)
        } else {
            if (onPermissionListener != null) {
                onPermissionListener!!.onPermissionsGranted(requestCode)
            }
        }
    }

    companion object : DialogUtils.DialogButtonListener {
        override fun onPositiveButtonClicked() {
        }

        override fun onNegativeButtonClicked() {
        }

        fun showRequestPermissionSettingsDialog(activity: Activity) {
            if (!activity.isFinishing) {
                DialogUtils.displayDialog(
                        activity,
                        activity.getString(R.string.permission_title),
                        activity.getString(R.string.enable_permission_msg),
                        activity.getString(R.string.enable),
                        activity.getString(R.string.deny),
                        true,
                        true, this

                )
            }
        }

        fun checkPermissions(context: Context, requestedPermission: Array<String>): Boolean {
            for (aRequestedPermission in requestedPermission) {
                if (ContextCompat.checkSelfPermission(context, aRequestedPermission) != PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
            return true
        }

        fun checkPermission(context: Context, requestedPermission: String): Boolean {
            return ContextCompat.checkSelfPermission(context, requestedPermission) == PackageManager.PERMISSION_GRANTED
        }
    }
}

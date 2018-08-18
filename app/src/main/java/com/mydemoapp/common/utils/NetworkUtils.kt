package com.mydemoapp.common.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.google.gson.Gson
import com.mydemoapp.R
import com.mydemoapp.common.BaseActivity
import retrofit2.HttpException


/**
 * Purpose of this Class is to check internet connection of phone and perform actions on user's input
 */
object NetworkUtils {


    /**
     * Checks internet network connection.
     *
     * @param context    Activity context
     * @param message    if want to show connection message to user then true, false otherwise.
     * @param goSettings if want to go action setting for connection then true, otherwise only OK button.
     * @return if network connectivity exists or is in the process of being established, false otherwise.
     */
    fun isOnline(context: Activity?, message: Boolean, goSettings: Boolean): Boolean {
        if (context != null && !context.isFinishing) {
            if (isNetworkOn(context)) {
                return true
            }

            if (message) {
                val dialog = AlertDialog.Builder(context)

                dialog.setTitle(context.getString(R.string.app_name))
                dialog.setCancelable(false)
                dialog.setMessage(context.getString(R.string.alert_no_connection))

                if (goSettings) {
                    dialog.setPositiveButton(context.getString(R.string.settings)) { dialog1, _ ->
                        dialog1.dismiss()
                        context.startActivity(Intent(android.provider.Settings.ACTION_SETTINGS))
                    }

                    dialog.setNegativeButton(context.getString(R.string.cancel)) { dialog12, _ -> dialog12.dismiss() }

                } else {
                    dialog.setNeutralButton(context.getString(R.string.ok)) { dialog13, _ -> dialog13.dismiss() }
                }
                dialog.show()
                return false
            }
        }
        return false
    }

    /**
     * Checks the Network availability.
     *
     * @return true if internet available, false otherwise
     */
    fun isNetworkOn(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo

        return activeNetwork != null && activeNetwork.isConnected
    }

    /**
     * Show common api error
     */
    fun showApiError(context: Context, message: String) {
        DialogUtils.showSnackBar(context as BaseActivity, if (message.isNotEmpty()) message else context.getString(R.string.alert_message_error))
    }

//    /**
//     * Handle network errors like PageNotFound, ServerError etc.
//     * @param e exception
//     * @return error message to display
//     */
//    fun handleErrorResponse(e: Throwable): String? {
//        if (e is HttpException) {
//            val errorBody = e.response().errorBody()
//            if (errorBody != null) {
//                try {
//                    val errMsg = errorBody.string()
//                    val modelError = Gson().fromJson<ModelError>(errMsg, ModelError::class.java)
//                    return modelError.status!!.msg
//                } catch (ignored: Exception) {
//                }
//
//            }
//        }
//        return "Something went wrong, Try again"
//    }
}

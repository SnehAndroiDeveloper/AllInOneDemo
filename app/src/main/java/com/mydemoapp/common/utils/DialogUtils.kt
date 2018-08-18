package com.mydemoapp.common.utils

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.support.design.widget.Snackbar
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import com.mydemoapp.R
import com.mydemoapp.common.utils.Utils
import kotlinx.android.synthetic.main.custom_common_dialog.*


/**
 * Purpose of this Class is to display different dialog in application.
 */
object DialogUtils {

    private var shipConfirmationDialog: Dialog? = null

    private var addNoteDialog: Dialog? = null

    private var progressDialog: Dialog? = null

    /**
     * Displays alert dialog to show common messages.
     *
     * @param message Message to be shown in the Dialog displayed
     * @param context Context of the Application, where the Dialog needs to be displayed
     */
    fun displayDialog(context: Context, message: String) {

        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.setTitle(context.getString(R.string.app_name))
        alertDialog.setCancelable(false)

        alertDialog.setMessage(message)
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, context.getString(android.R.string.ok)) { dialog, _ -> dialog.dismiss() }

        if (!(context as Activity).isFinishing) {
            alertDialog.show()
        }
    }

    /**
     * Displays alert dialog to show common messages.
     *
     * @param message Message to be shown in the Dialog displayed
     * @param context Context of the Application, where the Dialog needs to be displayed
     */
    fun displayDialogSettings(context: Context, message: String) {

        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.setTitle(context.getString(R.string.app_name))
        alertDialog.setCancelable(false)

        alertDialog.setMessage(message)
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, context.getString(R.string.cancel)) { dialog, _ ->
            context.startActivity(Intent(android.provider.Settings.ACTION_SECURITY_SETTINGS))
            dialog.dismiss()
        }
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, context.getString(R.string.settings)) { dialog, _ -> dialog.dismiss() }

        if (!(context as Activity).isFinishing) {
            alertDialog.show()
        }
    }

    /**
     * Custom view as app theme common dialog
     *
     * @param mContext              Activity context
     * @param title                 Title text
     * @param msg                   Message text
     * @param strPositiveBtn        Positive button text
     * @param strNegativeBtn        Negative button text
     * @param isTitle               Show title if true otherwise not
     * @param isNegativeBtn         Show negative if true otherwise not
     * @param mDialogButtonListener Listener for positive and negative buttons of dialog
     */
    fun displayDialog(mContext: Context?, title: String, msg: String, strPositiveBtn: String,
                      strNegativeBtn: String, isTitle: Boolean, isNegativeBtn: Boolean, mDialogButtonListener: DialogButtonListener) {
        var msg = msg
        if (mContext != null && !(mContext as Activity).isFinishing) {
            if (TextUtils.isEmpty(msg)) {
                msg = mContext.getString(R.string.alert_some_error)
            }
            val mDialog = Dialog(mContext, R.style.StyleCommonDialog)
            if (mDialog.window != null) {
                val windowLayoutManger = mDialog.window!!.attributes
                windowLayoutManger.gravity = Gravity.CENTER
                windowLayoutManger.width = WindowManager.LayoutParams.WRAP_CONTENT
                windowLayoutManger.height = WindowManager.LayoutParams.WRAP_CONTENT
                mDialog.window!!.attributes = windowLayoutManger

                mDialog.setCancelable(false)
                mDialog.setCanceledOnTouchOutside(false)
            }
            mDialog.setContentView(R.layout.custom_common_dialog)

            mDialog.tvTitle.text = title.trim { it <= ' ' }
            mDialog.tvMsg.text = msg.trim { it <= ' ' }

            if (isTitle) {
                mDialog.tvTitle.visibility = View.VISIBLE
            } else {
                mDialog.tvTitle.visibility = View.GONE
            }

            mDialog.btnPositive.setOnClickListener {
                Utils.hideSoftKeyBoard(mContext, it)
                mDialog.dismiss()
                mDialogButtonListener.onPositiveButtonClicked()
            }

            if (!TextUtils.isEmpty(strPositiveBtn.trim { it <= ' ' })) {
                mDialog.btnPositive.text = strPositiveBtn.trim { it <= ' ' }
            }

            if (isNegativeBtn) {
                mDialog.btnNegative.visibility = View.VISIBLE

                mDialog.btnNegative.setOnClickListener {
                    Utils.hideSoftKeyBoard(mContext, it)
                    mDialog.dismiss()
                    mDialogButtonListener.onNegativeButtonClicked()
                }
                if (!TextUtils.isEmpty(strNegativeBtn.trim { it <= ' ' })) {
                    mDialog.btnNegative.text = strNegativeBtn.trim { it <= ' ' }
                }
            } else {
                mDialog.btnNegative.visibility = View.GONE
            }
            mDialog.show()
        }
    }

    interface DialogButtonListener {
        fun onPositiveButtonClicked()

        fun onNegativeButtonClicked()
    }

    /**
     * Displays toast message
     *
     * @param mContext requires to create Toast
     */
    fun showToast(mContext: Context, message: String) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * Displays snackbar with message
     *
     * @param mContext requires to create Toast
     */
    fun showSnackBar(mContext: Activity?, message: String) {
        mContext?.let {
            val snackBar = Snackbar.make(it.findViewById<View>(android.R.id.content), message, Snackbar.LENGTH_LONG)
            val view = snackBar.view
            val textView = view.findViewById(android.support.design.R.id.snackbar_text) as TextView

//            when (mContext) {
//                is HomeActivity -> {
//                    view.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimary))
//                    textView.setTextColor(ContextCompat.getColor(mContext, R.color.colorWhite))
//                }
//                else -> {
//                    view.setBackgroundColor(ContextCompat.getColor(it, R.color.colorWhite))
//                    textView.setTextColor(ContextCompat.getColor(it, R.color.colorPrimary))
//                }
//            }

            snackBar.show()
        }
    }
}
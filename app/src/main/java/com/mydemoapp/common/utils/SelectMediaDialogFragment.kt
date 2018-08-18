//package com.mydemoapp.common.utils
//
//import android.Manifest
//import android.app.Activity
//import android.content.Context
//import android.content.Intent
//import android.graphics.*
//import android.media.ExifInterface
//import android.net.Uri
//import android.os.Bundle
//import android.provider.MediaStore
//import android.support.v4.app.DialogFragment
//import android.text.TextUtils
//import android.util.Log
//import android.view.*
//import com.mydemoapp.R
//import com.mydemoapp.MyDemoApp
//import kotlinx.android.synthetic.main.dialog_media.view.*
//import java.io.File
//import java.io.FileNotFoundException
//import java.io.FileOutputStream
//import java.io.IOException
//
//class SelectMediaDialogFragment : DialogFragment(), PermissionUtil.OnPermissionListener, View.OnClickListener {
//    private var permissionUtil: PermissionUtil? = null
//
//    private val REQUEST_CAPTURE_IMAGE = 2001
//    private val REQUEST_PICK_IMAGE = 2003
//
//    private val extraStateCameraUri = "extraStateCameraUri"
//    private val extraStateImageuri = "extraStateImageuri"
//
//    private var imgPath: String? = ""
//    private var cameraUri: String? = null
//
//    private var mediaSelectedListener: MediaSelectedListener? = null
//
//    var parentPosition = -1
//    var position = -1
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putString(extraStateCameraUri, cameraUri)
//        outState.putString(extraStateImageuri, imgPath)
//        outState.getInt(Constants.BUNDLE_ITEM_POSITION, parentPosition)
//        outState.getInt(Constants.BUNDLE_DEFECT_POSITION, position)
//    }
//
//    override fun onViewStateRestored(savedInstanceState: Bundle?) {
//        super.onViewStateRestored(savedInstanceState)
//        if (savedInstanceState != null) {
//            cameraUri = savedInstanceState.getString(extraStateCameraUri)
//            imgPath = savedInstanceState.getString(extraStateImageuri)
//            parentPosition = savedInstanceState.getInt(Constants.BUNDLE_ITEM_POSITION)
//            position = savedInstanceState.getInt(Constants.BUNDLE_DEFECT_POSITION)
//        }
//    }
//
//    override fun getTheme(): Int {
//        return R.style.StyleCommonDialog
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        if (dialog.window != null) {
//            val wlmp = dialog.window.attributes
//            wlmp.gravity = Gravity.CENTER
//            wlmp.width = WindowManager.LayoutParams.WRAP_CONTENT
//            wlmp.height = WindowManager.LayoutParams.WRAP_CONTENT
//            dialog.window.attributes = wlmp
//            dialog.setCancelable(true)
//            dialog.setCanceledOnTouchOutside(true)
//        }
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val view = inflater.inflate(R.layout.dialog_media, container, false)
//
//        view.ivCamera.setOnClickListener(this)
//        view.ivGallery.setOnClickListener(this)
//        view.btnClose.setOnClickListener(this)
//
//        return view
//    }
//
//    override fun onClick(view: View) {
//        Utils.hideSoftKeyBoard(MyDemoApp.instance, view)
//
//        when (view.id) {
//            R.id.ivCamera -> openCameraForImage()
//
//            R.id.ivGallery -> selectImageFromGallery()
//
//            R.id.btnClose -> dismiss()
//        }
//    }
//
//    fun setMediaSelectedListener(mediaSelectedListener: MediaSelectedListener) {
//        this.mediaSelectedListener = mediaSelectedListener
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (resultCode == Activity.RESULT_OK)
//            if (requestCode == REQUEST_CAPTURE_IMAGE) {
//                try {
//                    if (!TextUtils.isEmpty(cameraUri)) {
//                        imgPath?.let { context?.let { it1 -> compressImage(it1, it, true) }?.let { it2 -> mediaSelectedListener?.setImage(it2, parentPosition, position) } }
//                        dismiss()
//                    } else {
//                        dismiss()
//                    }
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//
//            } else if (requestCode == REQUEST_PICK_IMAGE) {
//                try {
//                    val uri = data?.data
//                    val imgPath = activity?.let { uri?.let { it1 -> GetFilePath.getPath(it, it1) } }
//
//                    if (imgPath != null) {
//                        if (!TextUtils.isEmpty(imgPath)) {
//                            if (imgPath.startsWith("http")) {
//                                dismiss()
//
//                            } else {
//                                this.imgPath = imgPath
//                                if (uri != null) {
//                                    context?.let { compressImage(it, uri.toString(), false) }?.let { mediaSelectedListener?.setImage(it, parentPosition, position) }
//                                }
//                                dismiss()
//                            }
//                        }
//                    }
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//
//            }
//    }
//
//    private fun requestAppPermissions(requestedPermissions: Array<String>, stringId: Int, requestCode: Int,
//                                      permissionListener: PermissionUtil.OnPermissionListener) {
//        permissionUtil = PermissionUtil(permissionListener)
//        permissionUtil!!.requestPermissionsFragment(this@SelectMediaDialogFragment, requestedPermissions, requestCode)
//    }
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
//        if (permissionUtil != null) {
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//            activity?.let { permissionUtil!!.handleRequestPermissionsResult(requestCode, grantResults) }
//            permissionUtil = null
//        }
//    }
//
//    /**
//     * code of take a picture from the Camera app
//     */
//    private fun openCameraForImage() {
//        requestStoragePermission(REQUEST_CAPTURE_IMAGE)
//    }
//
//    /**
//     * Method to Select an Image from the Phone's Gallery
//     */
//    private fun selectImageFromGallery() {
//        requestStoragePermission(REQUEST_PICK_IMAGE)
//    }
//
//    /**
//     * Common storage request
//     */
//    private fun requestStoragePermission(requestCode: Int) {
//        val perms = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
//        if (activity != null) {
//            requestAppPermissions(perms, R.string.storage_permission_msg, requestCode, this)
//        } else {
//            dismiss()
//        }
//    }
//
//    override fun onPermissionsGranted(requestCode: Int) {
//        if (requestCode == REQUEST_CAPTURE_IMAGE) {
//            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            try {
//                val mFile = activity?.let { Utils.createPhotoFile(it) }
//                mFile?.let {
//                    imgPath = it.absolutePath
//                    cameraUri = Utils.getUri(activity, it).toString()
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Utils.getUri(activity, it))
//                }
//                intent.putExtra("return-data", true)
//                startActivityForResult(intent, REQUEST_CAPTURE_IMAGE)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//
//        } else if (requestCode == REQUEST_PICK_IMAGE) {
//            val openGalleryIntent = Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//            openGalleryIntent.addCategory(Intent.CATEGORY_OPENABLE)
//            openGalleryIntent.type = "image/*"
//            openGalleryIntent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
//            startActivityForResult(openGalleryIntent, REQUEST_PICK_IMAGE)
//        }
//    }
//
//    override fun onPermissionDenied(requestCode: Int) {
//    }
//
//    private fun compressImage(mContext: Context, imageUri: String, isCamera: Boolean): String {
//
//        val filePath: String? = if ((!isCamera)) {
//            GetFilePath.getPath(mContext, Uri.parse(imageUri))
//        } else {
//            imageUri
//        }
//        var scaledBitmap: Bitmap? = null
//
//        val options = BitmapFactory.Options()
//
//        //      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//        //      you try the use the bitmap here, you will get null.
//        options.inJustDecodeBounds = true
//        var bmp = BitmapFactory.decodeFile(filePath, options)
//
//        var actualHeight = options.outHeight
//        var actualWidth = options.outWidth
//
//        //      max Height and width values of the compressed image is taken as 816x612
//
//        val maxHeight = 816.0f
//        val maxWidth = 612.0f
//        var imgRatio = (actualWidth / actualHeight).toFloat()
//        val maxRatio = maxWidth / maxHeight
//
//        //      width and height values are set maintaining the aspect ratio of the image
//
//        if (actualHeight > maxHeight || actualWidth > maxWidth) {
//            when {
//                imgRatio < maxRatio -> {
//                    imgRatio = maxHeight / actualHeight
//                    actualWidth = (imgRatio * actualWidth).toInt()
//                    actualHeight = maxHeight.toInt()
//                }
//                imgRatio > maxRatio -> {
//                    imgRatio = maxWidth / actualWidth
//                    actualHeight = (imgRatio * actualHeight).toInt()
//                    actualWidth = maxWidth.toInt()
//                }
//                else -> {
//                    actualHeight = maxHeight.toInt()
//                    actualWidth = maxWidth.toInt()
//
//                }
//            }
//        }
//
//        //      setting inSampleSize value allows to load a scaled down version of the original image
//
//        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight)
//
//        //      inJustDecodeBounds set to false to load the actual bitmap
//        options.inJustDecodeBounds = false
//
//        //      this options allow android to claim the bitmap memory if it runs low on memory
//        options.inPurgeable = true
//        options.inInputShareable = true
//        options.inTempStorage = ByteArray(16 * 1024)
//
//        try {
//            //          load the bitmap from its path
//            bmp = BitmapFactory.decodeFile(filePath, options)
//        } catch (exception: OutOfMemoryError) {
//            exception.printStackTrace()
//
//        }
//
//        try {
//            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888)
//        } catch (exception: OutOfMemoryError) {
//            exception.printStackTrace()
//        }
//
//        val ratioX = actualWidth / options.outWidth.toFloat()
//        val ratioY = actualHeight / options.outHeight.toFloat()
//        val middleX = actualWidth / 2.0f
//        val middleY = actualHeight / 2.0f
//
//        val scaleMatrix = Matrix()
//        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY)
//
//        val canvas = Canvas(scaledBitmap!!)
//        canvas.matrix = scaleMatrix
//        canvas.drawBitmap(bmp, middleX - bmp.width / 2, middleY - bmp.height / 2, Paint(Paint.FILTER_BITMAP_FLAG))
//
//        //      check the rotation of the image and display it properly
//        val exif: ExifInterface
//        try {
//            exif = ExifInterface(filePath)
//
//            val orientation = exif.getAttributeInt(
//                    ExifInterface.TAG_ORIENTATION, 0)
//            Log.d("EXIF", "Exif: $orientation")
//            val matrix = Matrix()
//            when (orientation) {
//                6 -> {
//                    matrix.postRotate(90f)
//                    Log.d("EXIF", "Exif: $orientation")
//                }
//                3 -> {
//                    matrix.postRotate(180f)
//                    Log.d("EXIF", "Exif: $orientation")
//                }
//                8 -> {
//                    matrix.postRotate(270f)
//                    Log.d("EXIF", "Exif: $orientation")
//                }
//            }
//            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
//                    scaledBitmap.width, scaledBitmap.height, matrix,
//                    true)
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//
//        val out: FileOutputStream?
//        val filename = getFilename(mContext)
//        try {
//            out = FileOutputStream(filename)
//
//            //          write the compressed bitmap at the destination specified by filename.
//            scaledBitmap!!.compress(Bitmap.CompressFormat.JPEG, 80, out)
//
//        } catch (e: FileNotFoundException) {
//            e.printStackTrace()
//        }
//
//        return filename
//
//    }
//
//    private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
//        val height = options.outHeight
//        val width = options.outWidth
//        var inSampleSize = 1
//
//        if (height > reqHeight || width > reqWidth) {
//            val heightRatio = Math.round(height.toFloat() / reqHeight.toFloat())
//            val widthRatio = Math.round(width.toFloat() / reqWidth.toFloat())
//            inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio
//        }
//        val totalPixels = (width * height).toFloat()
//        val totalReqPixelsCap = (reqWidth * reqHeight * 2).toFloat()
//        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
//            inSampleSize++
//        }
//
//        return inSampleSize
//    }
//
//    private fun getFilename(mContext: Context): String {
//        val file = File(Utils.CACHE_DIRECTORY)
//        if (!file.exists()) {
//            file.mkdirs()
//        }
//        return file.absolutePath + mContext.getString(R.string.app_name) + System.currentTimeMillis() + ".png"
//    }
//}
//
//interface MediaSelectedListener {
//    fun setImage(imagePath: String, parentPosition: Int, position: Int)
//}
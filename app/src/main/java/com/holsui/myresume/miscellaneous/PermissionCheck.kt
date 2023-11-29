package com.holsui.myresume.miscellaneous

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


fun checkPermissions(context: Context): Boolean {
    // on below line we are creating a variable for both of our permissions.

    // on below line we are creating a variable for writing to external storage permission
    var writeStoragePermission = ContextCompat.checkSelfPermission(
        context,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    // on below line we are creating a variable for
    // reading external storage permission
    var readStoragePermission = ContextCompat.checkSelfPermission(
        context,
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    )

    var readImagePermission = ContextCompat.checkSelfPermission(
        context,
        android.Manifest.permission.READ_MEDIA_IMAGES
    )

    // on below line we are returning true if both the
    // permissions are granted anf returning false if permissions are not granted.
    return writeStoragePermission == PackageManager.PERMISSION_GRANTED && readStoragePermission == PackageManager.PERMISSION_GRANTED
}

// on below line we are creating a function to request permission.
fun requestPermission(activity: Activity) {

    // on below line we are requesting read and write to
    // storage permission for our application.
    ActivityCompat.requestPermissions(
        activity,
        arrayOf(
            android.Manifest.permission.READ_MEDIA_IMAGES,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        ), 101
    )
}

package com.holsui.myresume

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.PixelCopy
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.core.graphics.scale
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {

    private val viewModel = ResumeViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ResumeScreen(
                    snapshotState = viewModel.snapshotState.collectAsState(),
                    onSnapshotTaken = { rect, bitmap ->
                        PixelCopy.request(window, rect, bitmap, {
                            val imageFile = prepareBitmapImage(bitmap)
                            generatePDF(this, bitmap)
                        }, Handler(Looper.getMainLooper()))

                    },
                    resumeClickListener = object : ResumeClickListener {
                        override fun onGeneratePDFButtonClick() {
                            Log.d(TAG, "onGeneratePDFButtonClick: 2")
                            viewModel.takeSnapshot()
                        }
                    }) // TODO: Replace to Real value
            }
        }
    }

    private fun prepareBitmapImage(bitmap: Bitmap): File {
        val fileExt = "jpg"
        val compressFormat = Bitmap.CompressFormat.JPEG
        val snapshot = this.createTempImageFile("snapshot", fileExt).apply {
            bitmap.save(this, compressFormat, 60)
        }
        return snapshot
    }
}


fun Context.createTempImageFile(prefix: String = "tmp", ext: String = "jpg"): File {
    val timeStamp: String = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(Date())
    return File.createTempFile(
        "${prefix}_${timeStamp}_",
        ".$ext",
        tempImageDir()
    )
}

fun Context.tempImageDir() = File(externalCacheDir ?: cacheDir, ".temp_images").apply {
    if (!exists()) mkdirs()
}


fun Bitmap.adjust(newWidth: Int): Bitmap {
    val originWidth = width
    val originHeight = height

    return if (originWidth > newWidth) {
        val ratio = originHeight / originWidth.toFloat()
        val newHeight = (newWidth * ratio).roundToInt()
        scale(newWidth, newHeight)
    } else {
        this
    }
}

fun Bitmap.save(
    file: File,
    format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
    quality: Int = 60
) {
    var os: OutputStream? = null

    try {
        file.createNewFile()
        os = FileOutputStream(file)

        compress(format, quality, os)
    } catch (ignored: Exception) {
    } finally {
        os?.close()
    }
}
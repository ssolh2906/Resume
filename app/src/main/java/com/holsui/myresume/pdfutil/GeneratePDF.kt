package com.holsui.myresume.pdfutil

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.util.Log
import android.util.TypedValue
import android.widget.Toast
import com.holsui.myresume.TextInfo
import java.io.File
import java.io.FileOutputStream


// on below line we are creating a generate PDF
// method which is use to generate our PDF file.
fun generatePDF(
    context: Context,
    pageSize: Rect,
    bitmap: Bitmap,
    textInfoMap: Map<String, TextInfo>,
    letterOffset: Rect? = null // TODO: replace to real value
) {

    val pageHeight = pageSize.height()
    val pageWidth = pageSize.width()

    val pdfDocument = PdfDocument()

    var paint: Paint = Paint()

    val myPageInfo: PdfDocument.PageInfo? =
        PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create()

    val myPage: PdfDocument.Page = pdfDocument.startPage(myPageInfo)

    val canvas: Canvas = myPage.canvas


    canvas.drawBitmap(bitmap, 0F, 0F, paint)

    for (textInfo in textInfoMap.values) {
        val title = Paint()
        title.textSize = spToPx(context, textInfo.fontSize.toFloat())

        canvas.drawText(
            textInfo.text,
            textInfo.x,
            textInfo.y,
            title
        )

    }
    pdfDocument.finishPage(myPage)

    val directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
    val file: File = File(directory, "Resume.pdf")

    try {
        pdfDocument.writeTo(FileOutputStream(file))

        Toast.makeText(context, "PDF file generated..", Toast.LENGTH_SHORT).show()
    } catch (e: Exception) {
        e.printStackTrace()
        Toast.makeText(context, "Fail to generate PDF file..", Toast.LENGTH_SHORT)
            .show()
    }

    pdfDocument.close()
}

fun spToPx(context: Context, sp: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        sp,
        context.resources.displayMetrics
    )
}
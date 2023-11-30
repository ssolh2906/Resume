package com.holsui.myresume.pdfutil

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.widget.Toast
import com.holsui.myresume.data.TextInfo
import com.holsui.myresume.miscellaneous.spToPx
import java.io.File
import java.io.FileOutputStream

fun generatePDF(
    context: Context,
    pageSize: Rect,
    bitmap: Bitmap,
    textInfoMap: Map<String, TextInfo>,
) {
    val pageHeight = pageSize.height()
    val pageWidth = pageSize.width()

    val pdfDocument = PdfDocument()

    val paint: Paint = Paint()

    val myPageInfo: PdfDocument.PageInfo? =
        PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create()

    val myPage: PdfDocument.Page = pdfDocument.startPage(myPageInfo)

    val canvas: Canvas = myPage.canvas

    canvas.drawBitmap(bitmap, 0F, 0F, paint)

    for (textInfo in textInfoMap.values) {
        val title = Paint()
        title.textAlign = Paint.Align.CENTER
        title.textSize = spToPx(context, textInfo.fontSize.toFloat())
        title.isFakeBoldText = textInfo.isBold

        canvas.drawText(
            textInfo.text,
            textInfo.x,
            textInfo.y + (2.12f * textInfo.fontSize - 72.4f),
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

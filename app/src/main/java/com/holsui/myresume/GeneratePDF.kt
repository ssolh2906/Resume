package com.holsui.myresume

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.util.Log
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream


// on below line we are creating a generate PDF
// method which is use to generate our PDF file.
fun generatePDF(context: Context, rect: Rect, bitmap: Bitmap) {

    var pageHeight = rect.height()
    var pageWidth =rect.width()


    var pdfDocument = PdfDocument()

    var paint: Paint = Paint()
    var title: Paint = Paint()

    var myPageInfo: PdfDocument.PageInfo? =
        PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create()

    var myPage: PdfDocument.Page = pdfDocument.startPage(myPageInfo)

    var canvas: Canvas = myPage.canvas

    canvas.drawBitmap(bitmap, 0F, 0F, paint)

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
package com.holsui.myresume.pdfutil

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.holsui.myresume.miscellaneous.checkPermissions
import com.holsui.myresume.miscellaneous.requestPermission
import java.io.File
import java.io.FileOutputStream

// on below line we are creating a
// pdf generator composable function for ui.
@Composable
fun pdfGenerator() {

    // on below line we are creating a variable for
    // our context and activity and initializing it.
    val context = LocalContext.current
    val activity = (LocalContext.current as? Activity)

    // on below line we are checking permission
    if (checkPermissions(context)) {
        // if permission is granted we are displaying a toast message.
        Toast.makeText(context, "Permissions Granted..", Toast.LENGTH_SHORT).show()
    } else {
        // if the permission is not granted
        // we are calling request permission method.
        requestPermission(activity!!)
    }

    // on below line we are creating a column for our ui.
    Column(
        // in this column we are adding a modifier for our
        // column and specifying max width, height and size.
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .fillMaxSize()

            // on below line we are adding padding
            // from all sides to our column.
            .padding(6.dp),

        // on below line we are adding vertical
        // arrangement for our column as center
        verticalArrangement = Arrangement.Center,

        // on below line we are adding
        // horizontal alignment for our column.
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // on below line we are creating a simple text as a PDF Generator.
        Text(
            // on below line we are setting text to our text
            text = "PDF Generator",

            // on below line we are
            // setting color for our text
            color = Color.Green,

            // on below line we are setting
            // font weight for our text
            fontWeight = FontWeight.Bold,

            // on below line we are setting
            // alignment for our text as center.
            textAlign = TextAlign.Center,

            // on below line we are
            // setting font size for our text
            fontSize = 20.sp
        )

        // on below line we are adding
        // spacer between text and a button.
        Spacer(modifier = Modifier.height(60.dp))

        // on the below line we are creating a button.
        Button(
            // on below line we are adding a modifier
            // to it and specifying max width to it.
            modifier = Modifier
                .fillMaxWidth()

                // on below line we are adding
                // padding for our button.
                .padding(20.dp),

            // on below line we are adding
            // on click for our button.
            onClick = {

                // inside on click we are calling our
                // generate PDF method to generate our PDF
//                generatePDF(context)
            }) {

            // on the below line we are displaying a text for our button.
            Text(modifier = Modifier.padding(6.dp), text = "Generate PDF")
        }
    }

}

// on below line we are creating a generate PDF
// method which is use to generate our PDF file.
private fun generatePDF2(context: Context, rect: Rect, bitmap: Bitmap) {
    // declaring width and height
    // for our PDF file.
    var pageHeight = rect.height()
    var pageWidth =rect.width()

    // creating a bitmap variable
    // for storing our images


    // creating an object variable
    // for our PDF document.
    var pdfDocument: PdfDocument = PdfDocument()

    // two variables for paint "paint" is used
    // for drawing shapes and we will use "title"
    // for adding text in our PDF file.
    var paint: Paint = Paint()
    var title: Paint = Paint()


    // we are adding page info to our PDF file
    // in which we will be passing our pageWidth,
    // pageHeight and number of pages and after that
    // we are calling it to create our PDF.
    var myPageInfo: PdfDocument.PageInfo? =
        PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create()

    // below line is used for setting
    // start page for our PDF file.
    var myPage: PdfDocument.Page = pdfDocument.startPage(myPageInfo)

    // creating a variable for canvas
    // from our page of PDF.
    var canvas: Canvas = myPage.canvas

//    // below line is used to draw our image on our PDF file.
//    // the first parameter of our drawbitmap method is
//    // our bitmap
//    // second parameter is position from left
//    // third parameter is position from top and last
//    // one is our variable for paint.
    canvas.drawBitmap(bitmap, 0F, 0F, paint)
//
//    // below line is used for adding typeface for
//    // our text which we will be adding in our PDF file.
//    title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL))
//
//    // below line is used for setting text size
//    // which we will be displaying in our PDF file.
//    title.textSize = 15F
//
//    // below line is sued for setting color
//    // of our text inside our PDF file.
//    title.setColor(ContextCompat.getColor(context, R.color.purple_200))
//
//    // below line is used to draw text in our PDF file.
//    // the first parameter is our text, second parameter
//    // is position from start, third parameter is position from top
//    // and then we are passing our variable of paint which is title.
//    canvas.drawText("A portal for IT professionals.", 209F, 100F, title)
//    canvas.drawText("Geeks for Geeks", 209F, 80F, title)
//    title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL))
//    title.setColor(ContextCompat.getColor(context, R.color.purple_200))
//    title.textSize = 15F
//
//    // below line is used for setting
//    // our text to center of PDF.
//    title.textAlign = Paint.Align.CENTER
//    canvas.drawText("This is sample document which we have created.", 396F, 560F, title)

    // after adding all attributes to our
    // PDF file we will be finishing our page.
    pdfDocument.finishPage(myPage)

    // below line is used to set the name of
    // our PDF file and its path.
    val directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
    val file: File = File(directory, "GFG.pdf")

    try {
        // after creating a file name we will
        // write our PDF file to that location.
        pdfDocument.writeTo(FileOutputStream(file))

        // on below line we are displaying a toast message as PDF file generated..
        Toast.makeText(context, "PDF file generated..", Toast.LENGTH_SHORT).show()
    } catch (e: Exception) {
        // below line is used
        // to handle error
        e.printStackTrace()

        // on below line we are displaying a toast message as fail to generate PDF
        Toast.makeText(context, "Fail to generate PDF file..", Toast.LENGTH_SHORT)
            .show()
    }
    // after storing our pdf to that
    // location we are closing our PDF file.
    pdfDocument.close()
}

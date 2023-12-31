package com.holsui.myresume

import android.util.Log
import androidx.lifecycle.ViewModel
import com.holsui.myresume.data.SnapshotState
import com.holsui.myresume.data.TextInfo
import kotlinx.coroutines.flow.MutableStateFlow

class ResumeViewModel : ViewModel() {


    val textInfoTable = mutableMapOf<String, TextInfo>()


    var snapshotState = MutableStateFlow(SnapshotState.STATE_IDLE)

    fun addTextInfo(tag: String, textInfo: TextInfo) {
        textInfoTable[tag] = textInfo
        Log.d("TF_PDF", "addTextInfo: $textInfo")
    }

    fun snapshotReady() {
        snapshotState.value = SnapshotState.STATE_READY
    }

    fun takeSnapshot() {
        snapshotState.value = SnapshotState.STATE_CAPTURE
    }
}
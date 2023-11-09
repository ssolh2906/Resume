package com.holsui.myresume

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ResumeViewModel : ViewModel() {

    companion object {
        val textInfoTable = mutableMapOf<String, TextInfo>()
    }

    var snapshotState = MutableStateFlow(SnapshotState.STATE_IDLE)

    fun addTextInfo(tag: String, textInfo: TextInfo) {
        textInfoTable[tag] = textInfo
    }

    fun snapshotReady() {
        snapshotState.value = SnapshotState.STATE_READY
    }

    fun takeSnapshot() {
        snapshotState.value = SnapshotState.STATE_CAPTURE
    }
}
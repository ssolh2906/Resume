package com.holsui.myresume

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ResumeViewModel: ViewModel() {
    var snapshotState = MutableStateFlow(SnapshotState.STATE_IDLE)


    fun snapshotReady() {
        snapshotState.value = SnapshotState.STATE_READY
    }
    fun takeSnapshot() {
        if (snapshotState.value == SnapshotState.STATE_READY) {
            snapshotState.value = SnapshotState.STATE_CAPTURE
        }
    }
}
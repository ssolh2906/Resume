package com.holsui.myresume

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ResumeViewModel: ViewModel() {
    var snapshotState = MutableStateFlow(SnapshotState.STATE_IDLE)

    fun takeSnapshot() {
        Log.d(TAG, "takeSnapshot: VIEWMODEL")
        if (snapshotState.value == SnapshotState.STATE_IDLE) {
            snapshotState.value = SnapshotState.STATE_CAPTURE
        }
    }
}
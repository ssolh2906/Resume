package com.holsui.myresume

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ResumeViewModel: ViewModel() {
    var snapshotState = MutableStateFlow(SnapshotState.STATE_IDLE)

    fun takeSnapshot() {
        if (snapshotState.value == SnapshotState.STATE_IDLE) {
            snapshotState.value = SnapshotState.STATE_CAPTURE
        }
    }
}
package io.github.seoj17.presentaion.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <T, K, R> combineWith(
    liveData1: LiveData<T>,
    liveData2: LiveData<K>,
    block: (T?, K?) -> R,
): LiveData<R> {
    val result = MediatorLiveData<R>()
    result.addSource(liveData1) {
        result.value = block(liveData1.value, liveData2.value)
    }

    result.addSource(liveData2) {
        result.value = block(liveData1.value, liveData2.value)
    }
    return result
}

fun <T, K, S, R> combineWith(
    liveData1: LiveData<T>,
    liveData2: LiveData<K>,
    liveData3: LiveData<S>,
    block: (T?, K?, S?) -> R,
): LiveData<R> {
    val result = MediatorLiveData<R>()
    result.addSource(liveData1) {
        result.value = block(liveData1.value, liveData2.value, liveData3.value)
    }

    result.addSource(liveData2) {
        result.value = block(liveData1.value, liveData2.value, liveData3.value)
    }

    result.addSource(liveData3) {
        result.value = block(liveData1.value, liveData2.value, liveData3.value)
    }

    return result
}

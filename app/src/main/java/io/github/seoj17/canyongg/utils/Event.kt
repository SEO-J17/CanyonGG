package io.github.seoj17.canyongg.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

open class Event<out T>(private val content: T) {
    private var hasBeenHandled = AtomicBoolean(false)

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled.compareAndSet(false, true)) {
            content
        } else {
            null
        }
    }

    fun peekContent(): T = content

}

class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}

fun <T> LiveData<Event<T>>.observeEvent(
    owner: LifecycleOwner,
    onEventUnhandledContent: (T) -> Unit,
) {
    observe(owner, EventObserver(onEventUnhandledContent))
}
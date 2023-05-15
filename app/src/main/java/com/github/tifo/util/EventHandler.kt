package com.github.tifo.util

interface EventHandler<T> {
    fun obtainEvent(event: T)
}

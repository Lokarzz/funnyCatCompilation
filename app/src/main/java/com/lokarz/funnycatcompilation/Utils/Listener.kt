package com.lokarz.funnycatcompilation.Utils

object Listener {

    interface OnItemClick<T> {
        fun onItemSelected(t: T){}
    }

    interface OnItemViewClick<T, V> {
        fun onItemSelected(t: T, v: V)
    }

}

package ru.skillbranch.devintensive.extensions

import android.content.res.Resources

val Int.spToPixels: Int
    get() = (this * Resources.getSystem().displayMetrics.scaledDensity).toInt()

val Int.dpToPixels: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Int.pxToDimensionPixels: Int
    get() = (this / Resources.getSystem().displayMetrics.density.toInt())
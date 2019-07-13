package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import android.opengl.ETC1.getHeight



fun Activity.hideKeyboard() {
    val inputManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.SHOW_FORCED)
}

fun Activity.isKeyboardClosed(): Boolean {
    val r = Rect()
    val rootView = this.window.decorView // this = activity
    rootView.getWindowVisibleDisplayFrame(r)
    val screenHeight = rootView.getRootView().getHeight()
    return !((r.bottom - r.top) < screenHeight)
}

fun Activity.isKeyboardOpen(): Boolean {
    val r = Rect()
    val rootView = this.window.decorView // this = activity
    rootView.getWindowVisibleDisplayFrame(r)
    val screenHeight = rootView.getRootView().getHeight()
    return ((r.bottom - r.top) < screenHeight)
}
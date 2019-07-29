package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlin.math.roundToLong
import android.util.TypedValue
import ru.skillbranch.devintensive.utils.Utils


fun Activity.hideKeyboard() {
    val inputManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.SHOW_FORCED)
}

fun Activity.isKeyboardOpen(): Boolean {
    val r = Rect()
    val rootView = findViewById<View>(android.R.id.content)
    rootView.getWindowVisibleDisplayFrame(r)
    val heightDiff = rootView.height - r.height()
    val marginOfError = Utils.convertDpToPx(this,50)

    return heightDiff > marginOfError
}

fun Activity.isKeyboardClosed(): Boolean {
    return this.isKeyboardOpen().not()
}

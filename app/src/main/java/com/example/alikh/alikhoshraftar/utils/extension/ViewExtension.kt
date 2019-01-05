package com.example.alikh.alikhoshraftar.utils.extension

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.getParentActivity(): AppCompatActivity?{
    var context = this.context
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}

fun androidx.fragment.app.Fragment.hideKeyboard() {
    activity!!.hideKeyboard(view)
}

fun Activity.hideKeyboard() {
    hideKeyboard(if (currentFocus == null) View(this) else currentFocus)
}

fun Context.hideKeyboard(view: View?) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view!!.windowToken, 0)
}
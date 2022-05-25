package vn.vnext.appcommon.utils

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.StateFlow
import kotlin.reflect.KFunction1

fun Context.getColor(id: Int): Int = ContextCompat.getColor(this, id)
fun Context.getDrawable(id: Int): Drawable? = ContextCompat.getDrawable(this, id)

fun <T> Fragment.stateFlowCollect(stateFlow: StateFlow<T>, collection: KFunction1<T, Unit>) {
    viewLifecycleOwner.lifecycleScope.launchWhenCreated {
        stateFlow.collect { state ->
            collection(state)
        }
    }
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}
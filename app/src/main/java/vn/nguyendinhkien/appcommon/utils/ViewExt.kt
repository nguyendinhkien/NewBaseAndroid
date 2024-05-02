package vn.nguyendinhkien.appcommon.utils

import android.view.View

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun onViews(views: List<View>, func: View.() -> Unit) {
    views.map { it.func() }
}

fun hideViews(views: List<View>) {
    onViews(views) { invisible() }
}

fun showViews(views: List<View>) {
    onViews(views) { visible() }
}

fun goneViews(views: List<View>) {
    onViews(views) { gone() }
}

fun conditionalShowViews(views: List<View>, predicate: () -> Boolean) {
    if (predicate()) showViews(views) else hideViews(views)
}
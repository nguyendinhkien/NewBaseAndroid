package vn.nguyendinhkien.appcommon.presentation.base.adapter

import androidx.recyclerview.widget.DiffUtil

class QuickDiffUtils<T>: DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return true;
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return true;
    }
}
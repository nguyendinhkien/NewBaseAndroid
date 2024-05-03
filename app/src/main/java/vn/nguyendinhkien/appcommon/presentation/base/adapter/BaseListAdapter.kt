package vn.nguyendinhkien.appcommon.presentation.base.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseListAdapter<T, VH : BaseListAdapter<T, VH>.BaseViewHolder>(diffUtil: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, VH>(diffUtil) {

    private lateinit var mContext: Context

    val context get() = mContext

    abstract inner class BaseViewHolder(binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun bindView(item: T, position: Int)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bindView(getItem(position), position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        mContext = parent.context
        return onCreateVH(parent, viewType)
    }

    abstract fun onCreateVH(parent: ViewGroup, viewType: Int): VH
}
package vn.nguyendinhkien.appcommon.presentation.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import vn.nguyendinhkien.appcommon.databinding.ItemRecyclerViewDemoBinding
import vn.nguyendinhkien.appcommon.presentation.base.adapter.BaseListAdapter
import vn.nguyendinhkien.appcommon.presentation.base.adapter.QuickDiffUtils
import javax.inject.Inject
import javax.inject.Singleton

//class NoteDiff : DiffUtil.ItemCallback<Note>() {
//    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
//        return true
//    }
//
//    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
//        return true
//    }
//}



@Singleton
class ListViewAdapter @Inject constructor() :
    BaseListAdapter<Note, ListViewAdapter.NoteVH>(QuickDiffUtils()) {

    // onClickListener Interface
    interface OnClickListener {
        fun onClick( item: Note, position: Int,)
    }

    private var onClickListener: OnClickListener? = null

    fun setOnItemClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    inner class NoteVH(
        parent: ViewGroup,
        private val binding: ItemRecyclerViewDemoBinding = ItemRecyclerViewDemoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    ) : BaseListAdapter<Note, NoteVH>.BaseViewHolder(binding) {

        override fun bindView(item: Note, position: Int) {
            binding.textDemo.text = item.name
            binding.itemNote.setOnClickListener {
                onClickListener?.onClick(item, position)
            }
        }
    }

    override fun onCreateVH(parent: ViewGroup, viewType: Int): NoteVH {
        return NoteVH(parent)
    }
}
package vn.nguyendinhkien.appcommon.presentation.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import vn.nguyendinhkien.appcommon.databinding.ItemLayoutBinding
import vn.nguyendinhkien.appcommon.presentation.base.adapter.BaseListAdapter
import vn.nguyendinhkien.appcommon.presentation.base.adapter.QuickDiffUtils
import javax.inject.Inject
import javax.inject.Singleton

data class Note(var name: String? = null)

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
    inner class NoteVH(
        parent: ViewGroup,
        private val binding: ViewBinding = ItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    ) : BaseListAdapter<Note, NoteVH>.BaseViewHolder(binding) {

        override fun bindView(item: Note) {
            //bindView
        }
    }

    override fun onCreateVH(parent: ViewGroup, viewType: Int): NoteVH {
        return NoteVH(parent)
    }
}
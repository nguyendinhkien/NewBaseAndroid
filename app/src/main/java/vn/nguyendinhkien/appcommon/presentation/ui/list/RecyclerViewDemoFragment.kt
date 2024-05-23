package vn.nguyendinhkien.appcommon.presentation.ui.list

import android.os.Bundle
import org.koin.android.ext.android.inject
import vn.nguyendinhkien.appcommon.databinding.FragmentRecyclerViewDemoBinding
import vn.nguyendinhkien.appcommon.presentation.base.ui.BaseFragment

class RecyclerViewDemoFragment() :
    BaseFragment<FragmentRecyclerViewDemoBinding, List<Note>, RecyclerViewDemoViewModel>(
        FragmentRecyclerViewDemoBinding::inflate
    ) {
    override val viewModel: RecyclerViewDemoViewModel by inject()
    private val adapter:ListViewAdapter by inject()

    override fun onViewReady(savedInstanceState: Bundle?) {
        binding.apply {
            listNote.adapter = adapter
        }
        onSuccessState = {data->
            adapter!!.apply {
                submitList(data)
                setOnItemClickListener(object : ListViewAdapter.OnClickListener{
                    override fun onClick(item: Note, position: Int) {
                        println("Clicked $position")
                    }
                })
            }
        }
    }

}
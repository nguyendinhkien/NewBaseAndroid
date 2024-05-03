package vn.nguyendinhkien.appcommon.presentation.ui.list

import android.os.Bundle
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import vn.nguyendinhkien.appcommon.databinding.FragmentRecyclerViewDemoBinding
import vn.nguyendinhkien.appcommon.presentation.base.ui.BaseFragment
import javax.inject.Inject

@AndroidEntryPoint
class RecyclerViewDemoFragment() :
    BaseFragment<FragmentRecyclerViewDemoBinding, List<Note>, RecyclerViewDemoViewModel>(
        FragmentRecyclerViewDemoBinding::inflate
    ) {
    override val viewModel: RecyclerViewDemoViewModel by viewModels()

    var adapter:ListViewAdapter? = null @Inject set

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
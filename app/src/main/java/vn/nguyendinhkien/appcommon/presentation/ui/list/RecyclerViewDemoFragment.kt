package vn.nguyendinhkien.appcommon.presentation.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import vn.nguyendinhkien.appcommon.R
import vn.nguyendinhkien.appcommon.databinding.FragmentRecyclerViewDemoBinding
import vn.nguyendinhkien.appcommon.presentation.base.ui.BaseFragment
import vn.nguyendinhkien.appcommon.presentation.base.ui.BaseViewModel

@AndroidEntryPoint
class RecyclerViewDemoFragment() :
    BaseFragment<FragmentRecyclerViewDemoBinding, List<Note>, RecyclerViewDemoViewModel>(
        FragmentRecyclerViewDemoBinding::inflate
    ) {
    override val viewModel: RecyclerViewDemoViewModel by viewModels()
    override fun onViewReady(savedInstanceState: Bundle?) {
        println("RecyclerViewDemoFragment onViewReady")
        onSuccessState = {state->
            println("onSuccess ${state.size}")
        }
    }

}
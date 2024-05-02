package vn.nguyendinhkien.appcommon.presentation

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import vn.nguyendinhkien.appcommon.databinding.ActivityMainBinding
import vn.nguyendinhkien.appcommon.presentation.base.ui.BaseActivity


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onViewReady(savedInstanceState: Bundle?) {

    }
}
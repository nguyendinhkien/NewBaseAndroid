package vn.vnext.appcommon.presentation.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<B : ViewBinding>(bindingFactory: (LayoutInflater) -> B) :
    AppCompatActivity() {

    protected val binding: B by lazy { bindingFactory(layoutInflater) }

    protected lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        onViewReady(savedInstanceState)
    }

    abstract fun onViewReady(savedInstanceState: Bundle?)
}
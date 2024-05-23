package vn.nguyendinhkien.appcommon.presentation

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import vn.nguyendinhkien.appcommon.R
import vn.nguyendinhkien.appcommon.databinding.ActivityMainBinding
import vn.nguyendinhkien.appcommon.presentation.base.ui.BaseActivity


class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onViewReady(savedInstanceState: Bundle?) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.loginFragment)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            when (destination.id) {
//                R.id.loginFragment, R.id.registrationFragment -> {
//                }
//
//                else -> {
//                    setupActionBarWithNavController(navController, appBarConfiguration)
//                }
//            }
//
//        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
package vn.vnext.appcommon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import vn.vnext.appcommon.databinding.ActivityMainBinding
import vn.vnext.appcommon.domain.model.login.ParamsLogin
import vn.vnext.appcommon.domain.preferences.PrefsHelper
import vn.vnext.appcommon.presentation.base.ui.BaseActivity
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onViewReady(savedInstanceState: Bundle?) {

    }
}
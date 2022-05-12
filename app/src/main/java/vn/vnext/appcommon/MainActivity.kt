package vn.vnext.appcommon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import dagger.hilt.android.AndroidEntryPoint
import vn.vnext.appcommon.domain.preferences.PrefsHelper
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
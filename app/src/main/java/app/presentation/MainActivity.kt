package app.presentation

import android.os.Bundle
import app.R
import app.di.ViewModelFactory
import app.extensions.viewModelProvider
import app.presentation.common.activity.BaseActivity
import app.presentation.common.menu.DrawerMenu
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject lateinit var drawerMenu: DrawerMenu
    @Inject lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainViewModel by viewModelProvider { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        setContentView(R.layout.activity_main)

        drawerMenu.setup(drawer_layout, drawer, toolbar, true)
    }

    override fun onBackPressed() {
        if (drawerMenu.closeDrawerIfNeeded()) {
            super.onBackPressed()
        }
    }
}

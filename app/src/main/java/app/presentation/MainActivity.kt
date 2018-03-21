package app.presentation

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import app.R
import app.di.ViewModelFactory
import app.presentation.common.activity.BaseActivity
import app.presentation.common.menu.DrawerMenu
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject lateinit var drawerMenu: DrawerMenu
    @Inject lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

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

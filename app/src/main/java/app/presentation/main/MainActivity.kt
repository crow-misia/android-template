package app.presentation.main

import android.os.Bundle
import app.R
import app.di.ViewModelFactory
import app.util.ext.viewModelProvider
import app.presentation.NavigationController
import app.presentation.common.activity.BaseActivity
import app.presentation.common.menu.DrawerMenu
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject lateinit var drawerMenu: DrawerMenu
    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var navigationController: NavigationController

    private val viewModel: MainViewModel by viewModelProvider { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        drawerMenu.setup(drawer_layout, drawer, toolbar, true)

        navigationController.navigateToMain()
    }

    override fun onBackPressed() {
        if (drawerMenu.closeDrawerIfNeeded()) {
            super.onBackPressed()
        }
    }
}

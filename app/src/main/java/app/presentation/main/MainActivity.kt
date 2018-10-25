package app.presentation.main

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import app.R
import app.di.ViewModelFactory
import app.presentation.NavigationController
import app.presentation.common.activity.BaseActivity
import app.presentation.common.menu.DrawerMenu
import app.util.ext.viewModelProvider
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject lateinit var drawerMenu: DrawerMenu
    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var navigationController: NavigationController

    private val viewModel: MainViewModel by viewModelProvider { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val drawer: NavigationView = findViewById(R.id.drawer)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        setSupportActionBar(toolbar)

        drawerMenu.setup(drawerLayout, drawer, toolbar, true)

        navigationController.navigateToMain()
    }

    override fun onBackPressed() {
        if (drawerMenu.closeDrawerIfNeeded()) {
            super.onBackPressed()
        }
    }
}
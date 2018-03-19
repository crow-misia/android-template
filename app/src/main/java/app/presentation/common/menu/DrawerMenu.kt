package app.presentation.common.menu

import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import javax.inject.Inject

class DrawerMenu @Inject constructor(
        private val activity: AppCompatActivity
) {
    private lateinit var drawerLayout: DrawerLayout

    fun setup(
            drawerLayout: DrawerLayout,
            navigationView: NavigationView,
            toolbar: Toolbar? = null,
            actionBarDrawerSync: Boolean = false
    ) {
        this.drawerLayout = drawerLayout
        navigationView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            false
        }
    }

    fun closeDrawerIfNeeded(): Boolean {
        return if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers()
            false
        } else {
            true
        }
    }
}

package app.presentation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import app.R
import app.presentation.main.MainFragment
import javax.inject.Inject

class NavigationController @Inject constructor(activity: AppCompatActivity) {
    private val fragmentManager: FragmentManager = activity.supportFragmentManager

    fun navigateToMain() {
        replaceFragment(MainFragment.newInstance())
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = fragmentManager
                .beginTransaction()
                .replace(R.id.content, fragment, null)

        if (fragmentManager.isStateSaved) {
            transaction.commitAllowingStateLoss()
        } else {
            transaction.commit()
        }
    }
}
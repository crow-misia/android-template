package app

import com.chibatching.kotpref.Kotpref
import org.robolectric.RuntimeEnvironment

class TestApp : App() {
    override fun onCreate() {
        super.onCreate()

        Kotpref.init(RuntimeEnvironment.application)
    }

    override fun setupThreeTenABP() = Unit
}
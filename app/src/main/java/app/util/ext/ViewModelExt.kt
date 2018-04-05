package app.util.ext

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

inline fun <reified VM : ViewModel> FragmentActivity.viewModelProvider(crossinline provider: () -> ViewModelProvider.Factory) = lazy {
    ViewModelProviders.of(this, provider.invoke()).get(VM::class.java)
}

inline fun <reified VM : ViewModel> Fragment.viewModelProvider(crossinline provider: () -> ViewModelProvider.Factory) = lazy {
    ViewModelProviders.of(this, provider.invoke()).get(VM::class.java)
}

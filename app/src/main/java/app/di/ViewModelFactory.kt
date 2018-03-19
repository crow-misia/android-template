package app.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
        private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = try {
        @Suppress("UNCHECKED_CAST")
        creators[modelClass]?.run {
            creators.entries.firstOrNull { (key, _) -> modelClass.isAssignableFrom(key) }?.value
        }?.run {
            get() as T
        }
    } catch (e: Exception) {
        throw RuntimeException(e)
    } ?: run {
        throw IllegalArgumentException("unknown model class $modelClass")
    }
}

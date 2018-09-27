package app.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: run {
            creators.entries.firstOrNull { (key, _) -> modelClass.isAssignableFrom(key) }?.value
                    ?: throw IllegalArgumentException("unknown ViewModel class $modelClass")
        }

        @Suppress("UNCHECKED_CAST")
        return try {
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
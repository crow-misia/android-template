package app.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import app.R
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MainFragment @Inject constructor() : DaggerFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_main, container, false)

    companion object {
        fun newInstance() = MainFragment()
    }
}

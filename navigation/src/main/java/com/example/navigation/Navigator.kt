package com.example.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import javax.inject.Inject

class Navigator @Inject constructor(private val fragmentManager: FragmentManager?) {

    fun navigateToFragmentAddBackStack(fragment: Fragment) {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.main_layout, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    fun navigateToFragmentDeleteBackStack(fragment: Fragment) {
        fragmentManager?.popBackStack()
        fragmentManager?.beginTransaction()
            ?.replace(R.id.main_layout, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }
}
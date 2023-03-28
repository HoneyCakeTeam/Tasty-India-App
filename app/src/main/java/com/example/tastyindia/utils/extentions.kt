package com.example.tastyindia.utils

import androidx.fragment.app.Fragment
import com.example.tastyindia.R

/**
 * Created by Aziza Helmy on 3/27/2023.
 */
 fun Fragment.replaceFragment(fragment: Fragment) {
    val fragmentManager = requireActivity().supportFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()
    fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
    fragmentTransaction.commit()
}

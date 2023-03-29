package com.example.tastyindia.utils

import android.widget.ImageButton
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.tastyindia.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Aziza Helmy on 3/27/2023.
 */
fun Fragment.replaceFragment(fragment: Fragment) {
    val fragmentManager = requireActivity().supportFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()
    fragmentTransaction.replace(R.id.fragmentContainerView, fragment).addToBackStack(null)
    fragmentTransaction.commit()
}

fun Fragment.onClickBack() {
    activity?.findViewById<ImageButton>(R.id.button_navDirection)?.let { navigateIcon ->
        navigateIcon.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}

fun Fragment.onClickBackFromNavigation() {
    val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            showAlertDialog()
        }
    }
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    callback.isEnabled = true
}

fun Fragment.showAlertDialog() {
    MaterialAlertDialogBuilder(requireContext())
        .setTitle("Tasty India")
        .setMessage("Confirm Exit Application ?")
        .setPositiveButton(
            "Exit"
        ) { _, _ ->
            activity?.moveTaskToBack(true)
            activity?.finish()
        }
        .setNegativeButton(
            "Stay"
        ) { _, _ ->
            Snackbar.make(requireView(), "Canceled", Snackbar.LENGTH_SHORT)
                .show()
        }.show()
}

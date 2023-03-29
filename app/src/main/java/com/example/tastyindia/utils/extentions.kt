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
    MaterialAlertDialogBuilder(requireContext(), R.style.alertDialogCustomStyle)
        .setTitle(getString(R.string.tasty_india))
        .setMessage(getString(R.string.confirm))
        .setPositiveButton(
            getString(R.string.exit)
        ) { _, _ ->
            activity?.moveTaskToBack(true)
            activity?.finish()
        }
        .setNegativeButton(
            getString(R.string.stay)
        ) { _, _ ->
            Snackbar.make(requireView(), getString(R.string.canceled), Snackbar.LENGTH_SHORT)
                .show()
        }.show()
}

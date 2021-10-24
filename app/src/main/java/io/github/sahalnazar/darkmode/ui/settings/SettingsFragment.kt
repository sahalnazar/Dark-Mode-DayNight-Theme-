package io.github.sahalnazar.darkmode.ui.settings

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import io.github.sahalnazar.darkmode.MainViewModel
import io.github.sahalnazar.darkmode.R
import io.github.sahalnazar.darkmode.databinding.DialogThemeBinding
import io.github.sahalnazar.darkmode.databinding.FragmentSettingsBinding
import io.github.sahalnazar.darkmode.utils.Constants

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private var _binding: FragmentSettingsBinding? = null
    private val viewModel by viewModels<MainViewModel>()
    private var alertDialog: AlertDialog? = null
    private var dialogThemeBinding: DialogThemeBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSettingsBinding.bind(view)
        _binding = binding

        binding.tvChangeTheme.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        dialogThemeBinding = DialogThemeBinding.inflate(layoutInflater)
        alertDialog = MaterialAlertDialogBuilder(requireContext()).apply {
            dialogThemeBinding?.root?.let { setView(it) }
            setCancelable(true)
        }.create()

        when (viewModel.selectedTheme) {
            Constants.DEVICE_THEME -> dialogThemeBinding?.rbDefaultTheme?.isChecked = true
            Constants.DARK_THEME -> dialogThemeBinding?.rbDarkTheme?.isChecked = true
            Constants.LIGHT_THEME -> dialogThemeBinding?.rbLightTheme?.isChecked = true
        }

        dialogThemeBinding?.rbDefaultTheme?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                viewModel.selectedTheme = Constants.DEVICE_THEME
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                alertDialog?.dismiss()
            }
        }

        dialogThemeBinding?.rbDarkTheme?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                viewModel.selectedTheme = Constants.DARK_THEME
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                alertDialog?.dismiss()
            }
        }

        dialogThemeBinding?.rbLightTheme?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                viewModel.selectedTheme = Constants.LIGHT_THEME
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                alertDialog?.dismiss()
            }
        }

        alertDialog?.show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
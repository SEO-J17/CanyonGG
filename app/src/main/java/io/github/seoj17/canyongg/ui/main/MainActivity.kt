package io.github.seoj17.canyongg.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.canyongg.R
import io.github.seoj17.canyongg.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPreferenceTheme()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val host = supportFragmentManager.findFragmentById(binding.container.id) as NavHostFragment
        val navController = host.navController
        binding.bottomNavBar.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, _, arguments  ->
            binding.bottomNavBar.isVisible = arguments?.getBoolean("showBottomNavView",true) == true
        }
    }

    private fun setPreferenceTheme() {
        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(this)
        val theme = sharedPreference.getString(
            getString(R.string.setting_theme_list_key),
            getString(R.string.setting_light_theme_value)
        )
        when (theme) {
            getString(R.string.setting_light_theme_value) -> {
                setTheme(R.style.Theme_Light)
            }
            getString(R.string.setting_dark_theme_value) -> {
                setTheme(R.style.Theme_Dark)
            }
        }
    }
}
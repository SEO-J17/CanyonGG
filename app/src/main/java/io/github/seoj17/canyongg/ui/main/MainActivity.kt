package io.github.seoj17.canyongg.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.canyongg.R
import io.github.seoj17.canyongg.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPreferenceTheme()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val host = supportFragmentManager.findFragmentById(binding.container.id) as NavHostFragment
        navController = host.navController
        binding.bottomNavBar.setupWithNavController(navController)

        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.home, R.id.search_summoner, R.id.setting),
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, _, arguments ->
            binding.bottomNavBar.isVisible =
                arguments?.getBoolean("showBottomNavView", true) == true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        return findNavController(binding.container.id).navigateUp()
    }

    private fun setPreferenceTheme() {
        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(this)
        val theme = sharedPreference.getString(
            getString(R.string.setting_theme_list_key),
            getString(R.string.setting_light_theme_value),
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

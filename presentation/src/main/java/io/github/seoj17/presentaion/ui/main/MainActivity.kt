package io.github.seoj17.presentaion.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.R
import io.github.seoj17.presentaion.databinding.ActivityMainBinding
import io.github.seoj17.presentaion.ui.setting.ThemeState

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        viewModel.themeSetting.observe(this) {
            AppCompatDelegate.setDefaultNightMode(it ?: ThemeState.SYSTEM.mode)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        return findNavController(binding.container.id).navigateUp()
    }
}

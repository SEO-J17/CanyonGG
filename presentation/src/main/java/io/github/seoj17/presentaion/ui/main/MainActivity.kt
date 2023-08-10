package io.github.seoj17.presentaion.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.databinding.ActivityMainBinding
import io.github.seoj17.presentaion.ui.setting.ThemeState
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavigation()
        setTheme()
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        return findNavController(binding.container.id).navigateUp()
    }

    private fun initNavigation() {
        val host = supportFragmentManager.findFragmentById(binding.container.id) as NavHostFragment
        navController = host.navController
        binding.bottomNavBar.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, _, arguments ->
            binding.bottomNavBar.isVisible =
                (arguments?.getBoolean("showBottomNavView", true) ?: true) == true
        }
    }

    private fun setTheme() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.themeSetting.collect {
                    AppCompatDelegate.setDefaultNightMode(ThemeState(it).mode)
                }
            }
        }
    }
}

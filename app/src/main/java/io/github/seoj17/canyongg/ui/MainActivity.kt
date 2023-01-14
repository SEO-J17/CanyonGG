package io.github.seoj17.canyongg.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.github.seoj17.canyongg.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
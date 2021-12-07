package com.inaki.starwarsmvpapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.inaki.starwarsmvpapp.adapters.FragmentsAdapter
import com.inaki.starwarsmvpapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.starWarsContainer.adapter = FragmentsAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(binding.starWarsMenu, binding.starWarsContainer) { tab, position ->
            when(position) {
                0 -> {
                    tab.text = "Characters"
                    tab.icon = getDrawable(R.drawable.ic_launcher_foreground)
                }
                1 -> tab.text = "Planets"
                else -> tab.text = "Starships"
            }
        }.attach()
    }
}
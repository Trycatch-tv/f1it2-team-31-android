package com.devslatam.f1it2_team_31_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devslatam.f1it2_team_31_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpButtons()
    }

    private fun setUpButtons() {
        binding.btnLogin.setOnClickListener {

        }
        binding.btnRegister.setOnClickListener {

        }
    }
}
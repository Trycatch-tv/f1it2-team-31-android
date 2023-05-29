package com.devslatam.f1it2_team_31_android.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.devslatam.f1it2_team_31_android.common.entities.UserRequest
import com.devslatam.f1it2_team_31_android.common.utils.Validations
import com.devslatam.f1it2_team_31_android.databinding.ActivityLoginBinding
import com.devslatam.f1it2_team_31_android.viewModel.LoginViewModel
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sepUpObservers()
        setUpButtons()
        backNormally()
    }

    private fun sepUpObservers() {
        loginViewModel.let {
            it.getResult().observe(this) {response->
                Log.i("Token", response.token)
            }
            it.getMsgSnackbar().observe(this){msg->
                Snackbar.make(binding.root,
                    Validations.extractMsg( this@LoginActivity ,msg),
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun setUpButtons() {
        with(binding) {
            etEmail.addTextChangedListener { Validations.validateFields(root, tilEmail) }
            etPassword.addTextChangedListener { Validations.validateFields(root, tilPassword) }
            btnBack.setOnClickListener { back() }
            btnLogin.setOnClickListener {
                if (Validations.validateFields(root, tilEmail, tilPassword)
                    && Validations.isEmailValid(root, tilEmail)) {
                    val userRequest = UserRequest(etEmail.text.toString(), etPassword.text.toString())
                    loginViewModel.postUserLogin(userRequest)
                }
            }
        }
    }

    private fun back() {
        if (isTaskRoot) {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun backNormally() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
    }
}
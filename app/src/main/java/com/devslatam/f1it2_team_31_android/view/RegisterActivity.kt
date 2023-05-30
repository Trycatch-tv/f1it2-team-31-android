package com.devslatam.f1it2_team_31_android.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.devslatam.f1it2_team_31_android.R
import com.devslatam.f1it2_team_31_android.common.entities.UserRegisterRequest
import com.devslatam.f1it2_team_31_android.common.entities.UserRequest
import com.devslatam.f1it2_team_31_android.common.utils.Validations
import com.devslatam.f1it2_team_31_android.databinding.ActivityRegisterBinding
import com.devslatam.f1it2_team_31_android.viewModel.RegisterViewModel
import com.google.android.material.snackbar.Snackbar

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModels()
    private var spinnerValue : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpObservers()
        setUpButtons()
        setUpSpinners()
    }

    private fun setUpObservers() {
        registerViewModel.let {
            it.getResult().observe(this){response ->
                Log.i("Res", response.token.toString())
                if (response != null){
                    finishRegistration()
                }
            }
            it.getMsgSnackbar().observe(this){msg->
                Snackbar.make(binding.root,
                    Validations.extractMsg( this@RegisterActivity ,msg),
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun setUpButtons() {
        with(binding){
            etName.addTextChangedListener { Validations.validateFields(binding.root, tilName) }
            etLastName.addTextChangedListener { Validations.validateFields(binding.root, tilLastName) }
            etEmail.addTextChangedListener { Validations.validateFields(binding.root, tilEmail) }
            etPhone.addTextChangedListener { Validations.validateFields(binding.root, tilPhone) }
            etPassword.addTextChangedListener { Validations.validateFields(binding.root, tilPassword) }
            btnBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
            btnRegister.setOnClickListener {
                if (Validations.validateFields(binding.root, tilName, tilLastName, tilEmail, tilPhone, tilPassword)
                    && Validations.isEmailValid(binding.root, tilEmail) && validateSpinner()){
                    val userRegisterRequest = UserRegisterRequest(
                        etName.text.toString(),
                        etLastName.text.toString(),
                        etPhone.text.toString(),
                        etEmail.text.toString(),
                        etPassword.text.toString(),
                        spinnerValue
                    )
                    registerViewModel.postUserLogin(userRegisterRequest)
                }
            }

        }
    }

    private fun finishRegistration(){
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }, 2000)
    }

    private fun validateSpinner(): Boolean {
        if (spinnerValue != "Cliente" && spinnerValue != "Comerciante" ) {
            Toast.makeText(this@RegisterActivity, getString(R.string.text_type_user_no_selected), Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return true
    }

    private fun setUpSpinners() {
        val spinner = binding.spinnerTypeUser
        val adapter = ArrayAdapter.createFromResource(this, R.array.spinner_values, R.layout.item_textview_spinner)
        adapter.setDropDownViewResource(R.layout.item_checked_spinner)
        spinner.adapter = adapter

        object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                spinnerValue = parent.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                Toast.makeText(this@RegisterActivity, getString(R.string.text_select_value), Toast.LENGTH_SHORT).show()
            }
        }.also { spinner.onItemSelectedListener = it }
    }
}
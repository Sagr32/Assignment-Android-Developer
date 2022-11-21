package com.sakr.assignment.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sakr.assignment.data.local.User
import com.sakr.assignment.databinding.FragmentRegistrationBinding
import com.sakr.assignment.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    lateinit var edtEmail: EditText
    lateinit var edtPassword: EditText
    lateinit var edtName: EditText
    lateinit var btnLogin: TextView
    lateinit var btnRegister: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRegistrationBinding.inflate(inflater)
        edtEmail = binding.edtUserName
        edtPassword = binding.edtPassword
        edtName = binding.edtFirstName
        btnLogin = binding.signInBtn
        btnRegister = binding.signUpButton
        // Inflate the layout for this fragment
        btnLogin.setOnClickListener {
            this.findNavController()
                .navigate(RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment())

        }

        btnRegister.setOnClickListener {
            if (edtName.text.isNotBlank() && edtEmail.text.isNotBlank() && edtPassword.text.isNotBlank()) {
                viewModel.saveUser(
                    User(
                        password = edtPassword.text.toString(),
                        email = edtEmail.text.toString(),
                        firstName = edtName.text.toString(),
                    )
                )
                Toast.makeText(
                    requireContext(),
                    "Welcome ${edtName.text.toString()} !",
                    Toast.LENGTH_SHORT
                ).show()
                this.findNavController()
                    .navigate(RegistrationFragmentDirections.actionRegistrationFragmentToHomeFragment())

            } else {
                Toast.makeText(requireContext(), "Input can't be empty", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
}
package com.sakr.assignment.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.sakr.assignment.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    lateinit var edtEmail: EditText
    lateinit var edtPassword: EditText
    lateinit var btnLogin: Button
    lateinit var btnRegister: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLoginBinding.inflate(inflater)
        edtEmail = binding.edtUserName
        edtPassword = binding.edtPassword
        btnLogin = binding.signInBtn
        btnRegister = binding.signUpButton

        btnLogin.setOnClickListener {
            if (edtEmail.text.isNotBlank() && edtPassword.text.isNotBlank()) {
                this.findNavController()
                    .navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
            } else {
                Toast.makeText(requireContext(), "Input can't be empty", Toast.LENGTH_SHORT).show()
            }
        }

        btnRegister.setOnClickListener {
            this.findNavController()
                .navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment())

        }

        // Inflate the layout for this fragment
        return binding.root
    }


}
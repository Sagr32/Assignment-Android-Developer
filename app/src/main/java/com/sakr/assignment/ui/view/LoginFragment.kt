package com.sakr.assignment.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sakr.assignment.databinding.FragmentLoginBinding
import com.sakr.assignment.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Observer

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
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


        viewModel.userInfo.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it != null) {
                Toast.makeText(requireContext(), "Welcome ${it.firstName} !", Toast.LENGTH_SHORT)
                    .show()
                this.findNavController()
                    .navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
            } else {
                Toast.makeText(
                    requireContext(),
                    "Check your information again !",
                    Toast.LENGTH_SHORT
                ).show()

            }

        })

        btnLogin.setOnClickListener {
            if (edtEmail.text.isNotBlank() && edtPassword.text.isNotBlank()) {
                viewModel.checkUserDetails(edtEmail.text.toString(), edtPassword.text.toString())
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
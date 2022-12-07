package com.udacity.shoestore
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding



class LoginFragment : Fragment() {
    private lateinit var binding:FragmentLoginBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            LoginButton.setOnClickListener { login() }
            registerButton.setOnClickListener { login() }
        }

    }

    private fun login() {
        if (sharedViewModel.loginSuccess()){
            val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
            findNavController().navigate(action)
        }else{
            Toast.makeText(
                context,
                getString(R.string.loginErrorMessage),
                Toast.LENGTH_SHORT).show()
        }
    }
}

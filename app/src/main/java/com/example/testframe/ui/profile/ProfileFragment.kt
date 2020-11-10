package com.example.testframe.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.testframe.R
import com.example.testframe.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

  lateinit var binding: FragmentProfileBinding
  private val profileViewModel: ProfileViewModel by viewModels()
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile, container, false)
    binding.viewModel = profileViewModel
    profileViewModel.profileName.observe(viewLifecycleOwner){
      it?.let {
        binding.profileUserName.text = it.userName
        binding.mailText.text = it.mail
        binding.phoneText.text = it.phoneNumber
        binding.eduText.text = it.education
      }
    }
    return binding.root
  }
}
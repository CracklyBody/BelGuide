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
    binding.executePendingBindings()
    profileViewModel.profileName.observe(viewLifecycleOwner){}
    return binding.root
  }
}
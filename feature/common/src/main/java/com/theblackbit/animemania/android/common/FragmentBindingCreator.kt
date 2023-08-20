package com.theblackbit.animemania.android.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class FragmentBindingCreator<T : ViewDataBinding> : Fragment() {
    lateinit var binding: T
    abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!::binding.isInitialized) {
            binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        }
        binding.also {
            it.lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }
}

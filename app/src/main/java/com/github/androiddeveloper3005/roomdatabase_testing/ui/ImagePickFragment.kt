package com.github.androiddeveloper3005.roomdatabase_testing.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.androiddeveloper3005.roomdatabase_testing.R

class ImagePickFragment : Fragment(R.layout.image_pick_fragment) {
    lateinit var viewModel: ShoppingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)
    }
}
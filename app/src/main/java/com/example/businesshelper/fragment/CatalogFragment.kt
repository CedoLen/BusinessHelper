package com.example.businesshelper.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.businesshelper.R
import com.example.businesshelper.databinding.FragmentCatalogBinding
import com.example.businesshelper.fragment.addfragment.AddProductFragment

class CatalogFragment:Fragment(R.layout.fragment_catalog) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = FragmentCatalogBinding.inflate(layoutInflater)
        val fragment = AddProductFragment()
        bind.openAddProduct.setOnClickListener{
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.flFragment,fragment, AddProductFragment::class.java.simpleName)
                    .addToBackStack(null)
                    .commit()
            }
        }
        return bind.root
    }
}
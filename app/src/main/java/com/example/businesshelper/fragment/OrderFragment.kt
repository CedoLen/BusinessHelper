package com.example.businesshelper.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.businesshelper.R
import com.example.businesshelper.databinding.FragmentOrderBinding
import com.example.businesshelper.fragment.addfragment.AddOrderFragment

class OrderFragment:Fragment(R.layout.fragment_order) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = FragmentOrderBinding.inflate(layoutInflater)
        val fragment = AddOrderFragment()
        bind.openAddOrder.setOnClickListener{
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.flFragment,fragment, AddOrderFragment::class.java.simpleName)
                    .addToBackStack(null)
                    .commit()
            }
        }
        return bind.root
    }
}
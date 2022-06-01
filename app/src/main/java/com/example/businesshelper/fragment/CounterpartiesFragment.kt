package com.example.businesshelper.fragment

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import com.example.businesshelper.fragment.addfragment.AddCounterpartiesFragment
import com.example.businesshelper.R
import com.example.businesshelper.databinding.FragmentCounterpartiesBinding


class CounterpartiesFragment:Fragment(R.layout.fragment_counterparties){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = FragmentCounterpartiesBinding.inflate(layoutInflater)
        val fragment = AddCounterpartiesFragment()
        bind.openAddCounterparties.setOnClickListener{
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.flFragment,fragment, AddCounterpartiesFragment::class.java.simpleName)
                    .addToBackStack(null)
                    .commit()
            }
        }
    return bind.root
    }
}
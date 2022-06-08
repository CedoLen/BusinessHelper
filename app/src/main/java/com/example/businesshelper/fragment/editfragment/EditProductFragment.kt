package com.example.businesshelper.fragment.editfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.businesshelper.R
import com.example.businesshelper.data.Product
import com.example.businesshelper.databinding.FragmentEditCounterpartyBinding
import com.example.businesshelper.databinding.FragmentEditProductBinding
import com.google.firebase.database.DatabaseReference


class EditProductFragment(val data:Product) : Fragment(R.layout.fragment_edit_product) {

    private lateinit var database: DatabaseReference
    private lateinit var bind: FragmentEditProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentEditProductBinding.inflate(layoutInflater)

        bind.stData = data

        return bind.root
    }


}
package com.example.businesshelper.fragment.editfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.businesshelper.R
import com.example.businesshelper.data.Counterparty
import com.example.businesshelper.databinding.FragmentCounterpartiesBinding
import com.example.businesshelper.databinding.FragmentEditCounterpartyBinding
import com.example.businesshelper.fragment.addfragment.AddCounterpartiesFragment
import com.google.firebase.database.DatabaseReference
import java.util.ArrayList


class EditCounterpartyFragment(val data:Counterparty) : Fragment(R.layout.fragment_edit_counterparty) {

    private lateinit var database: DatabaseReference
    private lateinit var bind: FragmentEditCounterpartyBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentEditCounterpartyBinding.inflate(layoutInflater)

        bind.stData = data

        return bind.root
    }

}
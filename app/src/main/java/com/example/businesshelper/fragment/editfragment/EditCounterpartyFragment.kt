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
import com.google.firebase.database.FirebaseDatabase
import java.util.ArrayList


class EditCounterpartyFragment(var data:Counterparty) : Fragment(R.layout.fragment_edit_counterparty) {

    private lateinit var database: DatabaseReference
    private lateinit var bind: FragmentEditCounterpartyBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentEditCounterpartyBinding.inflate(layoutInflater)
        database = FirebaseDatabase.getInstance().getReference("counterparties")
        bind.stData = data


        val email = bind.root.findViewById<EditText>(R.id.email_counterparty_edit)
        val company = bind.root.findViewById<EditText>(R.id.name_counterparty_edit)
        val phone = bind.root.findViewById<EditText>(R.id.phone_counterparty_edit)
        val inn = bind.root.findViewById<EditText>(R.id.inn_counterparty_edit)
        val kpp = bind.root.findViewById<EditText>(R.id.kpp_counterparty_edit)
        val legalAd = bind.root.findViewById<EditText>(R.id.legalAddress_counterparty_edit)
        val actualAd = bind.root.findViewById<EditText>(R.id.actualAddress_counterparty_edit)


        bind.removeCounterItem.setOnClickListener{
            database.child(data.id.toString()).removeValue()
        }
        bind.editCounterItem.setOnClickListener{
            data.kpp =kpp.text.toString()
            data.inn = inn.text.toString()
            data.email=email.text.toString()
            data.company=company.text.toString()
            data.phone=phone.text.toString()
            data.legalAddress=legalAd.text.toString()
            data.actualAddress=actualAd.text.toString()

            database.child(data.id.toString()).setValue(data)
        }
        return bind.root
    }


}
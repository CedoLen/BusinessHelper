package com.example.businesshelper.fragment.addfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.businesshelper.R
import com.example.businesshelper.data.Counterparty
import com.example.businesshelper.databinding.FragmentAddCounterpartiesBinding
import com.example.businesshelper.databinding.FragmentCounterpartiesBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AddCounterpartiesFragment:Fragment(R.layout.fragment_add_counterparties) {
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = FragmentAddCounterpartiesBinding.inflate(inflater, container, false)
        database =FirebaseDatabase.getInstance().getReference("counterparties")

        bind.addNewItem.setOnClickListener {
            try {
                val id=database.push().key
                val email = bind.root.findViewById<EditText>(R.id.email_counterparty).text.toString()
                val company = bind.root.findViewById<EditText>(R.id.name_counterparty).text.toString()
                val phone = bind.root.findViewById<EditText>(R.id.phone_counterparty).text.toString()
                val inn = bind.root.findViewById<EditText>(R.id.inn_counterparty).text.toString()
                val kpp = bind.root.findViewById<EditText>(R.id.kpp_counterparty).text.toString()
                val legalAd = bind.root.findViewById<EditText>(R.id.legalAddress_counterparty).text.toString()
                val actualAd = bind.root.findViewById<EditText>(R.id.actualAddress_counterparty).text.toString()

                val human = Counterparty(
                    id,
                    email,
                    company,
                    phone,
                    inn,
                    kpp,
                    legalAd,
                    actualAd
                )
                database.push().setValue(human)

                fragmentManager?.popBackStackImmediate()
            }
            catch (e:Exception)
            {
                Toast.makeText(bind.root.context,e.message.toString(),Toast.LENGTH_LONG).show()
            }
        }
        return bind.root
    }
}
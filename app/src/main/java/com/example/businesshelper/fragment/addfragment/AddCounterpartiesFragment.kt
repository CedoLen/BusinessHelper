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
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AddCounterpartiesFragment:Fragment(R.layout.fragment_add_counterparties) {
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = FragmentAddCounterpartiesBinding.inflate(layoutInflater)
        database = Firebase.database.reference

        bind.addNewItem.setOnClickListener {
            try {
                var email = bind.root.findViewById<EditText>(R.id.email_counterparty)
                var company = bind.root.findViewById<EditText>(R.id.name_counterparty)
                var phone = bind.root.findViewById<EditText>(R.id.phone_counterparty)
                var inn = bind.root.findViewById<EditText>(R.id.inn_counterparty)
                var kpp = bind.root.findViewById<EditText>(R.id.kpp_counterparty)
                var legalAd = bind.root.findViewById<EditText>(R.id.legalAddress_counterparty)
                var actualAd = bind.root.findViewById<EditText>(R.id.actualAddress_counterparty)

                var human = Counterparty(
                    email.text.toString(),
                    company.text.toString(),
                    phone.text.toString(),
                    inn.text.toString(),
                    kpp.text.toString(),
                    legalAd.text.toString(),
                    actualAd.text.toString()
                )
                database.child("counterparties").child(id.toString()).setValue(human)
            }
            catch (e:Exception)
            {
                Toast.makeText(bind.root.context,e.message.toString(),Toast.LENGTH_LONG).show()
            }
        }
        return bind.root
    }
}
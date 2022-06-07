package com.example.businesshelper.fragment.addfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.example.businesshelper.R
import com.example.businesshelper.data.Counterparty
import com.example.businesshelper.data.Product
import com.example.businesshelper.databinding.FragmentAddCounterpartiesBinding
import com.example.businesshelper.databinding.FragmentAddProductBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class AddProductFragment:Fragment(R.layout.fragment_add_product){
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = FragmentAddProductBinding.inflate(inflater, container, false)
        database = FirebaseDatabase.getInstance().getReference("catalog")

        bind.addNewItem.setOnClickListener {
            try {
                val id= database.key
                val title = bind.root.findViewById<EditText>(R.id.title_product).text.toString()
                val price = bind.root.findViewById<EditText>(R.id.price_product).text.toString().toLong()
                val unit = bind.root.findViewById<EditText>(R.id.unit_product).text.toString()
                val rawMaterials = bind.root.findViewById<EditText>(R.id.rawMaterials_product).text.toString().toDouble()
                val salary = bind.root.findViewById<EditText>(R.id.salary_product).text.toString().toDouble()
                val socialNeeds = bind.root.findViewById<EditText>(R.id.socialNeeds).text.toString().toDouble()
                val depreciation = bind.root.findViewById<EditText>(R.id.depreciation_product).text.toString().toDouble()
                val taxes = bind.root.findViewById<EditText>(R.id.taxes_product).text.toString().toDouble()
                val storage = bind.root.findViewById<EditText>(R.id.storage_product).text.toString().toDouble()
                val other = bind.root.findViewById<EditText>(R.id.other_product).text.toString().toDouble()
                val dateRegistration = Calendar.getInstance().time.toString()

                val product = Product(
                    id,
                    title,
                    price,
                    unit,
                    rawMaterials,
                    salary,
                    socialNeeds,
                    depreciation,
                    taxes,
                    storage,
                    other,
                    dateRegistration
                )
                database.push().setValue(product)
                Toast.makeText(bind.root.context,"Объект добавлен", Toast.LENGTH_LONG).show()
            }
            catch (e:Exception)
            {
                Toast.makeText(bind.root.context,e.message.toString(), Toast.LENGTH_LONG).show()
            }
        }
        return bind.root
    }
}
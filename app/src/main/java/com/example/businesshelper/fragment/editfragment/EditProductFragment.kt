package com.example.businesshelper.fragment.editfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.businesshelper.R
import com.example.businesshelper.data.Product
import com.example.businesshelper.databinding.FragmentEditCounterpartyBinding
import com.example.businesshelper.databinding.FragmentEditProductBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*


class EditProductFragment(val data:Product) : Fragment(R.layout.fragment_edit_product) {

    private lateinit var database: DatabaseReference
    private lateinit var bind: FragmentEditProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentEditProductBinding.inflate(layoutInflater)
        database = FirebaseDatabase.getInstance().getReference("catalog")
        bind.stData = data

        val title = bind.root.findViewById<EditText>(R.id.title_product_edit)
        val price = bind.root.findViewById<EditText>(R.id.price_product_edit)
        val unit = bind.root.findViewById<EditText>(R.id.unit_product_edit)
        val rawMaterials = bind.root.findViewById<EditText>(R.id.rawMaterials_product_edit)
        val salary = bind.root.findViewById<EditText>(R.id.salary_product_edit)
        val socialNeeds = bind.root.findViewById<EditText>(R.id.socialNeeds_product_edit)
        val depreciation = bind.root.findViewById<EditText>(R.id.depreciation_product_edit)
        val taxes = bind.root.findViewById<EditText>(R.id.taxes_product_edit)
        val storage = bind.root.findViewById<EditText>(R.id.storage_product_edit)
        val other = bind.root.findViewById<EditText>(R.id.other_product_edit)

        bind.removeProductItem.setOnClickListener{
            database.child(data.id.toString()).removeValue()
            fragmentManager?.popBackStackImmediate()
        }
        bind.editProductItem.setOnClickListener{
            data.title = title.text.toString()
            data.price=price.text.toString().toLong()
            data.unit = unit.text.toString()
            data.rawMaterials = rawMaterials.text.toString().toInt()
            data.salary =salary.text.toString().toInt()
            data.socialNeeds=socialNeeds.text.toString().toInt()
            data.depreciation=depreciation.text.toString().toInt()
            data.taxes=taxes.text.toString().toInt()
            data.storage=storage.text.toString().toInt()
            data.other=other.text.toString().toInt()

            database.child(data.id.toString()).setValue(data)
            fragmentManager?.popBackStackImmediate()
        }

        return bind.root
    }


}
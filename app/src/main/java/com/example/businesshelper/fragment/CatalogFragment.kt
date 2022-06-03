package com.example.businesshelper.fragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.businesshelper.R
import com.example.businesshelper.data.Counterparty
import com.example.businesshelper.data.Product
import com.example.businesshelper.data.adepters.catalogAdapter
import com.example.businesshelper.data.adepters.counterpartiesAdapter
import com.example.businesshelper.databinding.FragmentCatalogBinding
import com.example.businesshelper.fragment.addfragment.AddProductFragment
import com.google.firebase.database.*
import java.util.ArrayList

class CatalogFragment:Fragment(R.layout.fragment_catalog) {
    private lateinit var database: DatabaseReference
    private lateinit var productList: ArrayList<Product>

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

        database = FirebaseDatabase.getInstance().getReference("catalog")

        val recyclerView: RecyclerView = bind.root.findViewById(R.id.recyclerView_catalog)
        recyclerView.setHasFixedSize(true)

        productList = arrayListOf<Product>()
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                productList.clear()
                if(snapshot.exists()){
                    for(item in snapshot.children)
                    {
                        val product = item.getValue(Product::class.java)
                        productList.add(product!!)
                    }
                    recyclerView.adapter = catalogAdapter(requireContext(),productList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "loadPost:onCancelled", error.toException())
            }
        })

        return bind.root
    }
}
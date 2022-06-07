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
import com.example.businesshelper.fragment.addfragment.AddOrderFragment
import com.example.businesshelper.fragment.addfragment.AddProductFragment
import com.example.businesshelper.fragment.editfragment.EditCounterpartyFragment
import com.example.businesshelper.fragment.editfragment.EditProductFragment
import com.google.firebase.database.*
import java.util.ArrayList

class CatalogFragment:Fragment(R.layout.fragment_catalog), catalogAdapter.CellClickListener {
    private lateinit var database: DatabaseReference
    private lateinit var productList: ArrayList<Product>
    private lateinit var bind:FragmentCatalogBinding
    private var editFragment:EditProductFragment = EditProductFragment()
    private var addFragment:AddProductFragment= AddProductFragment()
    private lateinit var recyclerView:RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentCatalogBinding.inflate(layoutInflater)
        bind.openAddProduct.setOnClickListener{
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.flFragment,addFragment, AddProductFragment::class.java.simpleName)
                    .addToBackStack(null)
                    .commit()
            }
        }

        database = FirebaseDatabase.getInstance().getReference("catalog")

        recyclerView = bind.root.findViewById(R.id.recyclerView_catalog)
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
                    recyclerView.adapter = catalogAdapter(requireContext(),productList, this@CatalogFragment)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "loadPost:onCancelled", error.toException())
            }
        })

        return bind.root
    }

    override fun onClickProductListener(data: Product) {
        fragmentManager?.beginTransaction()?.apply {
            replace(R.id.flFragment,editFragment, EditProductFragment::class.java.simpleName)
                .addToBackStack(null)
                .commit()
        }
    }
}
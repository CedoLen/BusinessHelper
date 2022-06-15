package com.example.businesshelper.fragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
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
    private lateinit var editFragment:EditProductFragment
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
                    productList.sortBy { item->item.title }
                    recyclerView.adapter = catalogAdapter(requireContext(),productList, this@CatalogFragment)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "loadPost:onCancelled", error.toException())
            }
        })
        setHasOptionsMenu(true)
        return bind.root
    }

    override fun onClickProductListener(data: Product) {
        editFragment = EditProductFragment(data)
        fragmentManager?.beginTransaction()?.apply {
            replace(R.id.flFragment,editFragment, EditProductFragment::class.java.simpleName)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_menu, menu)

        val search = menu.findItem(R.id.action_search)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {

                productList.filter { item->item.title!!.contains(newText.toString()) }
                recyclerView.adapter = catalogAdapter(requireContext(),productList, this@CatalogFragment )
                return true
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.action_sort->{

            }
        }
        return true
    }
}
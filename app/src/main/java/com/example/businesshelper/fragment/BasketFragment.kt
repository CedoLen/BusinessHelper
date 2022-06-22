package com.example.businesshelper.fragment

import android.app.AlertDialog
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.businesshelper.R
import com.example.businesshelper.data.*
import com.example.businesshelper.data.adepters.basketAdapter
import com.example.businesshelper.data.adepters.catalogAdapter
import com.example.businesshelper.databinding.FragmentBasketBinding
import com.example.businesshelper.databinding.FragmentCatalogBinding
import com.example.businesshelper.fragment.addfragment.AddProductFragment
import com.example.businesshelper.fragment.editfragment.EditProductFragment
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Response
import java.util.ArrayList

class BasketFragment:Fragment(R.layout.fragment_basket), basketAdapter.CellClickListener {
    private lateinit var database: DatabaseReference
    private lateinit var productList: ArrayList<Product>
    private lateinit var bind: FragmentBasketBinding
    private lateinit var recyclerView:RecyclerView
    private lateinit var query: Query

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentBasketBinding.inflate(layoutInflater)
        database = FirebaseDatabase.getInstance().getReference("basket")

        recyclerView = bind.root.findViewById(R.id.rV_basket)
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
                    recyclerView.adapter = basketAdapter(requireContext(),productList, this@BasketFragment)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "loadPost:onCancelled", error.toException())
            }
        })



        return bind.root
    }

    override fun onClickBasketListener(data: Product) {
        val builder = AlertDialog.Builder(bind.root.context)
        builder.setTitle("Удаление элемента")
            .setMessage("Убрать из корзины: ${data.title}?")
            .setCancelable(true)
            .setPositiveButton("Да") { dialog, id ->

                query = database.orderByChild("id").equalTo(data.id.toString())
                query.addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if(snapshot.exists()){
                            for (item in snapshot.children)
                            {
                                item.ref.removeValue()
                            }
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        Log.e(ContentValues.TAG, "onCancelled", error.toException());
                    }
                })
            }
            .setNegativeButton("Нет"){ dialog, id -> }
        builder.show()
    }

}
package com.example.businesshelper.fragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.businesshelper.R
import com.example.businesshelper.data.Counterparty
import com.example.businesshelper.data.Order
import com.example.businesshelper.data.Product
import com.example.businesshelper.data.adepters.basketAdapter
import com.example.businesshelper.data.adepters.ordersAdapter
import com.example.businesshelper.databinding.FragmentBasketBinding
import com.example.businesshelper.databinding.FragmentOrderBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import java.util.ArrayList

class OrderFragment:Fragment(R.layout.fragment_order) {
    private lateinit var database: DatabaseReference
    private lateinit var orderList: ArrayList<Order>
    private lateinit var bind: FragmentOrderBinding
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentOrderBinding.inflate(layoutInflater)
        database = FirebaseDatabase.getInstance().getReference("orders")

        recyclerView = bind.root.findViewById(R.id.rV_orders)
        recyclerView.setHasFixedSize(true)

        orderList = arrayListOf<Order>()
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                orderList.clear()
                if(snapshot.exists()){
                    for(item in snapshot.children)
                    {

                        val id = item.child("id").value.toString()
                        val status = item.child("status").value.toString()
                        val dateRegistration = item.child("dateRegistration").value.toString()
                        val dateIssue= item.child("dateIssue").value.toString()
                        val deliveryAddress= item.child("deliveryAddress").value.toString()
                        val counterCompany= item.child("counterCompany").value.toString()
                        val counterparty = Counterparty(
                            id = item.child("counterparty").child("id").value.toString(),
                            email= item.child("counterparty").child("email").value.toString(),
                            company= item.child("counterparty").child("company").value.toString(),
                            phone= item.child("counterparty").child("phone").value.toString(),
                            inn= item.child("counterparty").child("inn").value.toString(),
                            kpp= item.child("counterparty").child("kpp").value.toString(),
                            legalAddress= item.child("counterparty").child("legalAddress").value.toString(),
                            actualAddress= item.child("counterparty").child("actualAddress").value.toString()
                        )


                        val order = Order(
                            id = id,
                            status = status,
                            dateRegistration =dateRegistration,
                            dateIssue= dateIssue,
                            deliveryAddress= deliveryAddress,
                            counterCompany= counterCompany,
                            counterparty = counterparty
                        )
                        orderList.add(order)
                    }

                    recyclerView.adapter = ordersAdapter(requireContext(),orderList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "loadPost:onCancelled", error.toException())
            }
        })

        return bind.root
    }
}
package com.example.businesshelper.fragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.businesshelper.fragment.addfragment.AddCounterpartiesFragment
import com.example.businesshelper.R
import com.example.businesshelper.data.Counterparty
import com.example.businesshelper.data.Status
import com.example.businesshelper.data.adepters.counterpartiesAdapter
import com.example.businesshelper.databinding.FragmentCounterpartiesBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import java.util.ArrayList


class CounterpartiesFragment:Fragment(R.layout.fragment_counterparties){

    private lateinit var database: DatabaseReference
    private lateinit var counterList: ArrayList<Counterparty>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = FragmentCounterpartiesBinding.inflate(layoutInflater)
        val fragment = AddCounterpartiesFragment()
        bind.openAddCounterparties.setOnClickListener{
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.flFragment,fragment, AddCounterpartiesFragment::class.java.simpleName)
                    .addToBackStack(null)
                    .commit()
            }
        }


        database = FirebaseDatabase.getInstance().getReference("counterparties")

        val recyclerView: RecyclerView = bind.root.findViewById(R.id.recyclerView_counter)
        recyclerView.setHasFixedSize(true)

        counterList = arrayListOf<Counterparty>()
        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                counterList.clear()
                if(snapshot.exists()){
                    for(item in snapshot.children)
                    {
                        val human = item.getValue(Counterparty::class.java)
                        counterList.add(human!!)
                    }
                    recyclerView.adapter = counterpartiesAdapter(requireContext(),counterList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "loadPost:onCancelled", error.toException())
            }

        })

    return bind.root
    }
}
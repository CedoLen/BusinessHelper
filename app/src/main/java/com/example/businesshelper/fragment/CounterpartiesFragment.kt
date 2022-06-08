package com.example.businesshelper.fragment

import android.content.ContentValues
import android.content.Intent
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
import com.example.businesshelper.fragment.addfragment.AddProductFragment
import com.example.businesshelper.fragment.editfragment.EditCounterpartyFragment
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import java.util.ArrayList


class CounterpartiesFragment:Fragment(R.layout.fragment_counterparties),
    counterpartiesAdapter.CellClickListener {

    private lateinit var database: DatabaseReference
    private lateinit var counterList: ArrayList<Counterparty>
    private lateinit var bind: FragmentCounterpartiesBinding
    private var addFragment: AddCounterpartiesFragment = AddCounterpartiesFragment()
    private lateinit var editFragment:EditCounterpartyFragment
    private lateinit var recyclerView:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentCounterpartiesBinding.inflate(layoutInflater)
        bind.openAddCounterparties.setOnClickListener{
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.flFragment,addFragment, AddCounterpartiesFragment::class.java.simpleName)
                    .addToBackStack(null)
                    .commit()
            }
        }


        database = FirebaseDatabase.getInstance().getReference("counterparties")

        recyclerView = bind.root.findViewById(R.id.recyclerView_counter)
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
                    recyclerView.adapter = counterpartiesAdapter(requireContext(),counterList, this@CounterpartiesFragment )
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "loadPost:onCancelled", error.toException())
            }

        })

    return bind.root
    }

    override fun onClickCounterListener(data: Counterparty) {
        editFragment = EditCounterpartyFragment(data)
        fragmentManager?.beginTransaction()?.apply {
            replace(R.id.flFragment,editFragment,EditCounterpartyFragment::class.java.simpleName)
                .addToBackStack(null)
                .commit()
        }
    }
}
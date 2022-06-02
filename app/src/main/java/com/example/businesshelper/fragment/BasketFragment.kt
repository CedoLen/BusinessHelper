package com.example.businesshelper.fragment

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
import com.example.businesshelper.databinding.FragmentBasketBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Response
import java.util.ArrayList

class BasketFragment:Fragment(R.layout.fragment_basket) {
    private var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: DatabaseReference
    private lateinit var statusList:ArrayList<Status>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBasketBinding.inflate(inflater, container, false)
        val root: View = binding.root

        database = Firebase.database.reference

        val recyclerView: RecyclerView = root.findViewById(R.id.temp_recyclerView)

        statusList = arrayListOf<Status>()
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                if(dataSnapshot.exists()) {
                    for (item in dataSnapshot.children) {
                        val child = item.getValue(Status::class.java)
                        statusList.add(child!!)
                    }

                    recyclerView.adapter = listAdapter(requireContext(),statusList)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        database.addValueEventListener(postListener)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
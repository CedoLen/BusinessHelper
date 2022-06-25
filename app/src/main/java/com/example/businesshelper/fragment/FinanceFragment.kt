package com.example.businesshelper.fragment

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.businesshelper.R
import com.example.businesshelper.addActivity.*
import com.example.businesshelper.data.Expenses
import com.example.businesshelper.data.InCome
import com.example.businesshelper.data.adepters.fullFinaceAdapter
import com.example.businesshelper.data.api.Currencies
import com.example.businesshelper.data.api.InterfaceCurrency
import com.example.businesshelper.data.api.RetrofitCurrency
import com.example.businesshelper.data.api.Valute
import com.example.businesshelper.data.fullFinance
import com.example.businesshelper.databinding.FragmentFinanceBinding
import com.google.firebase.database.*
import retrofit2.Call
import retrofit2.Response

class FinanceFragment:Fragment(R.layout.fragment_finance) {

    private lateinit var bind:FragmentFinanceBinding
    private lateinit var database: DatabaseReference

    private lateinit var listInCome:ArrayList<fullFinance>
    private lateinit var listExpenses:ArrayList<fullFinance>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentFinanceBinding.inflate(inflater)
        database = FirebaseDatabase.getInstance().reference

        val ret = RetrofitCurrency().getRetrofit()
        val inter = ret.create(InterfaceCurrency::class.java)
        val call = inter.getCurrency().enqueue(object : retrofit2.Callback<Currencies>{
            override fun onResponse(call: Call<Currencies>, response: Response<Currencies>) {
                if(response.isSuccessful){
                    try {
                        val temp = response.body()!!
                        bind.outputUSDCurrency.setText(temp!!.Valutes!!.get("USD")!!.Value)
                        bind.outputEURCurrency.setText(temp!!.Valutes!!.get("EUR")!!.Value)
                    }
                    catch (e:Exception)
                    { Toast.makeText(bind.root.context,e.message,Toast.LENGTH_SHORT).show() }
                }
            }
            override fun onFailure(call: Call<Currencies>, t: Throwable) {
                Toast.makeText(bind.root.context, t.localizedMessage, Toast.LENGTH_LONG).show() }
        })

        bind.btPlusIncome.setOnClickListener {
            val intent = Intent(bind.root.context, PlusInComeActivity::class.java)
            startActivity(intent)
        }
        bind.btMinusIncome.setOnClickListener {
            val intent = Intent(bind.root.context, MinusInComeActivity::class.java)
            startActivity(intent)
        }
        bind.btPlusExpense.setOnClickListener {
            val intent = Intent(bind.root.context, PlusExpenseActivity::class.java)
            startActivity(intent)
        }
        bind.btMinusExpense.setOnClickListener {
            val intent = Intent(bind.root.context, MinusExpenseActivity::class.java)
            startActivity(intent)
        }

        bind.rVIncomeProgress.setHasFixedSize(true)
        bind.rVExpenseProgress.setHasFixedSize(true)

        getExpense()
        getIncome()

        bind.rVIncomeProgress.adapter = fullFinaceAdapter(requireContext(),listInCome)
        bind.rVExpenseProgress.adapter = fullFinaceAdapter(requireContext(),listExpenses)

        return bind.root
    }

    fun getIncome() {
        listInCome= arrayListOf(
            fullFinance(type = "tic1", title = resources.getString(R.string.tic1)),
            fullFinance(type = "tic2", title = resources.getString(R.string.tic2)),
            fullFinance(type = "tic3", title = resources.getString(R.string.tic3)),
            fullFinance(type = "tic4", title = resources.getString(R.string.tic4)))

        database.child("inCome").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (item in snapshot.children) {
                        val temp = item.getValue(InCome::class.java)
                        for (value in listInCome) {
                            if(value.type==temp!!.type){
                                val plus = value.currentSum.plus(temp.sum)
                                value.currentSum = plus
                            } } } } }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "loadPost:onCancelled", error.toException()) }
        })

        database.child("futureInCome").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (item in snapshot.children) {
                        val temp = item.getValue(InCome::class.java)
                        for (value in listInCome) {
                            if(value.type==temp!!.type){
                                val plus = value.futureSum.plus(temp.sum)
                                value.futureSum = plus
                            } } } } }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "loadPost:onCancelled", error.toException()) }
        })
    }

    fun  getExpense(){
        listExpenses= arrayListOf(
            fullFinance(type = "te1", title = resources.getString(R.string.te1)),
            fullFinance(type = "te2", title = resources.getString(R.string.te2)),
            fullFinance(type = "te3", title = resources.getString(R.string.te3)),
            fullFinance(type = "te4", title = resources.getString(R.string.te4)),
            fullFinance(type = "te5", title = resources.getString(R.string.te5)),
            fullFinance(type = "te6", title = resources.getString(R.string.te6)),
            fullFinance(type = "te7", title = resources.getString(R.string.te7)),
            fullFinance(type = "te8", title = resources.getString(R.string.te8)),
            fullFinance(type = "te9", title = resources.getString(R.string.te9)),
            fullFinance(type = "te10", title = resources.getString(R.string.te10)))

        database.child("expenses").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (item in snapshot.children) {
                        val temp = item.getValue(Expenses::class.java)
                        for (value in listExpenses) {
                            if(value.type==temp!!.type){
                                val plus = value.currentSum.plus(temp.sum)
                                value.currentSum = plus
                            } } } } }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "loadPost:onCancelled", error.toException()) }
        })

        database.child("futureExpenses").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (item in snapshot.children) {
                        val temp = item.getValue(Expenses::class.java)
                        for (value in listExpenses) {
                            if(value.type==temp!!.type){
                                val plus = value.futureSum.plus(temp.sum)
                                value.futureSum = plus
                            } } } } }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "loadPost:onCancelled", error.toException()) }
        })
    }
}

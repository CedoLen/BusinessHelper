package com.example.businesshelper.data.adepters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.businesshelper.R
import com.example.businesshelper.data.Product
import com.example.businesshelper.data.api.Currencies
import com.example.businesshelper.data.api.Currency
import java.util.ArrayList

class currencyAdapter(val context: Context, val list: Currencies): RecyclerView.Adapter<currencyAdapter.MyVM>() {
    class MyVM(itemView: View):RecyclerView.ViewHolder(itemView) {
        val title:TextView =  itemView.findViewById(R.id.title_currency)
        val value:TextView = itemView.findViewById(R.id.value_currency)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): currencyAdapter.MyVM {
        val root = LayoutInflater.from(context).inflate(R.layout.currency_card,parent,false)
        return MyVM(root)
    }

    override fun onBindViewHolder(holder: currencyAdapter.MyVM, position: Int) {
        holder.title.text = list.data[position].CharCode
        holder.value.text = list.data[position].Value.toString()
    }

    override fun getItemCount(): Int {
        return list.data.size
    }

}
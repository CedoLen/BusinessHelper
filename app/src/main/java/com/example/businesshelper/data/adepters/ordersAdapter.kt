package com.example.businesshelper.data.adepters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.businesshelper.R
import com.example.businesshelper.data.Counterparty
import com.example.businesshelper.data.Order
import java.util.ArrayList

class ordersAdapter(val context: Context, val list: ArrayList<Order>): RecyclerView.Adapter<ordersAdapter.MyVM>() {
    class MyVM(itemView: View):RecyclerView.ViewHolder(itemView) {
        val bakgImage:ImageView = itemView.findViewById(R.id.image_order_VM)
        val counter:TextView = itemView.findViewById(R.id.counter_order_VM)
        val dates:TextView = itemView.findViewById(R.id.dates_order_VM)
        val status:TextView=itemView.findViewById(R.id.status_order_VM)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ordersAdapter.MyVM {
        val root = LayoutInflater.from(context).inflate(R.layout.order_card,parent,false)
        return ordersAdapter.MyVM(root)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ordersAdapter.MyVM, position: Int) {
        holder.counter.text = list[position].counterparty!!.company
        holder.dates.text = "${list[position].dateRegistration}-${list[position].dateIssue}"

        if(list[position].status=="st1")
        {
            holder.bakgImage.setBackgroundResource(R.color.st1)
            holder.status.text = "Новый"
        }
        else
        {
            holder.bakgImage.setBackgroundResource(R.color.white)
            holder.status.text = "-"
        }
    }

    override fun getItemCount(): Int {
        return  list.size
    }
}
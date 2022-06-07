package com.example.businesshelper.data.adepters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.businesshelper.R
import com.example.businesshelper.data.Counterparty
import com.example.businesshelper.data.Product
import java.util.ArrayList

class catalogAdapter(val context: Context, val list: ArrayList<Product>, private val cellClickListener: catalogAdapter.CellClickListener)
    : RecyclerView.Adapter<catalogAdapter.MyVM>() {

    inner class MyVM(itemView: View):RecyclerView.ViewHolder(itemView) {
        val title:TextView = itemView.findViewById(R.id.title_product_VM)
        val price:TextView = itemView.findViewById(R.id.price_product_VM)

        init {
            itemView.setOnClickListener{ v:View->
                Toast.makeText(itemView.context,"Функция редактирования находиться в разработке.",
                    Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVM {
        val root = LayoutInflater.from(context).inflate(R.layout.product_card,parent,false)
        return MyVM(root)
    }

    override fun onBindViewHolder(holder: catalogAdapter.MyVM, position: Int) {
        holder.title.text = list[position].title
        holder.price.text= list[position].price.toString()

        val data = list[position]
        holder.itemView.setOnClickListener {
            cellClickListener.onClickProductListener(data)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface CellClickListener {
        fun onClickProductListener(data: Product)
    }
}
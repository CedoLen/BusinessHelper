package com.example.businesshelper.data.adepters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.businesshelper.R
import com.example.businesshelper.data.Product
import java.util.ArrayList

class basketAdapter(val context: Context, val list: ArrayList<Product>, private val cellClickListener: basketAdapter.CellClickListener)
    : RecyclerView.Adapter<basketAdapter.MyVM>() {

    class MyVM(itemView: View):RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title_product_inBasket_VM)
        val price: TextView = itemView.findViewById(R.id.price_product_inBasket_VM)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVM {
        val root = LayoutInflater.from(context).inflate(R.layout.itembasket_card,parent,false)
        return MyVM(root)
    }

    override fun onBindViewHolder(holder: basketAdapter.MyVM, position: Int) {
        holder.title.text = list[position].title
        holder.price.text= list[position].price.toString()

        val data = list[position]
        holder.itemView.setOnClickListener {
            cellClickListener.onClickBasketListener(data)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface CellClickListener {
        fun onClickBasketListener(data: Product)
    }

}
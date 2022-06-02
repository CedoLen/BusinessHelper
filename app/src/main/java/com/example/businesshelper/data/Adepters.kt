package com.example.businesshelper.data

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.businesshelper.R
import java.util.ArrayList

class listAdapter(val context: Context, val list: ArrayList<Status>): RecyclerView.Adapter<listAdapter.MyVH>() {
    class MyVH(itemView: View):RecyclerView.ViewHolder(itemView) {
        val textView: TextView=itemView.findViewById(R.id.text_adept_counterparties)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        val root =LayoutInflater.from(context).inflate(R.layout.counterparty_card,parent,false)
        return MyVH(root)
    }

    override fun onBindViewHolder(holder: MyVH, position: Int) {
        holder.textView.text = list[position].title
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
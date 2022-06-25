package com.example.businesshelper.data.adepters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.businesshelper.R
import com.example.businesshelper.data.InCome
import com.example.businesshelper.data.Order
import com.example.businesshelper.data.fullFinance
import java.util.ArrayList

class fullFinaceAdapter(val context: Context, val list: List<fullFinance>):RecyclerView.Adapter<fullFinaceAdapter.MyVM>() {

    class MyVM(itemView: View):RecyclerView.ViewHolder(itemView) {
        val title:TextView = itemView.findViewById(R.id.title_fin_VM)
        val progressBar:ProgressBar = itemView.findViewById(R.id.progress_fin_VM)
        val futureSum:TextView = itemView.findViewById(R.id.full_sum_fin_VM)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVM {
        val root = LayoutInflater.from(context).inflate(R.layout.progress_finance_card,parent,false)
        return fullFinaceAdapter.MyVM(root)
    }

    override fun onBindViewHolder(holder: MyVM, position: Int) {
        holder.title.text = list[position].title
        holder.futureSum.text=list[position].futureSum.toString()

        holder.progressBar.max = list[position].futureSum.toInt()
        holder.progressBar.progress=list[position].currentSum.toInt()
    }

    override fun getItemCount(): Int {
        return  list.size
    }
}
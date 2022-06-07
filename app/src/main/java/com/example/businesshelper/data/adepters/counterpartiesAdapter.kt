package com.example.businesshelper.data.adepters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.businesshelper.R
import com.example.businesshelper.data.Counterparty
import com.example.businesshelper.fragment.CounterpartiesFragment
import com.example.businesshelper.fragment.addfragment.AddCounterpartiesFragment
import com.example.businesshelper.fragment.editfragment.EditCounterpartyFragment
import java.util.ArrayList

class counterpartiesAdapter(val context: Context, val list: ArrayList<Counterparty>, private val cellClickListener: counterpartiesAdapter.CellClickListener):
    RecyclerView.Adapter<counterpartiesAdapter.MyVM>() {

      class MyVM(itemView: View):RecyclerView.ViewHolder(itemView) {
            val title: TextView =itemView.findViewById(R.id.title_counter_VM)
            val phone:TextView=itemView.findViewById(R.id.phone_counter_VM)
            val email:TextView=itemView.findViewById(R.id.email_counter_VM)

     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVM {
        val root = LayoutInflater.from(context).inflate(R.layout.counterparty_card,parent,false)
        return MyVM(root)
    }

    override fun onBindViewHolder(holder: MyVM, position: Int) {
        holder.title.text=list[position].company
        holder.email.text=list[position].email
        holder.phone.text=list[position].phone

        val data = list[position]
        holder.itemView.setOnClickListener {
            cellClickListener.onClickCounterListener(data)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface CellClickListener {
        fun onClickCounterListener(data: Counterparty)
    }
}
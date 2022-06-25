package com.example.businesshelper.addActivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.example.businesshelper.R
import com.example.businesshelper.data.Expenses
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MinusExpenseActivity : AppCompatActivity() {

    private lateinit var mainButton:FloatingActionButton
    private lateinit var edSum:EditText
    private lateinit var linLayout:LinearLayout
    private lateinit var list:ArrayList<Expenses>
    private lateinit var database: DatabaseReference

    @SuppressLint("SimpleDateFormat")
    private val sdf = SimpleDateFormat("yyyy.MM.dd HH:mm:ss")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_minus_expense)

        mainButton = findViewById(R.id.flbt_minus_expense)
        edSum = findViewById(R.id.edTx_sum_minus_expense)
        linLayout = findViewById(R.id.list_minus_articles)
        database = FirebaseDatabase.getInstance().getReference("expenses")

        mainButton.setOnClickListener{
            try {
                setListExp()
                if (list.size != 0) {
                    for (value in list) {
                        database.push().setValue(value)
                    }
                    this.onBackPressed()
                }
            }
            catch (e:Exception)
            {Toast.makeText(this,e.message,Toast.LENGTH_SHORT).show()}
        }
    }

    fun setListExp(){
        list= arrayListOf()
        list.clear()
        for (i in 0..linLayout.childCount-1){
            val v = linLayout.getChildAt(i)
            if(v is CheckBox)
            {
                if(v.isChecked){
                    val exp = Expenses(
                        date = sdf.format(Date()),
                        sum = edSum.text.toString().toLong(),
                        type = "te${i+1}"
                    )
                    list.add(exp)
                }
            }
        }
    }
}
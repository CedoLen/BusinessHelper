package com.example.businesshelper.addActivity

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.example.businesshelper.R
import com.example.businesshelper.data.InCome
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import kotlin.collections.ArrayList

class PlusInComeActivity : AppCompatActivity() {

    private lateinit var mainButton: FloatingActionButton
    private lateinit var edSum: EditText
    private lateinit var edDate: EditText
    private lateinit var linLayout: LinearLayout
    private lateinit var list:ArrayList<InCome>
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plus_in_come)

        mainButton = findViewById(R.id.flbt_plus_income)
        edSum = findViewById(R.id.edTx_sum_plus_income)
        edDate = findViewById(R.id.edTx_date_plus_income)
        linLayout = findViewById(R.id.list_plus_income)
        database = FirebaseDatabase.getInstance().getReference("futureInCome")

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
            {
                Toast.makeText(this,e.message, Toast.LENGTH_SHORT).show()}
        }

        edDate.setOnClickListener{
            val calendar = Calendar.getInstance()
            val mYear = calendar[Calendar.YEAR]
            val mMonth = calendar[Calendar.MONTH]
            val mDay = calendar[Calendar.DAY_OF_MONTH]

            val datePickerDialog = DatePickerDialog(this,
                { view, year, month, dayOfMonth -> edDate.setText(dayOfMonth.toString() + "." + (month + 1) + "." + year) },
                mYear,
                mMonth,
                mDay
            )
            datePickerDialog.show()
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
                    val exp = InCome(
                        date = edDate.text.toString(),
                        sum = edSum.text.toString().toInt(),
                        type = "tic${i+1}"
                    )
                    list.add(exp)
                }
            }
        }
    }
}
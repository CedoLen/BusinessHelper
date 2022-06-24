package com.example.businesshelper

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.example.businesshelper.data.CountProducts
import com.example.businesshelper.data.Counterparty
import com.example.businesshelper.data.Order
import com.example.businesshelper.data.Product
import com.example.businesshelper.data.adepters.basketAdapter
import com.example.businesshelper.data.adepters.counterpartiesAdapter
import com.google.android.material.navigation.NavigationBarView
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.properties.Delegates


class AddOrderActivity : AppCompatActivity(), basketAdapter.CellClickListener, counterpartiesAdapter.CellClickListener {
    private lateinit var currentDate:TextView
    private lateinit var finishDate:TextView
    private lateinit var addOrder:AppCompatButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var spinner: Spinner

    private lateinit var database: DatabaseReference
    private lateinit var query: Query

    private lateinit var productList: ArrayList<Product>
    private lateinit var counterList:ArrayList<Counterparty>
    private lateinit var countProducts: ArrayList<CountProducts>
    private lateinit var arrayCompanies: ArrayList<String>

    private lateinit var basket:HashMap<String,CountProducts>
    private lateinit var counter:Counterparty
    private lateinit var company:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_order)

        database = FirebaseDatabase.getInstance().reference

        addOrder=findViewById(R.id.add_Order)
        currentDate=findViewById(R.id.text_dateRegist)
        finishDate=findViewById(R.id.text_dateFinish)
        recyclerView=findViewById(R.id.rV_basketInOrder)
        spinner=findViewById(R.id.spiner_counterInOrder)

        recyclerView.setHasFixedSize(true)

        getBasket()
        getCounter()

        val sdf = SimpleDateFormat("dd.M.yyyy", Locale.getDefault());
        var curDate = sdf.format(Date());
        currentDate.text = curDate
        finishDate.text = curDate

        finishDate.setOnClickListener{
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, myear, mmonth, mdayOfMonth ->
                finishDate.setText(""+ mdayOfMonth +"."+ mmonth +"."+ myear)
            }, year, month, day)
            datePickerDialog.show()
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                company = p0!!.getItemAtPosition(p2).toString()
                counter = counterList[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                company = "${counterList[0].company} ${counterList[0].phone}"
                counter = counterList[0]
            }

        }

        addOrder.setOnClickListener {
            try {
                val id=database.push().key
                val status="st1"
                val dateRegistration=currentDate.text.toString()
                val dateIssue=finishDate.text.toString()
                val deliveryAddress=""
                val priceDelivery:Long=0
                setCountProducts()
                val totalPrice = setBasket()

                val order = Order(id,status, dateRegistration,dateIssue,deliveryAddress,priceDelivery,totalPrice,company,counter,basket)

                database.child("orders").push().setValue(order)
                database.child("basket").removeValue()
                this.onBackPressed()
            }
            catch(e:Exception) {
                Toast.makeText(this,e.message.toString(),Toast.LENGTH_LONG).show()
            }
        }
    }

    
    fun setCountProducts()
    {
        if(productList.isNotEmpty()){
            countProducts= arrayListOf()
            for (item in productList)
            {
                val temp = CountProducts(item,1)
                if(countProducts.contains(temp))
                {
                    for (item2 in countProducts){
                        if(item2.product==temp.product)
                        {
                            item2.count = item2.count!!.plus(1)
                        }

                    }
                }
                else
                {
                    countProducts.add(temp)
                }
            }
        }
    }
    fun setBasket():Long{
        if(countProducts.isNotEmpty())
        {
            basket= HashMap(countProducts.size)
            basket.clear()
            var totalPrise:Long = 0
            for (i in 0..countProducts.size.minus(1))
            {
                totalPrise = totalPrise.plus((countProducts[i].product!!.price)!!*(countProducts[i].count!!))
                basket.put(i.toString(),countProducts[i])
            }
            return totalPrise
        }
        return 0
    }

    fun getBasket()
    {
        productList = arrayListOf<Product>()
        database.child("basket").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                productList.clear()
                if(snapshot.exists()){
                    for(item in snapshot.children)
                    {
                        val product = item.getValue(Product::class.java)
                        productList.add(product!!)
                    }
                    productList.sortBy { item->item.title }
                    recyclerView.adapter = basketAdapter(this@AddOrderActivity,productList,this@AddOrderActivity)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "loadPost:onCancelled", error.toException())
            }
        })
    }
    fun getCounter()
    {
        counterList = arrayListOf<Counterparty>()
        database.child("counterparties").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                counterList.clear()
                if(snapshot.exists()){
                    for(item in snapshot.children)
                    {
                        val human = item.getValue(Counterparty::class.java)
                        counterList.add(human!!)
                    }
                    counterList.sortBy { item->item.company }

                    setArrayAdepter()

                    val adepter = ArrayAdapter(this@AddOrderActivity,android.R.layout.simple_spinner_item,arrayCompanies)
                    adepter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner.adapter = adepter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "loadPost:onCancelled", error.toException())
            }

        })
    }

    fun setArrayAdepter()
    {
        arrayCompanies = arrayListOf<String>()
        if(counterList.isNotEmpty())
        {
            arrayCompanies.clear()
            for(item in counterList)
            {
                arrayCompanies.add("${item.company} ${item.phone}")
            }
        }
    }

    override fun onClickBasketListener(data: Product) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Удаление элемента")
            .setMessage("Убрать из корзины: ${data.title}?")
            .setCancelable(true)
            .setPositiveButton("Да") { dialog, id ->

                query = database.child("basket").orderByChild("id").equalTo(data.id.toString())
                query.addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if(snapshot.exists()){
                            for (item in snapshot.children)
                            {
                                item.ref.removeValue()
                            }
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        Log.e(ContentValues.TAG, "onCancelled", error.toException());
                    }
                })
            }
            .setNegativeButton("Нет"){ dialog, id -> }
        builder.show()
    }

    override fun onClickCounterListener(data: Counterparty) {
    }
}
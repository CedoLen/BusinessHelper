package com.example.businesshelper.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.businesshelper.R
import com.example.businesshelper.data.api.Currencies
import com.example.businesshelper.data.api.InterfaceCurrency
import com.example.businesshelper.data.api.RetrofitCurrency
import com.example.businesshelper.data.api.Valute
import com.example.businesshelper.databinding.FragmentFinanceBinding
import retrofit2.Call
import retrofit2.Response

class FinanceFragment:Fragment(R.layout.fragment_finance) {

    private lateinit var bind:FragmentFinanceBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentFinanceBinding.inflate(inflater)

        val ret = RetrofitCurrency().getRetrofit()
        val inter = ret.create(InterfaceCurrency::class.java)
        val call = inter.getCurrency().enqueue(object : retrofit2.Callback<Currencies>{
            override fun onResponse(call: Call<Currencies>, response: Response<Currencies>) {
                if(response.isSuccessful){
                    try {
                        val temp = response.body()!!
                        bind.outputUSDCurrency.setText(temp!!.Valutes!!.get("USD")!!.Value)
                        bind.outputEURCurrency.setText(temp!!.Valutes!!.get("EUR")!!.Value)
                    }
                    catch (e:Exception)
                    {
                        Toast.makeText(bind.root.context,e.message,Toast.LENGTH_SHORT).show()
                    }

                }
            }

            override fun onFailure(call: Call<Currencies>, t: Throwable) {
                Toast.makeText(bind.root.context, t.localizedMessage, Toast.LENGTH_LONG).show()
            }

        })

        return bind.root
    }
}
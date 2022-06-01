package com.example.businesshelper

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.businesshelper.fragment.BasketFragment
import com.example.businesshelper.fragment.CatalogFragment
import com.example.businesshelper.fragment.CounterpartiesFragment
import com.example.businesshelper.fragment.OrderFragment
import com.example.businesshelper.fragment.addfragment.AddCounterpartiesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var navigation: BottomNavigationView
    private lateinit var floating: View

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment).commit()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation = findViewById(R.id.bottomNavigationView)

        val catalogFragment = CatalogFragment()
        val orderFragment = OrderFragment()
        val basketFragment = BasketFragment()
        val counterpartiesFragment = CounterpartiesFragment()
        val addCounterpartiesFragment = AddCounterpartiesFragment()

        setCurrentFragment(catalogFragment)

        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.catalog -> setCurrentFragment(catalogFragment)
                R.id.basket -> setCurrentFragment(basketFragment)
                R.id.people -> setCurrentFragment(counterpartiesFragment)
                R.id.order -> setCurrentFragment(orderFragment)
            }
            true
        }
    }
}
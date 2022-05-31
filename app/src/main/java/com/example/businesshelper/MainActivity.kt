package com.example.businesshelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navigation: BottomNavigationView


    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment).commit()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation = findViewById(R.id.bottomNavigationView)
        val newsFragment = NewsFragment()
        val financeFragment = FinanceFragment()
        val tasksFragment = TasksFragment()
        val catalogFragment = CatalogFragment()
        val orderFragment = OrderFragment()
        val basketFragment = BasketFragment()
        val counterpartiesFragment = CounterpartiesFragment()


        setCurrentFragment(newsFragment)

        navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.news->setCurrentFragment(newsFragment)
                R.id.catalog->setCurrentFragment(catalogFragment)
                R.id.basket->setCurrentFragment(basketFragment)
                R.id.people->setCurrentFragment(counterpartiesFragment)
                R.id.order->setCurrentFragment(orderFragment)

            }
            true
        }
    }
}
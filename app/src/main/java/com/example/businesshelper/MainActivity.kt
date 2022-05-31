package com.example.businesshelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navigation: BottomNavigationView


    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation = findViewById(R.id.bottomNavigationView)
        val firstFragment=NewsFragment()
        val secondFragment=FinanceFragment()
        val thirdFragment=TasksFragment()

        setCurrentFragment(firstFragment)


        navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.news->setCurrentFragment(firstFragment)
                R.id.finance->setCurrentFragment(secondFragment)
                R.id.tasks->setCurrentFragment(thirdFragment)

            }
            true
        }
    }
}
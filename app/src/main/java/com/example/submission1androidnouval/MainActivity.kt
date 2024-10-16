package com.example.submission1androidnouval

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.submission1androidnouval.databinding.ActivityMainBinding
import com.example.submission1androidnouval.fragmen.FinishedFragment
import com.example.submission1androidnouval.fragmen.HomeFragment
import com.example.submission1androidnouval.fragmen.UpcomingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setContentView(R.layout.activity_main)//

        // Initialize fragments
        val homeFragment = HomeFragment()
        val upcomingFragment = UpcomingFragment()
        val finishedFragment = FinishedFragment()

        // Set the initial fragment to homeFragment
        makeCurrentFragment(homeFragment)

        // Set up the BottomNavigationView
        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_home -> makeCurrentFragment(homeFragment)
                R.id.ic_upcoming -> makeCurrentFragment(upcomingFragment)
                R.id.ic_finished -> makeCurrentFragment(finishedFragment)
            }
            true
        }
    }

    // Function to switch the current fragment
    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_wrapper, fragment)
            commit()
        }
}
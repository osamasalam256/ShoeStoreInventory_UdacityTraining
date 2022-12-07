package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolBar)
        val navHostFragment= supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        // setup app bar configuration with custom tool bar.
        NavigationUI.setupWithNavController(binding.toolBar, navController,appBarConfiguration)
        // declare which page should have app bar.
        navController.addOnDestinationChangedListener{_, destination, _, ->
            when (destination.id) {
                R.id.shoeListFragment -> {
                    supportActionBar!!.show()
                    supportActionBar!!.title = "Shoe list"
                }
                R.id.detailsFragment -> {
                    supportActionBar!!.show()
                    supportActionBar!!.title = "Shoe details"
                }
                else -> {
                    supportActionBar!!.hide()
                }
            }
        }
    }

}
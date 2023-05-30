package com.example.testespessoais

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import com.example.testespessoais.R
import com.example.testespessoais.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId){
        R.id.action_settings ->{
            val navController = findNavController(R.id.fragmentContainerView)
            navController.navigate(R.id.action_homeFragment_to_settingsFragment)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}
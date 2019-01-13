package com.mstoyan.test.red.redtestproject.core

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.mstoyan.test.red.redtestproject.R

abstract class ProjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.addOnBackStackChangedListener{
            if (supportFragmentManager.backStackEntryCount > 0)
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            else supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        }
    }

    private fun popFragment(){
        if (supportFragmentManager.backStackEntryCount > 0)
            supportFragmentManager.popBackStack()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item!!.itemId
        when (id){
            R.id.home -> popFragment()
        }
        return super.onOptionsItemSelected(item)
    }
}
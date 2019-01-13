package com.mstoyan.test.red.redtestproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mstoyan.test.red.redtestproject.R
import com.mstoyan.test.red.redtestproject.core.ProjectActivity
import com.mstoyan.test.red.redtestproject.core.ui.BaseFragmentInteractionListener
import com.mstoyan.test.red.redtestproject.ui.search.MainFragment

class MainActivity : ProjectActivity(), BaseFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (null == savedInstanceState) {
            showFragment(MainFragment::class.java, null, false)
        }
    }

    override fun <T: Fragment> showFragment(fragmentClass: Class<T>, bundle: Bundle?,
                                          addToBackStack: Boolean){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        var fragment = supportFragmentManager.findFragmentByTag(fragmentClass.simpleName)
        if (null == fragment){
            try {
                fragment = fragmentClass.newInstance()
                fragment.arguments = bundle
            } catch (e : Exception){
                when (e){
                    is InstantiationException,
                    is IllegalAccessException -> throw RuntimeException("Error creating fragment: ", e)
                    else -> throw RuntimeException("I don't know wtf happened", e)
                }
            }
        }
        if (addToBackStack) fragmentTransaction.addToBackStack(null)
        fragmentTransaction.replace(R.id.container, fragment!!, fragmentClass.simpleName).commit()
    }
}

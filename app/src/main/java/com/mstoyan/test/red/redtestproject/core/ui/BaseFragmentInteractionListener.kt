package com.mstoyan.test.red.redtestproject.core.ui

import android.os.Bundle
import androidx.fragment.app.Fragment

interface BaseFragmentInteractionListener {
    fun <T: Fragment> showFragment(fragmentClass: Class<T>, bundle: Bundle?,
                                   addToBackStack: Boolean)
}
package com.yousufsohail.tmdb.common

/**
 * Extension function for AppCompatActivity.
 */

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity


fun <T : ViewModel> Fragment.obtainViewModel(viewModelClass: Class<T>) =
        ViewModelProviders.of(this, ViewModelFactory.getInstance()).get(viewModelClass)

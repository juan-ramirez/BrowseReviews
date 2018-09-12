package com.juan.browsereviews.model

import android.arch.lifecycle.ViewModel
import com.juan.browsereviews.di.ApiModule
import com.juan.browsereviews.di.DaggerViewModelInjector
import com.juan.browsereviews.di.ViewModelInjector

abstract class BaseViewModel: ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(ApiModule)
            .build()

    init {
        when (this) {
            is ReviewsListViewModel -> injector.inject(this)
        }
    }
}
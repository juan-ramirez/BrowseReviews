package com.juan.browsereviews.di

import com.juan.browsereviews.model.ReviewsListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApiModule::class)])
/*Injects any View Model that's necessary, this is purely illustrative as for our case we only need one
in case we need more, for user lists, guide short profiles, ads or whatever, can be added here
 */
interface ViewModelInjector {
    fun inject(reviewsListViewModel: ReviewsListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        //here ^
        fun networkModule(networkModule: ApiModule): Builder
    }
}
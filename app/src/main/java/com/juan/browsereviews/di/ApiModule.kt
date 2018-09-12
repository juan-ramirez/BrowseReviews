package com.juan.browsereviews.di

import com.juan.browsereviews.utils.BASE_SERVICE_URL
import com.juan.browsereviews.utils.GetYourGuideApi
import com.juan.browsereviews.utils.TOUR_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@Suppress("unused")
object ApiModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideGetYourGuideApi(retrofit: Retrofit): GetYourGuideApi {
        return retrofit.create(GetYourGuideApi::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_SERVICE_URL + TOUR_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }
}
package com.elchaninov.gbprofessionaldevelopment.model.api

import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("words/search")
    fun search(
        @Query("search") wordToSearch: String
    ): Single<List<DataModel>>
}
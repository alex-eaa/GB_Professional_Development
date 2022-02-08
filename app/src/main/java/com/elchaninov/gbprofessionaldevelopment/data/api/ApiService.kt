package com.elchaninov.gbprofessionaldevelopment.data.api

import com.elchaninov.model.dto.DataModelDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("words/search")
    suspend fun search(@Query("search") wordToSearch: String): List<DataModelDto>
}
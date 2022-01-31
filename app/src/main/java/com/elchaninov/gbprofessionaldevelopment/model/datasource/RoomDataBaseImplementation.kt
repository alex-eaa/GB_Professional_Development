package com.elchaninov.gbprofessionaldevelopment.model.datasource

import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import io.reactivex.rxjava3.core.Observable
import java.lang.Error

class RoomDataBaseImplementation : DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return Observable.just(listOf(DataModel(null, null)))
            .map {
                throw Exception("Нет связи")
            }
        //TODO("not implemented")
    }
}
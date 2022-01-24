package com.elchaninov.gbprofessionaldevelopment.view

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class SchedulerProvider {
    fun io() = Schedulers.io()
    fun ui() = AndroidSchedulers.mainThread()
}
package com.elchaninov.gbprofessionaldevelopment.view

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerProvider {
    fun io() = Schedulers.io()
    fun ui() = AndroidSchedulers.mainThread()
}
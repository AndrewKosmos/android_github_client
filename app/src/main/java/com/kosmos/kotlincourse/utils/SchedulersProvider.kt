package com.kosmos.kotlincourse.utils

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SchedulersProvider @Inject constructor() {

    fun ui() : Scheduler = AndroidSchedulers.mainThread()
    fun io() : Scheduler = Schedulers.io()
    fun newThread() : Scheduler = Schedulers.newThread()

}